import { Component, useState } from "react";
import { Container } from 'react-bootstrap';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import "../Styles/Login.css"
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Register = () => {
    const [fName, setFname] = useState('')
    const [Lname, setLname] = useState('')
    const [email, setEmail] = useState('')
    const [pwd, setPwd] = useState('')
    const api = "http://localhost:8080/api/v1/users"
    const navigate = useNavigate()

    const handleSubmit = (event) => {
        event.preventDefault();
        alert("Sending form data")
        axios.post(api, {
            firstName: fName,
            lastName: Lname,
            phoneNumber: null,
            emailAddress: email,
            password: pwd,
            accAmt: 0
        }).then(response => {
            console.log("Success")
            navigate("/")
        }).catch(error => {
            console.log("Error adding user")
        })
    }

    return (
        <div>
            <Container className='container'>
                <Form onSubmit={handleSubmit}>
                    <Form.Group className="mb-3">
                        <Form.Label>First Name</Form.Label>
                        <Form.Control type="text" placeholder="Enter First Name" value={fName}
                        onChange={event => setFname(event.target.value)}/>
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label>Last Name</Form.Label>
                        <Form.Control type="text" placeholder="Enter Last Name" value={Lname}
                        onChange={event => setLname(event.target.value)}/>
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="formBasicEmail">
                        <Form.Label>Email address</Form.Label>
                        <Form.Control type="email" placeholder="Enter email" value={email}
                        onChange={event => setEmail(event.target.value)}/>
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="formBasicPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" placeholder="Password" value={pwd}
                            onChange={event => setPwd(event.target.value)}/>
                    </Form.Group>
                    
                    <Button variant="primary" type="submit">
                        Submit
                    </Button>
                </Form>
            </Container>
        </div>
    )
}

export default Register