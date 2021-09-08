import React from 'react';
import { Divider } from '@material-ui/core';
import Button from "@material-ui/core/Button";
import AddIcon from '@material-ui/icons/Add';
import {MDBBtn, MDBDataTable, MDBIcon} from 'mdbreact';

const DoctorList = (props) => {

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
                label: 'Specialization',
                field: 'specialization',
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

            ...props.doctors.map((doctor) => ({
                    id: doctor.id,
                    name: doctor.name,
                specialization: doctor.specialization.name,
                    action:(
                        <div>
                            <MDBBtn id={doctor.id}  onClick={() => props.onEdit(doctor.id)} color="info" outline size="sm"><MDBIcon icon="magic" className="mr-1" />Edit</MDBBtn>
                            <MDBBtn  onClick={() => {if (window.confirm('Are you sure you want to delete this doctor?')) props.onDelete(doctor.id)}} color="danger" size="sm"><MDBIcon icon="trash" className="mr-1" />Delete</MDBBtn>
                        </div>
                    ),

                })

            )
        ]
    };



    return (
        <div>
            <h4>MANAGE DOCTOR</h4>
            <Divider />
            <br />
            <Button
                variant="contained"
                color="primary"
                startIcon={<AddIcon>add</AddIcon>}
                href={"/dashboard/doctor/add"}
            >
                Add new doctor
            </Button>

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

export default DoctorList;
