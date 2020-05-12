import React, {useEffect, useState} from 'react';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import ListSubheader from '@material-ui/core/ListSubheader';
import PeopleIcon from '@material-ui/icons/People';
import AssignmentIcon from '@material-ui/icons/Assignment';
import {Link} from "react-router-dom";
import AuthService from "../../authentication/axiosAuthRepository";
import BorderColorIcon from '@material-ui/icons/BorderColor';
import DescriptionIcon from '@material-ui/icons/Description';
import HomeIcon from '@material-ui/icons/Home';
import ScheduleIcon from '@material-ui/icons/Schedule';
import AddAlarmIcon from '@material-ui/icons/AddAlarm';
import EventNoteIcon from '@material-ui/icons/EventNote';
import AccountBoxIcon from '@material-ui/icons/AccountBox';
import RecentActorsIcon from '@material-ui/icons/RecentActors';

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

{/*/---------------------------DOCTOR ROLE LIST ITEMS --------------------------------/*/}
            {doctorRole &&
            <ListItem button component={Link} to="/dashboard/home">
                <ListItemIcon>
                    <HomeIcon/>
                </ListItemIcon>
                <ListItemText primary="Dashboard"/>
            </ListItem>
            }

            {doctorRole &&
            <ListItem button component={Link} to={`/dashboard/doctor/details/${user.id}`}>
                <ListItemIcon>
                    <AccountBoxIcon/>
                </ListItemIcon>
                <ListItemText primary="View Profile"/>
            </ListItem>
            }

            {doctorRole &&
            <ListItem button component={Link} to={`/dashboard/patient/list/doctor/${user.id}`}>
                <ListItemIcon>
                    <PeopleIcon/>
                </ListItemIcon>
                <ListItemText primary="Patients"/>
            </ListItem>
            }

            {doctorRole &&
            <ListItem button component={Link} to={`/dashboard/appointment/doctor/${user.id}`}>
                <ListItemIcon>
                    <DescriptionIcon/>
                </ListItemIcon>
                <ListItemText primary="Appointments"/>
            </ListItem>
            }

            {doctorRole &&
            <ListItem button component={Link} to={`/dashboard/term/doctor/${user.id}`}>
                <ListItemIcon>
                    <ScheduleIcon/>
                </ListItemIcon>
                <ListItemText primary="Terms"/>
            </ListItem>
            }

            {doctorRole &&
            <ListItem button component={Link} to={`/dashboard/term/add/${user.id}`}>
                <ListItemIcon>
                    <AddAlarmIcon/>
                </ListItemIcon>
                <ListItemText primary="Add term"/>
            </ListItem>
            }

{/*---------------------------------- ADMIN ROLE LIST ITEMS -------------------------------------------------*/}
            {adminRole &&
            <ListItem button component={Link} to="/dashboard/home">
                <ListItemIcon>
                    <HomeIcon/>
                </ListItemIcon>
                <ListItemText primary="Dashboard"/>
            </ListItem>
            }

            {adminRole &&
            <ListItem button component={Link}  to="/dashboard/doctor/list" >
                <ListItemIcon>
                    <PeopleIcon/>
                </ListItemIcon>
                <ListItemText primary="Doctors"/>
            </ListItem>
            }

            {adminRole &&
            <ListItem button component={Link} to="/dashboard/patient/list">
                <ListItemIcon>
                    <PeopleIcon/>
                </ListItemIcon>
                <ListItemText primary="Patients"/>
            </ListItem>
            }

            {adminRole &&
            <ListItem button component={Link} to="/dashboard/users/list">
                <ListItemIcon>
                    <PeopleIcon/>
                </ListItemIcon>
                <ListItemText primary="Users"/>
            </ListItem>
            }

            {adminRole &&
            <ListItem button component={Link} to="/dashboard/appointment/list">
                <ListItemIcon>
                    <EventNoteIcon/>
                </ListItemIcon>
                <ListItemText primary="All Appointments"/>
            </ListItem>
            }

            {adminRole &&
            <ListItem button component={Link} to="/dashboard/specialization/list">
                <ListItemIcon>
                    <RecentActorsIcon/>
                </ListItemIcon>
                <ListItemText primary="Doctor Specializations"/>
            </ListItem>
            }

{/*--------------------------- USER ROLE LIST ITEMS -----------------------------------------------*/}
            {userRole &&
            <ListItem button component={Link} to="/dashboard/home">
                <ListItemIcon>
                    <HomeIcon/>
                </ListItemIcon>
                <ListItemText primary="Dashboard"/>
            </ListItem>
            }

            {userRole &&
            <ListItem button component={Link} to={`/dashboard/patient/details/${AuthService.getCurrentUser().id}`}>
                <ListItemIcon>
                    <AccountBoxIcon/>
                </ListItemIcon>
                <ListItemText primary="View Profile"/>
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
            <ListItem button component={Link} to={`/dashboard/appointment/patient/${user.id}`}>
                <ListItemIcon>
                    <DescriptionIcon/>
                </ListItemIcon>
                <ListItemText primary="Appointments"/>
            </ListItem>
            }


            {userRole &&
            <ListItem button component={Link} to={`/dashboard/medicalHistory/list/${user.id}`}>
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