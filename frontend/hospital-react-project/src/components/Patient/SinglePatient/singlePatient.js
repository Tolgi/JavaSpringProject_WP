import React  from 'react';
import { Link } from 'react-router-dom';



const SinglePatient = (props) => {

    return (
        <tr key={props.patient.id}>
            <td>{props.patient.id}</td>
            <td>{props.patient.name}</td>
            <td>{props.patient.ssn}</td>
            <td>{props.patient.contactNo}</td>
            <td>{props.patient.creationDate}</td>
            <td>
                <button onClick={() => props.onEdit(props.patient.id, "edit")} className="btn btn-primary a-btn-slide-text">
                    <span className="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    <span><strong>Edit</strong></span>
                </button>


                <button onClick={()=>props.onDelete(props.patient.id)} className="btn btn-primary a-btn-slide-text">
                    <span className="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    <span><strong>Delete</strong></span>
                </button>

                <button onClick={()=>props.onDetails(props.patient.id, "details")} className="btn btn-primary a-btn-slide-text">
                    <span className="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    <span><strong>Details</strong></span>
                </button>

            </td>
        </tr>
    );
}

export default SinglePatient;
