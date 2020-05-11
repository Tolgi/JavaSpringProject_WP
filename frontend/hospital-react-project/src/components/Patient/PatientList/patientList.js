import React, {useEffect, useState} from 'react';
import SinglePatient from "../SinglePatient/singlePatient";
import {Divider} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import AddIcon from '@material-ui/icons/Add';
import AuthService from "../../../authentication/axiosAuthRepository";

const PatientList = (props) => {



    const [doctorRole, setDoctorRole] = useState(false);

    useEffect(() => {
        const currentUser = AuthService.getCurrentUser();
        if(currentUser){
            setDoctorRole(currentUser.roles.includes("ROLE_DOCTOR"));
        }
    }, []);


    const singlePatient = props.patients.map((patient) => {
        return(
            <SinglePatient onDetails={props.onDetails} onEdit={props.onEdit} onDelete={props.onDelete} patient={patient} key={patient.id}/>
        );
    });


    return (
        <div>
            <h4>MANAGE PATIENT</h4>
            <Divider />
            <br />
            {doctorRole &&
                <Button
                    variant="contained"
                    color="primary"
                    startIcon={<AddIcon>add</AddIcon>}
                    href={"/dashboard/patient/add"}
                >
                    Add new patient
                </Button>
            }

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
