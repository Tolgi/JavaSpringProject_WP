import React, {useEffect, useState} from 'react';
import {Divider} from "@material-ui/core";
import {MDBDataTable} from "mdbreact";


const AllAppointmentList = (props) => {

    const data = {
        columns: [
            {
                label: 'ID',
                field: 'id',
                sort: 'asc',
                width: 150
            },
            {
                label: 'Doctor Name',
                field: 'doctorName',
                sort: 'asc',
                width: 270
            },
            {
                label: 'Patient Name',
                field: 'patientName',
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
                label: 'Consultancy Fee',
                field: 'fees',
                sort: 'asc',
                width: 200
            },
            {
                label: 'Appointment Date/Time',
                field: 'date',
                sort: 'asc',
                width: 200
            },
            {
                label: 'Appointment Creation Date',
                field: 'creationDate',
                sort: 'asc',
                width: 200
            },
            {
                label: 'Current status',
                field: 'status',
                sort: 'asc',
                width: 200
            }

        ],
        rows: [

            ...props.appointments.map((appointment) => ({
                    id: appointment.id,
                    doctorName: appointment.doctorName,
                    patientName: appointment.patient.name,
                    specialization: appointment.doctor.specialization.name,
                    fees: appointment.doctor.consultancyFees,
                    date: appointment.date + " / " +appointment.time,
                    creationDate:appointment.creationDate.replace('T', ' ').slice(0, 16),
                    status: appointment.status,
                })

            )
        ]
    };


    return (
        <div>
            <h4>APPOINTMENTS</h4>
            <Divider />
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

export default AllAppointmentList;
