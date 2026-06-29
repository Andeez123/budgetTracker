from fastapi import FastAPI, UploadFile, Header
from fastapi.middleware.cors import CORSMiddleware
from pathlib import Path
from paddleocr import PaddleOCR
import json
import cv2
import numpy as np
from google import genai
from google.genai import types
from dotenv import load_dotenv
import os
import httpx
from typing import Annotated

load_dotenv()
api_key = os.getenv("GEMINI_API_KEY")
client = genai.Client(api_key=api_key)

UPLOAD_DIR = Path()/'uploads'

ocr = PaddleOCR(
    lang="en",
    use_doc_orientation_classify=False,
    use_doc_unwarping=False,
    use_textline_orientation=False,
    engine="paddle",
    enable_mkldnn=False,
)

app = FastAPI()
app.add_middleware(
    CORSMiddleware,
    allow_origins=['*'],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"]
)

@app.post('/uploadfile')
async def create_upload_file(file_upload: UploadFile, authorization = Header(None)):
    url = "http://localhost:8080/api/v1/transactions/user/add-transactions"
    data = await file_upload.read()
    # save_to = UPLOAD_DIR/file_upload.filename
    # with open(save_to, 'wb') as f:
    #     f.write(data)
    
    # return {"filename": file_upload.filename}
    np_arr = np.frombuffer(data, np.uint8)
    image = cv2.imdecode(np_arr, cv2.IMREAD_COLOR)
    if image is None:
        print("Error: Could not read the image. Check the file path.")
    else:
        result = ocr.predict(image)
        for res in result:
            texts = res['rec_texts']
            json_data = {"rec_texts": texts}
        
        json_string = json.dumps(json_data)
        response = client.models.generate_content(
            model="gemini-2.5-flash", 
            contents=[
                f"Here is the JSON data:\n{json_string}",
                "Extract the transaction amount, category and type: (which can be income or expense) from the json string, the response has to be in the following json format: type, amount, categoryName"
            ],
            config=types.GenerateContentConfig(
                response_mime_type="application/json"
            )
        )
        headers = {
            "Authorization": authorization
        }
        print(response.text)
        extracted = json.loads(response.text)
        print(extracted)
        reply = httpx.post(url, json=extracted, headers=headers)