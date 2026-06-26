from paddleocr import PaddleOCR
import json

ocr = PaddleOCR(lang='en')

img_path = 'image.jpg'

ocr = PaddleOCR(
    use_doc_orientation_classify=False,
    use_doc_unwarping=False,
    use_textline_orientation=False,
    engine="paddle",
    enable_mkldnn=False,
)
result = ocr.predict(img_path)
for res in result:
    texts = res['rec_texts']
    json_data = {"rec_texts": texts}

with open("output.json", "w") as file:
    json.dump(json_data, file, indent=4)