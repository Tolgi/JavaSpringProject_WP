import React, {useEffect} from 'react';
import MedicalHistory from "../../MedicalHistory/medicalHistory";
import {Link} from "react-router-dom";
import MedicalHistoryList from "../../MedicalHistory/MedicalHistoryList/medicalHistoryList";

import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import AddIcon from '@material-ui/icons/Add';
import {Divider} from "@material-ui/core";

const DoctorDetails = (props) => {


    useEffect(() => {
        props.onLoad(props.match.params.id);
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

            <h4>DOCTOR | DOCTOR DETAILS</h4>
            <Divider />
            <br/>
            <div className="table100 ver1 m-b-110">
                <table data-vertable="ver1" className="table table-sm table-hover  table-active">
                    <tbody>

                    <tr>
                        <th>#ID</th>
                        <td>{props.doctor.id}</td>
                        <th>Name</th>
                        <td>{props.doctor.name}</td>
                    </tr>

                    <tr>
                        <th>Address</th>
                        <td>{props.doctor.address}</td>
                        <th>Consultancy Fees</th>
                        <td>{props.doctor.consultancyFees}</td>
                    </tr>
                    <tr>
                        <th>Email</th>
                        <td>{props.doctor.email}</td>
                        <th>Contact number</th>
                        <td>{props.doctor.contactNo}</td>
                    </tr>
                    </tbody>
                </table>
                <br/>

            </div>

        </div>

    );

};

export default DoctorDetails;
