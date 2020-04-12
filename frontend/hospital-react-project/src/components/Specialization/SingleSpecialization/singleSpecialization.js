import React from 'react';
import { Link } from 'react-router-dom';
import '../specialization.css';


const SingleSpecialization = (props) => {

    return (
        <tr key={props.specialization.id}>
            <td>{props.specialization.id}</td>
            <td>{props.specialization.name}</td>
            <td>{props.specialization.creationTime}</td>
            <td>
                <Link to={`/specialization/edit/${props.specialization.id}`}>
                    <button className="speciButton btn btn-primary a-btn-slide-text">
                        <span className="glyphicon glyphicon-remove" aria-hidden="true"></span>
                        <span><strong>Edit</strong></span>
                    </button>
                </Link>

                <button onClick={()=>props.onDelete(props.specialization.id)} className="speciButton btn btn-primary a-btn-slide-text">
                        <span className="glyphicon glyphicon-remove" aria-hidden="true"></span>
                        <span><strong>Delete</strong></span>
                </button>

            </td>
        </tr>
    );
}

export default SingleSpecialization;
