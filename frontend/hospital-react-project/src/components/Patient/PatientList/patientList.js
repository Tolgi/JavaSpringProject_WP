import React from 'react';
import {Link} from "react-router-dom";
import SinglePatient from "../SinglePatient/singlePatient";

const PatientList = (props) => {

    const singlePatient = props.patients.map((patient) => {
        return(
            <SinglePatient onDetails={props.onDetails} onEdit={props.onEdit} onDelete={props.onDelete} patient={patient} key={patient.id}/>
        );
    });


    return (
        <div>
            <Link className="btn btn-primary btn-lg" to={"/patient/add"}>Add new patient</Link>

            <hr/>
            <br />
            <div className="table-wrapper">
                <table className="fl-table">
                    <thead>
                    <tr>
                        <th>#ID</th>
                        <th>Name</th>
                        <th>SSN</th>
                        <th>Contact number</th>
                        <th>Creation date</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    {singlePatient}
                    </tbody>
                </table>
            </div>
        </div>

    );
}

export default PatientList;
