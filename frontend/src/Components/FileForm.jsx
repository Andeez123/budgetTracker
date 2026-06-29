import axios from "axios"
import { useState } from "react"

export const FileForm = (props) => {
    const [imageData, setImage] = useState(null)
    const token = localStorage.getItem('jwt')

    const handleSubmit = (event) => {
        event.preventDefault()

        const formData = new FormData()
        formData.append('file_upload', imageData)

        const user_API = "http://localhost:8081/uploadfile"

        axios.post(user_API, formData, { headers: { 'Authorization': `Bearer ${token}` } })
            .then(response => {
                console.log("Success:", response.data)
                props.onTransactionAdded()
                props.renderUser()
            }).catch(error => {
            console.log("Error:", error)
        })
    }

    return (
        <div>
            <h1>
                Upload file
            </h1>

            <form onSubmit={handleSubmit}>
                <input type="file" onChange={(event) => {setImage(event.target.files[0])}}/>
                <button type="submit">Upload</button>
            </form>


        </div>
    )
}

