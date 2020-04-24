import React, {useEffect, useState} from 'react';
import {Link} from "react-router-dom";
import SingleAppointment from "../SingleAppointment/singleAppointment";


const AllAppointmentList = (props) => {


    const singleAppointment = props.appointments.map((appointment) => {

        return(
            <SingleAppointment appointment={appointment} key={appointment.id}/>
        );
    });


    return (
        <div>
            <div className="table-wrapper">
                <table className="fl-table">
                    <thead>
                    <tr>
                        <th>#ID</th>
                        <th>Doctor name</th>
                        <th>Patient name</th>
                        <th>Specialization</th>
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
