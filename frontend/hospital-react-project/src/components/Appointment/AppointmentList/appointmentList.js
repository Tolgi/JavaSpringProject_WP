import React, {useEffect, useState} from 'react';
import {Divider} from "@material-ui/core";
import {MDBBtn, MDBDataTable, MDBIcon} from "mdbreact";

const AppointmentList = (props) => {

    const[flag, setFlag] = useState('');
    const[isDoctor, setDoctor] = useState(false);
    const [cancelFlag, setCancelFlag] = useState('');

    useEffect(() => {
        props.onClick(props.match.params.id)
        if(props.flag === "byDoctor"){
            setFlag("Patient name");
            setCancelFlag("Cancel by Doctor");
            setDoctor(true);
        }else {
            setFlag("Doctor name");
            setCancelFlag("Cancel by Patient");
           setDoctor(false);
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
                label: `${flag}`,
                field: 'flag',
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
            },
            {
                label: 'Action',
                field: 'action',
                sort: 'asc',
                width: 100
            }
        ],
        rows: [

            ...props.appointments.map((appointment) => ({
                    id: appointment.id,
                    flag: `${isDoctor ? appointment.doctorName : appointment.patient.name}`,
                    specialization: appointment.doctor.specialization.name,
                    fees: appointment.doctor.consultancyFees,
                    date: appointment.date + " / " +appointment.time,
                    creationDate:appointment.creationDate.replace('T', ' ').slice(0, 16),
                    status: appointment.status,
                    action:(
                        <div>
                            <button id="status" onClick={()=>props.onCancel(appointment.id, cancelFlag, props.match.params.id)} className="btn btn-primary a-btn-slide-text">
                                <span className="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                <span><strong>Cancel</strong></span>
                            </button>
                        </div>
                    ),

                })

            )
        ]
    };

    return (
        <div>
            <h4>YOUR APPOINTMENTS</h4>
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

export default AppointmentList;
