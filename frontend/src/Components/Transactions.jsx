import { Component, useEffect, useState } from "react";
import axios from "axios";
import { Routes, Route } from "react-router-dom";
import Table from 'react-bootstrap/Table'
import {format} from "date-fns"

const Transactions = (props) => {

    const token = props.token
    const api = "http://localhost:8080/api/v1/transactions/user/my-transactions"
    const [transactions, setTransactions] = useState([])

    useEffect(() => {
        const token = localStorage.getItem('jwt')
        axios.get(api, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        }).then(response => {
            console.log("api call success")
            setTransactions(response.data)
            console.log(response.data)
        }).catch(error => {
            console.log("error")
        })
    }, [])

    return (
        <div>
            <Table striped bordered hover variant="dark">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Transaction type</th>
                        <th>Category</th>
                        <th>Amount</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    {transactions.map((item) => {
                        return (
                            <tr key={item.TransactionID}>
                                <td>{item.TransactionID}</td>
                                <td>{item.type}</td>
                                <td>{item.categoryName}</td>
                                <td>{item.amount}</td>
                                <td>{ format(new Date(item.createdOn), "dd MMM yyyy")}</td>
                            </tr>
                        )
                    })}
                </tbody>
            </Table>

        </div>
    )
}

export default Transactions