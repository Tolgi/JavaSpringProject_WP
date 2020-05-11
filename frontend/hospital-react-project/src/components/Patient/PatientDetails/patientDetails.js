import React, {useEffect, useState} from 'react';
import MedicalHistory from "../../MedicalHistory/medicalHistory";
import {Link} from "react-router-dom";
import MedicalHistoryList from "../../MedicalHistory/MedicalHistoryList/medicalHistoryList";

import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import AddIcon from '@material-ui/icons/Add';
import {Divider} from "@material-ui/core";
import AuthService from "../../../authentication/axiosAuthRepository";
import EditIcon from '@material-ui/icons/Edit';

const PatientDetails = (props) => {

    const [doctorRole, setDoctorRole] = useState(false);
    const [userRole, setUserRole] = useState(false);

    useEffect(() => {
        props.onLoad(props.match.params.id);
        const currentUser = AuthService.getCurrentUser();
        if(currentUser){
            setDoctorRole(currentUser.roles.includes("ROLE_DOCTOR"));
            setUserRole(currentUser.roles.includes("ROLE_USER"));
        }
    }, []);



    const useStyles = makeStyles((theme) => ({
        root: {
            '& > *': {
                margin: theme.spacing(1),
            },
        },
    }));


    const classes = useStyles();

    return (
        <div>
            <h4>PATIENT DETAILS</h4>
            <Divider />
            <br/>
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

                <div className={classes.root}>
                    <Button variant="outlined" color="primary" href={`/dashboard/medicalHistory/list/${props.patient.id}`}>
                        Show medical histories
                    </Button>
                    {doctorRole &&
                    <Button variant="outlined" startIcon={<AddIcon>add</AddIcon>} color="secondary"
                            href={`/dashboard/medicalHistory/add/${props.patient.id}`}>
                        Add medical histories
                    </Button>
                    }

                    {userRole &&
                    <Button variant="outlined" startIcon={<EditIcon>Edit</EditIcon>} color="inherit"
                            href={`/dashboard/patient/edit/${props.patient.id}`}>
                       Edit profile
                    </Button>
                    }
                </div>

            </div>

        </div>

    );

};

export default PatientDetails;
