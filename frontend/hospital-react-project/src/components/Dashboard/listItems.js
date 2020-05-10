import React, {useEffect, useState} from 'react';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import ListSubheader from '@material-ui/core/ListSubheader';
import DashboardIcon from '@material-ui/icons/Dashboard';
import PeopleIcon from '@material-ui/icons/People';
import AssignmentIcon from '@material-ui/icons/Assignment';
import {Link} from "react-router-dom";
import AuthService from "../../authentication/axiosAuthRepository";
import BorderColorIcon from '@material-ui/icons/BorderColor';
import DescriptionIcon from '@material-ui/icons/Description';
import HomeIcon from '@material-ui/icons/Home';

export const MainListItems = () => {

    const [user, setUser] = useState({});
    const [userRole, setUserRole] = useState(false);
    const [adminRole, setAdminRole] = useState(false);
    const [doctorRole, setDoctorRole] = useState(false);

    useEffect(() => {
        const currentUser = AuthService.getCurrentUser();

        if(currentUser){
            setUserRole(currentUser.roles.includes("ROLE_USER"));
            setAdminRole(currentUser.roles.includes("ROLE_ADMIN"));
            setDoctorRole(currentUser.roles.includes("ROLE_DOCTOR"));
            setUser(currentUser);
        }
    }, []);

    return(
        <div>
            <ListSubheader inset>MAIN NAVIGATION</ListSubheader>
            {adminRole &&
            <ListItem button component={Link} to="/dashboard/adminHome">
                <ListItemIcon>
                    <HomeIcon/>
                </ListItemIcon>
                <ListItemText primary="Dashboard"/>
            </ListItem>
            }

            {doctorRole &&
            <ListItem button component={Link} to="/dashboard/doctorHome">
                <ListItemIcon>
                    <HomeIcon/>
                </ListItemIcon>
                <ListItemText primary="Dashboard"/>
            </ListItem>
            }

            {adminRole &&
            <ListItem button component={Link} to="/dashboard/doctor/list">
                <ListItemIcon>
                    <PeopleIcon/>
                </ListItemIcon>
                <ListItemText primary="Doctors"/>
            </ListItem>
            }

            {adminRole || doctorRole &&
            <ListItem button component={Link} to="/dashboard/patient/list">
                <ListItemIcon>
                    <PeopleIcon/>
                </ListItemIcon>
                <ListItemText primary="Patients"/>
            </ListItem>
            }

            {adminRole &&
            <ListItem button>
                <ListItemIcon>
                    <PeopleIcon/>
                </ListItemIcon>
                <ListItemText primary="Users"/>
            </ListItem>
            }

            {userRole &&
            <ListItem button component={Link} to="/dashboard/appointment/add">
                <ListItemIcon>
                    <BorderColorIcon/>
                </ListItemIcon>
                <ListItemText primary="Book Appointment"/>
            </ListItem>
            }

            {userRole &&
            <ListItem button component={Link} to="/dashboard/appointment/list">
                <ListItemIcon>
                    <DescriptionIcon/>
                </ListItemIcon>
                <ListItemText primary="Appointment History"/>
            </ListItem>
            }

            {doctorRole &&
            <ListItem button component={Link} to={`/dashboard/appointment/doctor/${user.id}`}>
                <ListItemIcon>
                    <DescriptionIcon/>
                </ListItemIcon>
                <ListItemText primary="Appointment History"/>
            </ListItem>
            }

            {userRole &&
            <ListItem button component={Link} to="/dashboard/medicalHistory/list">
                <ListItemIcon>
                    <DescriptionIcon/>
                </ListItemIcon>
                <ListItemText primary="Medical History"/>
            </ListItem>
            }

        </div>
    );
};

export const secondaryListItems = (
    <div>
        <ListSubheader inset>Logs / reports</ListSubheader>
        <ListItem button>
            <ListItemIcon>
                <AssignmentIcon />
            </ListItemIcon>
            <ListItemText primary="Doctor Session Logs" />
        </ListItem>
        <ListItem button>
            <ListItemIcon>
                <AssignmentIcon />
            </ListItemIcon>
            <ListItemText primary="User Session Logs" />
        </ListItem>
        <ListItem button>
            <ListItemIcon>
                <AssignmentIcon />
            </ListItemIcon>
            <ListItemText primary="Reports" />
        </ListItem>
    </div>
);