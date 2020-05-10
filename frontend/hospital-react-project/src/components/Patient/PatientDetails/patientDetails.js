import React from 'react';
import MedicalHistory from "../../MedicalHistory/medicalHistory";
import {Link} from "react-router-dom";
import MedicalHistoryList from "../../MedicalHistory/MedicalHistoryList/medicalHistoryList";

import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import AddIcon from '@material-ui/icons/Add';

const PatientDetails = (props) => {
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
                    <Button variant="outlined" color="primary" href={`/medicalHistory/list/${props.patient.id}`}>
                        Show medical histories
                    </Button>
                    <Button variant="outlined"  startIcon={<AddIcon>add</AddIcon>} color="secondary" href={`/medicalHistory/add/${props.patient.id}`}>
                        Add medical histories
                    </Button>
                </div>

            </div>

        </div>

    );

};

export default PatientDetails;
