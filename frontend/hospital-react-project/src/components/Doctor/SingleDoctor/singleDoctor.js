import React  from 'react';
import { Link } from 'react-router-dom';



const SingleDoctor = (props) => {

    return (
        <tr key={props.doctor.id}>
            <td>{props.doctor.id}</td>
            <td>{props.doctor.name}</td>
            <td>{props.doctor.specialization.name}</td>
            <td>
                <button onClick={() => props.onEdit(props.doctor.id)} className="btn btn-primary a-btn-slide-text">
                    <span className="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    <span><strong>Edit</strong></span>
                </button>


                <button onClick={()=>props.onDelete(props.doctor.id)} className="btn btn-primary a-btn-slide-text">
                    <span className="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    <span><strong>Delete</strong></span>
                </button>

            </td>
        </tr>
    );
}

export default SingleDoctor;
