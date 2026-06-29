import { useEffect, useState } from "react";
import axios from "axios";
import Table from 'react-bootstrap/Table'
import { format } from "date-fns"
import { Button } from 'primereact/button';

import "../Styles/transactions.css"
import Popup from "./Popup";
import UserData from "./UserData";
import { FileForm } from "./FileForm";

const TRANSACTIONS_API = "http://localhost:8080/api/v1/transactions/user/my-transactions"

const Transactions = (props) => {
    const [transactions, setTransactions] = useState([])
    const [gotData, setGotData] = useState(false)
    const [tryLoad, setTryLoad] = useState(false)
    const [showPopUp, setShowPopUp] = useState(false)
    const [isSubmitting, setIsSubmitting] = useState(false)
    const [userAmount, setAmount] = useState()

    const fetchTransactions = () => {
        const token = localStorage.getItem('jwt')
        return axios.get(TRANSACTIONS_API, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        }).then(response => {
            setTransactions(response.data)
            setTryLoad(true)
            setGotData(response.data.length > 0)
        }).catch(error => {
            console.log("error")
            setGotData(false)
            setTryLoad(true)
        })
    }

    const fetchUser = () => {
        const user_API = "http://localhost:8080/api/v1/users/my-account"
        const token = localStorage.getItem('jwt')
        return axios.get(user_API, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        }).then(response => {
            setAmount(response.data.accAmt)
        })
    }

    useEffect(() => {
        fetchTransactions()
        fetchUser()
    }, [])

    

    const closePopUp = () => {
        setShowPopUp(false)
    }

    const popUpFooter = (
        <div className="popup-footer">
            <Button label="Cancel" severity="secondary" onClick={closePopUp} disabled={isSubmitting} />
            <Button type="submit" form="add-transaction-form" label="Submit" icon="pi pi-check" loading={isSubmitting} />
        </div>
    )

    return (
        <div>
            <UserData userAmount={userAmount}></UserData>

            <div className="button-holder">
                <Button className="button-class"
                    onClick={() => setShowPopUp(true)}
                    label="Add transaction" text raised />
            </div>

            <div>
                <FileForm
                    onTransactionAdded={fetchTransactions}
                    renderUser={fetchUser}></FileForm>
            </div>

            <Popup showPopUp={showPopUp}
                closePopUp={closePopUp}
                popUpFooter={popUpFooter}
                onTransactionAdded={fetchTransactions}
                renderUser={fetchUser}
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
