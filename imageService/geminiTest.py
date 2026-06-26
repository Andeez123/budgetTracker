from google import genai
from dotenv import load_dotenv
import os

load_dotenv()

apiKey = os.getenv("GEMINI_API_KEY")

client = genai.Client(api_key=apiKey)

file_path = "output.json"
with open(file_path, 'r', encoding='utf-8') as f:
    json_data = f.read()

response = client.models.generate_content(
    model="gemini-2.5-flash", # Use gemini-2.5-flash or gemini-2.5-flash-lite
    contents=[
        f"Here is the JSON data:\n{json_data}",
        "Extract the transaction amount and category from the json file"
    ]
)

print(response.text)