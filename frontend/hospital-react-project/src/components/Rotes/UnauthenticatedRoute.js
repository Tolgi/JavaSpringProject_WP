import React from 'react';
import { Redirect, Route } from 'react-router-dom';
import AuthService from "../../authentication/axiosAuthRepository";


const RouteUnauthenticated = (props) => {
    if (AuthService.isAuthenticated()) {
        return <Redirect to="/dashboard/home" />;
    }

    return <Route component={props.component} path={props.path} />;
};

export default RouteUnauthenticated;