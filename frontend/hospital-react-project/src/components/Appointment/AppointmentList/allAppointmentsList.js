import React, {useEffect, useState} from 'react';
import {Link} from "react-router-dom";
import SingleAppointment from "../SingleAppointment/singleAppointment";
import {Divider} from "@material-ui/core";


const AllAppointmentList = (props) => {


    const singleAppointment = props.appointments.map((appointment) => {

        return(
            <SingleAppointment appointment={appointment} key={appointment.id}/>
        );
    });


    return (
        <div>
            <h4>APPOINTMENTS</h4>
            <Divider />
            <div className="table-wrapper">
                <table className="fl-table">
                    <thead>
                    <tr>
                        <th>#ID</th>
                        <th>Doctor name</th>
                        <th>Patient name</th>
                        <th>Consultancy Fee</th>
                        <th>Appointment Date/Time</th>
                        <th>Appointment Creation Time</th>
                        <th>Current status</th>
                    </tr>
                    </thead>
                    <tbody>
                    {singleAppointment}
                    </tbody>
                </table>
            </div>
        </div>

    );
}

export default AllAppointmentList;
