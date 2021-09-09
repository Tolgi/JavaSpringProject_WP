import React, {useEffect, useState} from 'react';
import {Divider} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import AddIcon from '@material-ui/icons/Add';
import AuthService from "../../../authentication/axiosAuthRepository";
import {MDBBtn, MDBDataTable, MDBIcon} from "mdbreact";
import EditIcon from "@material-ui/icons/Edit";
import DeleteIcon from "@material-ui/icons/Delete";
import DetailsIcon from "@material-ui/icons/Details";

const PatientList = (props) => {

    const [user, setUser] = useState({});
    const [userRole, setUserRole] = useState(false);
    const [adminRole, setAdminRole] = useState(false);
    const [doctorRole, setDoctorRole] = useState(false);

    useEffect(() => {
        const currentUser = AuthService.getCurrentUser();

        if(currentUser){
            setUserRole(currentUser.roles.includes("ROLE_USER"));
            setAdminRole(currentUser.roles.includes("ROLE_ADMIN"));
            setDoctorRole(currentUser.roles.includes("ROLE_DOCTOR"));
            setUser(currentUser);
        }
    }, []);


    const data = {
        columns: [
            {
                label: 'ID',
                field: 'id',
                sort: 'asc',
                width: 150
            },
            {
                label: 'Name',
                field: 'name',
                sort: 'asc',
                width: 270
            },
            {
                label: 'SSN',
                field: 'ssn',
                sort: 'asc',
                width: 200
            },
            {
                label: 'Contact number',
                field: 'contactNo',
                sort: 'asc',
                width: 200
            },
            {
                label: 'Creation date',
                field: 'date',
                sort: 'asc',
                width: 200
            },
            {
                label: 'Action',
                field: 'action',
                sort: 'asc',
                width: 100
            }
        ],
        rows: [

            ...props.patients.map((patient) => ({
                    id: patient.id,
                    name: patient.name,
                ssn: patient.ssn,
                contactNo: patient.contactNo,
                date: patient.creationDate.replace('T', ' ').slice(0, 16),
                    action:(
                        <div>
                            {adminRole &&
                            <Button
                                variant="contained"
                                color="primary"
                                size="small"
                                startIcon={<EditIcon>edit</EditIcon>}
                                onClick={() => props.onEdit(patient.id, "edit")}
                            >
                                Edit
                            </Button>

                            }
                            {adminRole &&
                            <Button
                                variant="contained"
                                color="secondary"
                                size="small"
                                startIcon={<DeleteIcon />}
                                onClick={() => {if (window.confirm('Are you sure you want to delete this patient?')) props.onDelete(patient.id)}}
                            >
                                Delete
                            </Button>
                            }

                            {adminRole || doctorRole &&
                            <Button
                                variant="contained"
                                color="primary"
                                size="small"
                                startIcon={<DetailsIcon>details</DetailsIcon>}
                                onClick={() => props.onDetails(patient.id, "details")}
                            >
                                Details
                            </Button>
                            }

                            {doctorRole &&
                            <Button variant="contained" size="small" startIcon={<AddIcon>add</AddIcon>} color="secondary" href={`/dashboard/medicalHistory/add/${patient.id}`}>
                                Add medical histories
                            </Button>
                            }

                            {doctorRole &&
                            <Button variant="contained" size="small" color="primary" href={`/dashboard/medicalHistory/list/${patient.id}`}>
                                Show histories
                            </Button>
                            }
                        </div>
                    ),

                })

            )
        ]
    };



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
                <MDBDataTable
                    responsive
                    striped
                    hover
                    data={data}
                />
            </div>
        </div>

    );
}

export default PatientList;
