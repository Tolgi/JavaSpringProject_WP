import React from 'react';
import SingleDoctor from "../SingleDoctor/singleDoctor";
import { Divider } from '@material-ui/core';
import Button from "@material-ui/core/Button";
import AddIcon from '@material-ui/icons/Add';


const DoctorList = (props) => {

    const singleDoctor = props.doctors.map((doctor) => {
        return(
            <SingleDoctor onEdit={props.onEdit} onDelete={props.onDelete} doctor={doctor} key={doctor.id}/>
        );
    });


    return (
        <div>
            <h4>MANAGE DOCTOR</h4>
            <Divider />
            <br />
            <Button
                variant="contained"
                color="primary"
                startIcon={<AddIcon>add</AddIcon>}
                href={"/doctor/add"}
            >
                Add new doctor
            </Button>

            <div className="table-wrapper">
                <table className="fl-table">
                    <thead>
                    <tr>
                        <th>#ID</th>
                        <th>Name</th>
                        <th>Specialization</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    {singleDoctor}
                    </tbody>
                </table>
            </div>
        </div>

    );
}

export default DoctorList;
