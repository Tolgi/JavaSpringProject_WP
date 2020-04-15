import React from 'react';
import MedicalHistory from "../../MedicalHistory/medicalHistory";
import {Link} from "react-router-dom";
import MedicalHistoryList from "../../MedicalHistory/MedicalHistoryList/medicalHistoryList";

const PatientDetails = (props) => {


    return (
        <div>
            <div className="table100 ver1 m-b-110">
                <table data-vertable="ver1" className="table table-sm table-hover  table-active">
                    <tbody>

                    <tr>
                        <th>#ID</th>
                        <td>{props.patient.id}</td>
                        <th>Name</th>
                        <td>{props.patient.name}</td>
                    </tr>

                    <tr>
                        <th>SSN</th>
                        <td>{props.patient.ssn}</td>
                        <th>Gender</th>
                        <td>{props.patient.gender}</td>
                    </tr>
                    <tr>
                        <th>Age</th>
                        <td>{props.patient.age}</td>
                        <th>Email</th>
                        <td>{props.patient.email}</td>
                    </tr>
                    <tr>
                        <th>Contact number</th>
                        <td>{props.patient.contactNo}</td>
                        <th>Creation date</th>
                        <td>{props.patient.creationDate}</td>
                    </tr>
                    </tbody>
                </table>
                <br/>
                <br/>
                <Link to={`/medicalHistory/list/${props.patient.id}`} className="btn btn-primary a-btn-slide-text">
                    <span className="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    <span><strong>Show medical histories</strong></span>
                </Link>
                <br/>
                <br/>
                <Link to={`/medicalHistory/add/${props.patient.id}`} className="btn btn-primary a-btn-slide-text">
                    <span className="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    <span><strong>Add medical histories</strong></span>
                </Link>

            </div>

        </div>

    );

};

export default PatientDetails;
