import React, {useEffect, useState} from 'react';
import AuthService from "../../authentication/axiosAuthRepository";
import {Link, useHistory} from "react-router-dom";

import "./signup.css";
import SpecializationService from "../../repository/axiosSpecializationRepository";

const SignUpDoctor = (props) => {


    const [user, setUser] = useState({});
    const [specializations, setSpecializations] = useState([]);
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

    useEffect(() => {
        loadSpecializations();
    }, []);

    const loadSpecializations = () => {
        SpecializationService.fetchSpecializations().then((response) =>{
            setSpecializations(response.data);
        })
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

    const options = specializations.map((specialization) => {
        return(
            <option key={specialization.id} value={specialization.id}>{specialization.name}</option>
        );
    });



    return(
        <div>
            <div id="success" className="signup-form" style={{display:"none",}}>
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

                    <div className="form-group">
                        <input onChange={handleTermOnChange} type="text" className="form-control" name="name"
                                                       placeholder="Name" required="required"/></div>
                    <div className="form-group">
                        <label>Select specialization</label>
                        <select name={"id"} className="form-control">
                            {options}
                        </select>
                    </div>
                    <div className="form-group">
                        <input type="text" onChange={handleTermOnChange} className="form-control" name="address"
                               placeholder="Address" required="required"/>
                    </div>

                    <div className="form-group">
                        <input type="number" onChange={handleTermOnChange} className="form-control" name="consultancyFees"
                               placeholder="Consultancy Fees" required="required"/>
                    </div>

                    <div className="form-group">
                        <input type="text" onChange={handleTermOnChange} className="form-control" name="contactNo"
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
                        <input type="password" onChange={handleTermOnChange} className="form-control" name="password" placeholder="Password"
                               required="required"/>
                    </div>
                    <label
                        style={{display:"none", color:"red"}}
                        id="error"
                        className="text-center"
                    >{message}</label>

                    <div className="form-group">
                        <label className="checkbox-inline"><input type="checkbox" required="required"/> I accept the <a
                            href="#">Terms of Use</a> &amp; <a href="#">Privacy Policy</a></label>
                    </div>
                    <div className="form-group">
                        <button type="submit" className="btn btn-success btn-lg btn-block">Register Now</button>
                    </div>
                </form>
                <div className="text-center">Already have an account? <Link to={"/login"}>Sign in</Link></div>
            </div>
        </div>
    );
};

export default SignUpDoctor;