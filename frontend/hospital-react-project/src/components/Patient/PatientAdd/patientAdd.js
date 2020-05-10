import React, {useState} from 'react';
import SaveIcon from '@material-ui/icons/Save';
import Button from '@material-ui/core/Button';


const PatientAdd = (props) => {


    const onFormSubmit = (e) => {
        e.preventDefault();

        props.onAdd({
            "name": e.target.name.value,
            "ssn": e.target.ssn.value,
            "gender": e.target.gender.value,
            "email": e.target.email.value,
            "address": e.target.address.value,
            "age": e.target.age.value,
            "contactNo": e.target.contactNo.value,
            "doctorId": 1 //vo idnina ke bide dinamicno
        });
    };


    return (

        <div>
            <div className="card-body" >
                <div className="card-text">
                    <form onSubmit={onFormSubmit} id="editForm">
                        <div className="form-group col-sm-2">
                            <label>Patient name</label>
                            <input type="text" name={"name"} className="form-control" placeholder="Enter name"/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Patient SSN</label>
                            <input type="text" name={"ssn"} className="form-control" placeholder="Enter SSN "/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Contact number</label>
                            <input type="text" name={"contactNo"} className="form-control" placeholder="Contact number"/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Patient email</label>
                            <input type="text"  name={"email"}  className="form-control" placeholder="Email..."/>
                        </div>
                        <div className="form-group col-md-8">
                            <label>Select Gender</label>
                            <select name={"gender"} className="form-control">
                                <option>Male</option>
                                <option>Female</option>
                            </select>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Patient Address</label>
                            <input type="text"  name={"address"} className="form-control" placeholder="Enter address ..."/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Patient Age</label>
                            <input type="number" name={"age"}  className="form-control" placeholder="Enter age ..."/>
                        </div>

                        <Button
                            variant="contained"
                            color="primary"
                            size="small"
                            type="submit"
                            startIcon={<SaveIcon />}
                        >
                            Save
                        </Button>
                    </form>
                </div>
            </div>

        </div>
    );

};

export default PatientAdd;
