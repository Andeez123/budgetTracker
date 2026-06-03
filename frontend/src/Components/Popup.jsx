import { Dialog } from 'primereact/dialog';
import Form from 'react-bootstrap/Form'
import { useState } from "react";
import axios from "axios"
import { Dropdown } from 'primereact/dropdown';
import "../Styles/popup.css"

const Popup = (props) => {
    const token = localStorage.getItem('jwt')
    const ADD_TRANSACTION_API = "http://localhost:8080/api/v1/transactions/user/add-transactions"
    const [isSubmitting, setIsSubmitting] = useState(false)
    const [form, setForm] = useState({
        type: "",
        amount: "",
        categoryName: "",
    })

    const TYPE_OPTIONS = [
        { label: "Income", value: "income" },
        { label: "Expense", value: "expense" },
    ]

    const handleSubmit = (event) => {
        event?.preventDefault()
        setIsSubmitting(true)

        axios.post(ADD_TRANSACTION_API, {
            type: form.type,
            amount: Number(form.amount),
            categoryName: form.categoryName.trim(),
        }, { headers: {'Authorization': `Bearer ${token}`}})
            .then(() => {
                props.closePopUp()

            })
            .catch(error => {
                console.log("Failed to add transaction", error)
            })
            .finally(() => setIsSubmitting(false))
    }

    return (
        <Dialog
            header="Add transaction"
            visible={props.showPopUp}
            onHide={props.closePopUp}
            footer={props.popUpFooter}
            modal
            className="transaction-dialog"
        >
            <Form id="add-transaction-form" onSubmit={handleSubmit}>
                <Form.Group className="mb-3" controlId="transactionType">
                    <Dropdown
                        onChange={e => setForm({ ...form, type: e.value })}
                        options={TYPE_OPTIONS}
                        optionLabel='label' placeholder='Select a type'
                        value={form.type}
                        className='dropdown-style'
                    ></Dropdown>
                </Form.Group>

                <Form.Group className="mb-3" controlId="transactionAmount">
                    <Form.Label>Amount</Form.Label>
                    <Form.Control
                        type="number"
                        min="0"
                        step="0.01"
                        placeholder="e.g. 200"
                        value={form.amount}
                        onChange={e => setForm({ ...form, amount: e.target.value })}
                        required
                    />
                </Form.Group>

                <Form.Group className="mb-3" controlId="transactionCategory">
                    <Form.Label>Category</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="e.g. food"
                        value={form.categoryName}
                        onChange={e => setForm({ ...form, categoryName: e.target.value })}
                        required
                    />
                </Form.Group>
            </Form>
        </Dialog>
    )
}

export default Popup