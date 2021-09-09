import React, {useState} from 'react';
import SaveIcon from '@material-ui/icons/Save';
import Button from '@material-ui/core/Button';
import {Divider} from "@material-ui/core";
import AuthService from "../../../authentication/axiosAuthRepository";

const PatientAdd = (props) => {

    const [newUser, setNewUser] = useState({});
    const[message, setMessage] = useState('');

    const onFormSubmit = (e) => {
        e.preventDefault();

        const currentUserId = AuthService.getCurrentUser().id;

        AuthService.registerPatient({
            "name": e.target.name.value,
            "ssn": e.target.ssn.value,
            "gender": e.target.gender.value,
            "address": e.target.address.value,
            "age": e.target.age.value,
            "contactNo": e.target.contactNo.value,
            "doctorId": currentUserId,
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



    return (

        <div>
            <h4>ADD NEW PATIENT</h4>
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
                <div  id="form" className="card-text">
                    <form onSubmit={onFormSubmit} id="editForm">
                        <div className="form-group col-sm-2">
                            <label>Patient name</label>
                            <input type="text"  onChange={handleTermOnChange} name={"name"} className="form-control" placeholder="Enter name" required="required"/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Patient SSN</label>
                            <input type="number"  onChange={handleTermOnChange} name={"ssn"} className="form-control" placeholder="Enter SSN " required="required"/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Contact number</label>
                            <input type="text"  onChange={handleTermOnChange} name={"contactNo"} className="form-control" placeholder="Contact number" required="required"/>
                        </div>
                        <div className="form-group col-md-8">
                            <label>Select Gender</label>
                            <select name={"gender"}  onChange={handleTermOnChange} className="form-control">
                                <option>Male</option>
                                <option>Female</option>
                            </select>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Patient Address</label>
                            <input type="text"  onChange={handleTermOnChange}  name={"address"} className="form-control" placeholder="Enter address ..." required="required"/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Patient Age</label>
                            <input type="number"  onChange={handleTermOnChange} name={"age"}  className="form-control" placeholder="Enter age ..." required="required"/>
                        </div>
                        <Divider />
                        <br/>
                        <p className="hint-text">Enter account info</p>

                        <div className="form-group  col-md-8">
                            <label>Username</label>
                            <input type="text"  onChange={handleTermOnChange} name={"username"} className="form-control" placeholder="Username ..." required="required"/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Patient email</label>
                            <input type="email"  onChange={handleTermOnChange} name={"email"}  className="form-control" placeholder="Email ..." required="required"/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Password</label>
                            <input type="password"  onChange={handleTermOnChange} name={"password"}  className="form-control" placeholder="Password ..." required="required"/>
                        </div>
                        <label
                            style={{display:"none", color:"red"}}
                            id="error"
                        >{message}</label>
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
