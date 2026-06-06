import axios from "axios"
import { useEffect, useState } from "react"
import { Container } from "react-bootstrap";

const UserData = (props) => {

    const accVal = 10

    return (
        <div>
            <Container>
                Amount remaining: {props.userAmount}
            </Container>
        </div>
    )
}


export default UserData