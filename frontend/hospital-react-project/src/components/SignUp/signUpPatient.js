import React, {useState} from 'react';
import AuthService from "../../authentication/axiosAuthRepository";
import {Link, useHistory} from "react-router-dom";

import "./signup.css";

const SignUpPatient = (props) => {


    const [user, setUser] = useState({});
    const[message, setMessage] = useState('');

    const onFormSubmit = (e) => {
        e.preventDefault();

        AuthService.registerPatient({
            "name": e.target.name.value,
            "ssn": e.target.ssn.value,
            "gender": e.target.gender.value,
            "address": e.target.address.value,
            "age": e.target.age.value,
            "contactNo": e.target.contactNo.value,
            "doctorId": null, //vo idnina ke bide dinamicno
            "username": e.target.username.value,
            "email": e.target.email.value,
            "password": e.target.password.value,
        }).then((response) => {

                const succ = response.data.message;
                console.log(response.data.message);
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
        setUser({...user, [paramName]: paramValue});
    };

    const showMessage = () => {
        document.getElementById("error").style.display = "block";
    };

    const showSuccessMessage = () => {
        document.getElementById("form").style.display = "none";
        document.getElementById("success").style.display = "block";
    };


    return(
        <div>
            <div id="success" style={{background:"#1de9b6"}} className="signup-form" style={{display:"none",}}>
                <div className={"alert alert-success"}
                     role="alert"
                >
                    {message}
                </div>
                <div  className="text-center">Want to sign in now?  <a href="/login">Sign in</a></div>

            </div>
            <div id="form" className="signup-form">
                <form  onSubmit={onFormSubmit}>
                    <h2>Register</h2>
                    <p className="hint-text">To create your account, please enter your personal info</p>

                        <div className="form-group"><input onChange={handleTermOnChange} type="text" className="form-control" name="name"
                                                             placeholder="Name" required="required"/></div>
                        <div className="form-group"><input onChange={handleTermOnChange} type="number" min="13" max="13" className="form-control" name="ssn"
                                                             placeholder="SSN" required="required"/></div>
                    <div  className="form-group">
                        <label>Select Gender</label>
                        <select name={"gender"} onChange={handleTermOnChange} className="form-control">
                            <option>Male</option>
                            <option>Female</option>
                        </select>
                    </div>
                    <div className="form-group">
                        <input type="text" onChange={handleTermOnChange} className="form-control" name="address"
                                                       placeholder="Address" required="required"/>
                    </div>
                    <div className="form-group">
                        <input type="number" onChange={handleTermOnChange} className="form-control" name="age"
                               placeholder="Age" required="required"/>
                    </div>
                    <div className="form-group">
                        <input type="number" onChange={handleTermOnChange} className="form-control" name="contactNo"
                               placeholder="Contact No." required="required"/>
                    </div>
                    <p className="hint-text">Enter your account info</p>
                    <div className="form-group">
                        <input type="text"  onChange={handleTermOnChange} className="form-control" name="username"
                               placeholder="Username" required="required"/>
                    </div>
                    <div className="form-group">
                        <input type="email" onChange={handleTermOnChange} className="form-control" name="email" placeholder="Email" required="required"/>
                    </div>
                    <div className="form-group">
                        <input type="password"  min="8" onChange={handleTermOnChange} className="form-control" name="password" placeholder="Password"
                               required="required"/>
                    </div>
                    <label
                        style={{display:"none", color:"red"}}
                        id="error"
                    >{message}</label>

                    <div className="form-group">
                        <label className="checkbox-inline"><input type="checkbox" required="required"/> I accept the <a
                            href="#">Terms of Use</a> &amp; <a href="#">Privacy Policy</a></label>
                    </div>
                    <div className="form-group">
                        <button type="submit" className="btn btn-success btn-lg btn-block">Register Now</button>
                    </div>
                </form>
                <div  className="text-center">Already have an account? <Link to={"/login"}>Sign in</Link></div>
            </div>
        </div>
    );
};

export default SignUpPatient;