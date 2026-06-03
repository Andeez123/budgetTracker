import { useEffect, useState } from "react";
import axios from "axios";
import Table from 'react-bootstrap/Table'
import Form from 'react-bootstrap/Form'
import { format } from "date-fns"
import { Button } from 'primereact/button';

import "../Styles/transactions.css"
import Popup from "./Popup";

const TRANSACTIONS_API = "http://localhost:8080/api/v1/transactions/user/my-transactions"

const Transactions = (props) => {
    const [transactions, setTransactions] = useState([])
    const [gotData, setGotData] = useState(false)
    const [tryLoad, setTryLoad] = useState(false)
    const [showPopUp, setShowPopUp] = useState(false)
    const [isSubmitting, setIsSubmitting] = useState(false)
    
    const [form, setForm] = useState({
        type: "income",
        amount: "",
        categoryName: "",
    })

    useEffect(() => {
        const token = localStorage.getItem('jwt')
        axios.get(TRANSACTIONS_API, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        }).then(response => {
            console.log("api call success")
            setTransactions(response.data)
            setTryLoad(true)
            if (response.data.length > 0) {
                setGotData(true)
            }
        }).catch(error => {
            console.log("error")
            setGotData(false)
            setTryLoad(true)
        })
    }, [])

    

    const resetForm = () => {
        setForm({ type: "income", amount: "", categoryName: "" })
    }

    const closePopUp = () => {
        setShowPopUp(false)
        resetForm()
    }

    const popUpFooter = (
        <div className="popup-footer">
            <Button label="Cancel" severity="secondary" onClick={closePopUp} disabled={isSubmitting} />
            <Button type="submit" form="add-transaction-form" label="Submit" icon="pi pi-check" loading={isSubmitting} />
        </div>
    )

    return (
        <div>
            <div className="button-holder">
                <Button className="button-class"
                    onClick={() => setShowPopUp(true)}
                    label="Add transaction" text raised />
            </div>

            <Popup showPopUp={showPopUp}
                closePopUp={closePopUp}
                popUpFooter={popUpFooter}
            ></Popup>

            {!gotData && tryLoad &&
                <div>
                    No transactions found
                </div>}
            
            {gotData && tryLoad &&
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
                                        <td>{format(new Date(item.createdOn), "dd MMM yyyy")}</td>
                                    </tr>
                                )
                            })}
                        </tbody>
                    </Table>
                </div>}
        
        </div>
    )
}

export default Transactions
