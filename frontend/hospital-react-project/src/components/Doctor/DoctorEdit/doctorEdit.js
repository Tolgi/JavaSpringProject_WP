import React, {useEffect} from 'react';
import Button from '@material-ui/core/Button';
import EditIcon from '@material-ui/icons/Edit';
import {Divider} from "@material-ui/core";

const DoctorEdit = (props) => {

    useEffect(() => {
        props.onLoad(props.match.params.id);
    }, []);

    const onFormSubmit = (e) => {
        e.preventDefault();

        props.onEdit({
            "id": props.doctor.id,
            "name": e.target.name.value,
            "address": e.target.address.value,
            "consultancyFees": e.target.consultancyFees.value,
            "contactNo": e.target.contactNo.value,
            "email": e.target.email.value,
            "specializationId": e.target.id.value
        });
    };

    const handleTermOnChange  = (e) => {
        const paramName = e.target.name;
        const paramValue = e.target.value;
        props.setDoctor({...props.doctor, [paramName]: paramValue});
    };


    const handleSelectOnChange  = (e) => {
        const paramName = "specialization";
        const paramValue = e.target.value;
        const selectedSpecialization = props.specializations.reduce((result, specialization) => {
            if(specialization.id === parseInt(paramValue)){
                result.push(specialization);
            }
            return result;
        }, []);

        props.setDoctor({...props.doctor, [paramName]: selectedSpecialization[0]});
    };



    const options = props.specializations.map((specialization) => {
        return(
            <option key={specialization.id} value={specialization.id}>{specialization.name}</option>
        );
    });


    return (

        <div>
            <h4>EDIT DOCTOR DETAILS</h4>
            <Divider />
            <div className="card-body" >
                <div className="card-text">
                    <form onSubmit={onFormSubmit} id="editForm">
                        <div className="form-group col-md-8">
                            <label>Doctor name</label>
                            <input type="text" onChange={handleTermOnChange} name={"name"} value={props.doctor.name} className="form-control" placeholder="Enter name"/>
                        </div>
                        <div className="form-group col-md-8">
                            <label>Select specialization</label>
                            <select  onChange={handleSelectOnChange} name={"id"} className="form-control">
                                {options}
                            </select>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Clinic address</label>
                            <input type="text" onChange={handleTermOnChange} name={"address"} value={props.doctor.address} className="form-control" placeholder="Enter address ..."/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Consultancy fees</label>
                            <input type="number" onChange={handleTermOnChange} name={"consultancyFees"} value={props.doctor.consultancyFees} className="form-control" placeholder="Consultancy fees"/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Contact number</label>
                            <input type="text" onChange={handleTermOnChange} name={"contactNo"} value={props.doctor.contactNo} className="form-control" placeholder="Contact number"/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Contact number</label>
                            <input type="text" onChange={handleTermOnChange} name={"email"} value={props.doctor.email} className="form-control" placeholder="Email..."/>
                        </div>
                        <Button
                            variant="contained"
                            color="primary"
                            startIcon={<EditIcon>edit</EditIcon>}
                            type="submit"
                        >
                            Edit
                        </Button>
                    </form>
                </div>
            </div>

        </div>
    );
}

export default DoctorEdit;
