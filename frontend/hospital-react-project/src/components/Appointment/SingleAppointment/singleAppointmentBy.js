import React  from 'react';
import { Link } from 'react-router-dom';



const SingleAppointmentBy = (props) => {

    return (
        <tr key={props.appointment.id}>
            <td>{props.appointment.id}</td>
            <td>{props.name}</td>
            <td>{props.appointment.doctor.specialization.name}</td>
            <td>{props.appointment.doctor.consultancyFees}</td>
            <td>{props.appointment.date} / {props.appointment.time}</td>
            <td>{props.appointment.creationDate}</td>
            <td>{props.appointment.status}</td>
            <td>
                <button onClick={()=>props.onCancel(props.appointment.id, props.cancelFlag, props.pathId)} className="btn btn-primary a-btn-slide-text">
                    <span className="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    <span><strong>Cancel</strong></span>
                </button>

            </td>
        </tr>
    );
}

export default SingleAppointmentBy;