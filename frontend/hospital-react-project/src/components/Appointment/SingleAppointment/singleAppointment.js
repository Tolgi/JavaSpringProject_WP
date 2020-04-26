import React  from 'react';

const SingleAppointment = (props) => {

    return (
        <tr key={props.appointment.id}>
            <td>{props.appointment.id}</td>
            <td>{props.appointment.doctorName}</td>
            <td>{props.appointment.patient.name}</td>
            <td>{props.appointment.consultancyFees}</td>
            <td>{props.appointment.date} / {props.appointment.time}</td>
            <td>{props.appointment.creationDate}</td>
            <td>{props.appointment.status}</td>

        </tr>
    );
}

export default SingleAppointment;
