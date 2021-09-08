import React, {useEffect, useState} from 'react';
import {Divider} from "@material-ui/core";
import {MDBBtn, MDBDataTable, MDBIcon} from "mdbreact";
import TermService from "../../../repository/axiosTermRepository";



const TermsList = (props) => {

   const[terms, setTerms] = useState([]);

    useEffect(() => {

        if(props.flag === "byDoctor") {
            props.onClick(props.match.params.id);
        }
        else{
            props.onClick(props.match.params.id, props.match.params.status);
        }

        setTerms(props.terms);
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
                label: 'Date',
                field: 'date',
                sort: 'asc',
                width: 270
            },
            {
                label: 'Time of Admission',
                field: 'time',
                sort: 'asc',
                width: 200
            },
            {
                label: 'Status',
                field: 'status',
                sort: 'asc',
                width: 270
            },
            {
                label: 'Action',
                field: 'action',
                sort: 'asc',
                width: 100
            }
        ],
        rows: [

            ...props.terms.map((term) => ({
                    id: term.id,
                    date: term.date,
                    time: term.timeOfAdmission,
                    status: term.status,
                    action:(
                        <div>
                            <MDBBtn  onClick={() => { if (window.confirm('Are you sure you wish to delete this term?')) props.onDelete(term.id, props.match.params.id) }} color="danger" size="sm"><MDBIcon icon="trash" className="mr-1" />Delete</MDBBtn>
                        </div>
                    ),

                })

            )
        ]
    };

    return (
        <div>
            <h4>MANAGE YOUR TERMS</h4>
            <Divider />
            <br />

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

export default TermsList;
