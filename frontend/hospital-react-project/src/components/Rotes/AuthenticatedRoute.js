import React from 'react';
import { Redirect, Route } from 'react-router-dom';
import AuthService from "../../authentication/axiosAuthRepository";



const RouteAuthenticated = (props) => {
    if (!AuthService.isAuthenticated()) {
        return <Redirect to="/login" />;
    }

    return <Route forceRefresh={true} component={props.component} path={props.path} />;
};

export default RouteAuthenticated;