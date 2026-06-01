import { Component, useEffect } from "react";
import axios from "axios";
import { Routes, Route} from "react-router-dom";

const Transactions = (props) => {

    const token = props.token
    const api = "http://localhost:8080/api/v1/transactions/user/my-transactions"

    useEffect(() => {
        const token = localStorage.getItem('jwt')
        axios.get(api, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        }).then(response => {
            console.log("api call success")
        }).catch(error => {
            console.log("error")
        })
    })

    return (
        <div>
            <h2>
                Home page
            </h2>

        </div>
    )
}

export default Transactions