import React, {useEffect, useState} from 'react';
import {Link} from "react-router-dom";
import SingleAppointmentBy from "../SingleAppointment/singleAppointmentBy";
import {Divider} from "@material-ui/core";

const AppointmentList = (props) => {

    const[flag, setFlag] = useState('');
    const [cancelFlag, setCancelFlag] = useState('');

    useEffect(() => {
        props.onClick(props.match.params.id)
        if(props.flag === "byDoctor"){
            setFlag("Patient name");
            setCancelFlag("Cancel by Doctor");
        }else {
            setFlag("Doctor name");
            setCancelFlag("Cancel by Patient");
        }
    }, []);

    const singleAppointment = props.appointments.map((appointment) => {
        var name = '';
        if(props.flag === "byDoctor"){
            name = appointment.patient.name;
        }else{
            name = appointment.doctorName;
        }

        return(
            <SingleAppointmentBy appointment={appointment} onCancel={props.onCancel} pathId={props.match.params.id} cancelFlag={cancelFlag} name={name} key={appointment.id}/>
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
                        <th>{flag}</th>
                        <th>Specialization</th>
                        <th>Consultancy Fee</th>
                        <th>Appointment Date/Time</th>
                        <th>Appointment Creation Date</th>
                        <th>Current status</th>
                        <th>Action</th>
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

export default AppointmentList;
