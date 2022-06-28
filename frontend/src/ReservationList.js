import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';


class ReservationList extends Component {

    constructor(props) {
        super(props);
        this.state = {reservations: []};
    }


    componentDidMount() {
        fetch('/reservations?username=vincent')
            .then(response => response.json())
            .then(data => this.setState({reservations: data}));
    }


    render() {
        const {reservations, isLoading} = this.state;

    if (isLoading) {
        return <p>Loading...</p>;
    }

    const reservationList = reservations.map(reservation => {
        return <tr key={reservation.id}>
            <td style={{whiteSpace: 'nowrap'}}>{reservation.stay.id}</td>
            <td>{reservation.checkin_date}</td>
            <td>{reservation.checkout_date}</td>
        </tr>
    });

    return (
        <div>
            <AppNavbar/>
            <Container fluid>
                <div className="float-right">
                </div>
                <h3>reservations</h3>
                <Table className="mt-4">
                    <thead>
                    <tr>
                        <th width="30%">id</th>
                        <th width="30%">checkin</th>
                        <th width="40%">checkout</th>
                    </tr>
                    </thead>
                    <tbody>
                    {reservationList}
                    </tbody>
                </Table>
            </Container>
        </div>
    );
}
}
export default ReservationList;