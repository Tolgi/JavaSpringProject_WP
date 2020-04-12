import React from 'react';

const DoctorAdd = (props) => {

    const onFormSubmit = (e) => {
        e.preventDefault();

        props.onAdd({
            "id": props.doctor.id,
            "name": e.target.name.value,
            "address": e.target.address.value,
            "consultancyFees": e.target.consultancyFees.value,
            "contactNo": e.target.contactNo.value,
            "email": e.target.email.value,
            "specializationId": e.target.id.value
        });
    };


    const options = props.specializations.map((specialization) => {
        return(
            <option key={specialization.id} value={specialization.id}>{specialization.name}</option>
        );
    });

    return (

        <div>
            <div className="card-body" >
                <div className="card-text">
                    <form onSubmit={onFormSubmit} id="editForm">
                        <div className="form-group col-md-8">
                            <label>Doctor name</label>
                            <input type="text" name={"name"} value={props.doctor.name} className="form-control" placeholder="Enter name"/>
                        </div>
                        <div className="form-group col-md-8">
                            <label>Select specialization</label>
                            <select  name={"id"} className="form-control">
                                {options}
                            </select>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Clinic address</label>
                            <input type="text"  name={"address"} value={props.doctor.address} className="form-control" placeholder="Enter address ..."/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Consultancy fees</label>
                            <input type="number" name={"consultancyFees"} value={props.doctor.consultancyFees} className="form-control" placeholder="Consultancy fees"/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Contact number</label>
                            <input type="text" name={"contactNo"} value={props.doctor.contactNo} className="form-control" placeholder="Contact number"/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Contact number</label>
                            <input type="text"  name={"email"} value={props.doctor.email} className="form-control" placeholder="Email..."/>
                        </div>
                        <button type="submit" id="editbtn" className="btn btn-primary  col-md-2 editButton">Add</button>
                    </form>
                </div>
            </div>

        </div>
    );

};

export default DoctorAdd;
