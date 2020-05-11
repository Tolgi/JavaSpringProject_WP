import React, {useState} from 'react';
import SaveIcon from '@material-ui/icons/Save';
import Button from '@material-ui/core/Button';
import AuthService from "../../../authentication/axiosAuthRepository";
import {Divider} from "@material-ui/core";

const DoctorAdd = (props) => {

    const [newUser, setNewUser] = useState({});
    const[message, setMessage] = useState('');

    const onFormSubmit = (e) => {
        e.preventDefault();

        AuthService.registerDoctor({
            "name": e.target.name.value,
            "address": e.target.address.value,
            "consultancyFees": e.target.consultancyFees.value,
            "contactNo": e.target.contactNo.value,
            "specializationId": e.target.id.value,
            "username": e.target.username.value,
            "email": e.target.email.value,
            "password": e.target.password.value,
        }).then((response) => {

                const succ = response.data.message;
                setMessage(succ);
                showSuccessMessage();
            },
            error => {
                const resMessage =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();

                setMessage(resMessage);
                showMessage();

            });
    };

    const handleTermOnChange  = (e) => {
        const paramName = e.target.name;
        const paramValue = e.target.value;
        setNewUser({...newUser, [paramName]: paramValue});
    };

    const showMessage = () => {
        document.getElementById("error").style.display = "block";
    };

    const showSuccessMessage = () => {
        document.getElementById("form").style.display = "none";
        document.getElementById("success").style.display = "block";
    };




    const options = props.specializations.map((specialization) => {
        return(
            <option key={specialization.id} value={specialization.id}>{specialization.name}</option>
        );
    });

    return (

        <div>
            <h4>ADD NEW DOCTOR</h4>
            <Divider />
            <br/>
            <div className="card-body" >
                <div id="success" style={{background:"#1de9b6"}} className="signup-form" style={{display:"none",}}>
                    <div className={"alert alert-success"}
                         role="alert"
                    >
                        {message}
                    </div>

                </div>
                <div id="form" className="card-text">
                    <form onSubmit={onFormSubmit} id="editForm">
                        <div className="form-group col-md-8">
                            <label>Doctor name</label>
                            <input type="text"  onChange={handleTermOnChange} name={"name"} className="form-control" placeholder="Enter name" required="required"/>
                        </div>
                        <div className="form-group col-md-8">
                            <label>Select specialization</label>
                            <select  name={"id"} onChange={handleTermOnChange} className="form-control">
                                {options}
                            </select>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Clinic address</label>
                            <input type="text"  onChange={handleTermOnChange} name={"address"} className="form-control" placeholder="Enter address ..." required="required"/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Consultancy fees</label>
                            <input type="number"  onChange={handleTermOnChange} name={"consultancyFees"}  className="form-control" placeholder="Consultancy fees" required="required"/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Contact number</label>
                            <input type="text" onChange={handleTermOnChange} name={"contactNo"} className="form-control" placeholder="Contact number" required="required"/>
                        </div>

                        <p className="hint-text">Enter account info</p>

                        <div className="form-group  col-md-8">
                            <label>Username</label>
                            <input type="text" onChange={handleTermOnChange} name={"username"} className="form-control" placeholder="Username ..." required="required"/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Patient email</label>
                            <input type="email"  onChange={handleTermOnChange} name={"email"}  className="form-control" placeholder="Email ..." required="required"/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Password</label>
                            <input type="password"  min="8" onChange={handleTermOnChange} name={"password"}  className="form-control" placeholder="Password ..." required="required"/>
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

export default DoctorAdd;
