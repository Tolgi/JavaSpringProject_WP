import React from 'react';
import SingleDoctor from "../SingleDoctor/singleDoctor";
import {Link} from "react-router-dom";

const DoctorList = (props) => {

    const singleDoctor = props.doctors.map((doctor) => {
        return(
            <SingleDoctor onEdit={props.onEdit} onDelete={props.onDelete} doctor={doctor} key={doctor.id}/>
        );
    });


    return (
        <div>
            <Link className="btn btn-primary btn-lg" to={"/doctor/add"}>Add new doctor</Link>

            <hr/>
            <br />
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
