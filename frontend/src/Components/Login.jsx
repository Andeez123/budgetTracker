import { Container } from 'react-bootstrap';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import "../Styles/Login.css"
import { Link, useNavigate} from 'react-router-dom';
import { useState } from 'react';
import axios from 'axios';

const Login = () => {
    const [email, setEmail] = useState('')
    const [pwd, setPwd] = useState('')
    const api = "http://localhost:8080/api/v1/users/login"
    const navigate = useNavigate()

    const handleSubmit = (event) => {
        event.preventDefault();
        axios.post(api, {
            email,
            password:pwd
        }, {
            withCredentials: true
        }).then(response => {
            console.log("Success")
            const { token } = response.data
            localStorage.setItem('jwt', token)
            console.log(token)
            navigate('/home')
        }).catch(error => {
            console.log("Error logging in")
        })
    }

    return (
        <div>
            <Container className='container'>
                <Form onSubmit={handleSubmit}>
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
            <div className='register'>
                <Link to="/register" style={{ textDecoration: 'none' }}>Register</Link>
            </div>
        </div>
    )
}

export default Login