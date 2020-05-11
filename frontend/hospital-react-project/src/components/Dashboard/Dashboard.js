import React, {useEffect, useState} from 'react';
import {Link} from "react-router-dom";
import clsx from 'clsx';
import { makeStyles } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import Drawer from '@material-ui/core/Drawer';
import Box from '@material-ui/core/Box';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import List from '@material-ui/core/List';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import Container from '@material-ui/core/Container';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';

import MenuIcon from '@material-ui/icons/Menu';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import NotificationsIcon from '@material-ui/icons/Notifications';
import {MainListItems, mainListItems, secondaryListItems} from './listItems';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import PersonIcon from '@material-ui/icons/Person';
import Doctor from "../Doctor/doctors";

import Home from "../Home/home";
import Specialization from "../Specialization/specializations";
import Patient from "../Patient/patients";
import MedicalHistory from "../MedicalHistory/medicalHistory";
import Term from "../Term/terms";
import Appointment from "../Appointment/appointments";
import AdminDashboard from "./adminDashboard";
import SpecializationList from "../Specialization/SpecializationList/specializationList";
import RouteAuthenticated from "../Rotes/AuthenticatedRoute";
import AuthService from "../../authentication/axiosAuthRepository";
import DoctorDashboard from "./doctorDashboard";
import RouteUnauthenticated from "../Rotes/UnauthenticatedRoute";
import Tooltip from '@material-ui/core/Tooltip';
import PatientDasboard from "./patientDashboard";


function Copyright() {
    return (
        <Typography variant="body2" color="textSecondary" align="center">
            {'Copyright © '}
            <Link color="inherit" href="https://material-ui.com/">
               Hospital Management System
            </Link>{' '}
            {new Date().getFullYear()}
            {'.'}
        </Typography>
    );
}

const drawerWidth = 240;

const useStyles = makeStyles((theme) => ({
    root: {
        display: 'flex',
    },
    toolbar: {
        paddingRight: 24, // keep right padding when drawer closed
    },
    toolbarIcon: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'flex-end',
        padding: '0 8px',
        ...theme.mixins.toolbar,
    },
    appBar: {
        zIndex: theme.zIndex.drawer + 1,
        transition: theme.transitions.create(['width', 'margin'], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
    },
    appBarShift: {
        marginLeft: drawerWidth,
        width: `calc(100% - ${drawerWidth}px)`,
        transition: theme.transitions.create(['width', 'margin'], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.enteringScreen,
        }),
    },
    menuButton: {
        marginRight: 36,
    },
    menuButtonHidden: {
        display: 'none',
    },
    title: {
        flexGrow: 1,
    },
    drawerPaper: {
        position: 'relative',
        whiteSpace: 'nowrap',
        width: drawerWidth,
        transition: theme.transitions.create('width', {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.enteringScreen,
        }),
    },
    drawerPaperClose: {
        overflowX: 'hidden',
        transition: theme.transitions.create('width', {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
        width: theme.spacing(7),
        [theme.breakpoints.up('sm')]: {
            width: theme.spacing(9),
        },
    },
    appBarSpacer: theme.mixins.toolbar,
    content: {
        flexGrow: 1,
        height: '100vh',
        overflow: 'auto',
    },
    container: {
        paddingTop: theme.spacing(8),
        paddingBottom: theme.spacing(4),
        paddingLeft: theme.spacing(0),
    },
    paper: {
        padding: theme.spacing(2),
        display: 'flex',
        overflow: 'auto',
        flexDirection: 'column',
    },
    fixedHeight: {
        height: 240,
    },
}));

export default function Dashboard({match}) {

    const [userRole, setUserRole] = useState(false);
    const [adminRole, setAdminRole] = useState(false);
    const [doctorRole, setDoctorRole] = useState(false);
    const [user, setUser]= useState({});

    useEffect(() => {
        const currentUser = AuthService.getCurrentUser();
        setUser(currentUser);

        if(currentUser){
            setUserRole(currentUser.roles.includes("ROLE_USER"));
            setAdminRole(currentUser.roles.includes("ROLE_ADMIN"));
            setDoctorRole(currentUser.roles.includes("ROLE_DOCTOR"));

        }
    }, []);

    const classes = useStyles();
    const [open, setOpen] = React.useState(true);
    const handleDrawerOpen = () => {
        setOpen(true);
    };
    const handleDrawerClose = () => {
        setOpen(false);
    };
    const fixedHeightPaper = clsx(classes.paper, classes.fixedHeight);

    return (
        <div className={classes.root}>
            <CssBaseline />
            <AppBar position="absolute" className={clsx(classes.appBar, open && classes.appBarShift)}>
                <Toolbar className={classes.toolbar}>
                    <IconButton
                        edge="start"
                        color="inherit"
                        aria-label="open drawer"
                        onClick={handleDrawerOpen}
                        className={clsx(classes.menuButton, open && classes.menuButtonHidden)}
                    >
                        <MenuIcon />
                    </IconButton>
                    <Typography component="h1" variant="h6" color="inherit" noWrap className={classes.title}>
                       Hospital Management System
                    </Typography>
                    <Typography component="h1" variant="h6" color="inherit" noWrap>
                        {userRole &&
                            <Tooltip title="View Profile">
                                <IconButton color="inherit" href={`/dashboard/patient/details/${user.id}`}> <PersonIcon
                                    color="inherit"/></IconButton>
                            </Tooltip>
                        }
                        {doctorRole &&
                        <Tooltip title="View Profile">
                            <IconButton color="inherit" href={`/dashboard/doctor/details/${user.id}`}> <PersonIcon
                                color="inherit"/></IconButton>
                        </Tooltip>
                        }
                        {user.username}
                    </Typography>
                    <Tooltip title="Log Out">
                    <Link to={"/logout"}><IconButton color="inherit">
                        <ExitToAppIcon color="action"/>
                    </IconButton></Link>
                    </Tooltip>
                </Toolbar>
            </AppBar>
            <Drawer
                variant="permanent"
                classes={{
                    paper: clsx(classes.drawerPaper, !open && classes.drawerPaperClose),
                }}
                open={open}
            >
                <div className={classes.toolbarIcon}>
                    <IconButton onClick={handleDrawerClose}>
                        <ChevronLeftIcon />
                    </IconButton>
                </div>
                <Divider />
                <List><MainListItems/></List>
                <Divider />
                <List>{secondaryListItems}</List>
            </Drawer>
            <main className={classes.content}>
                <div className={classes.appBarSpacer} />
                <Container maxWidth="lg" className={classes.container}>

                    <RouteAuthenticated path="/dashboard/doctor" component={Doctor} />
                    <RouteAuthenticated path="/dashboard/patient" component={Patient} />
                    <RouteAuthenticated path="/dashboard/medicalHistory" component={MedicalHistory} />
                    <RouteAuthenticated path="/dashboard/term" component={Term} />
                    <RouteAuthenticated path="/dashboard/appointment" component={Appointment} />
                    {/*<Switch>*/}
                    {/*    <Route path="/specialization" component={Specialization}/>*/}
                    {/*    <Route path="/doctor" component={Doctor}/>*/}
                    {/*    <Route path="/patient" component={Patient}/>*/}
                    {/*    <Route path="/medicalHistory" component={MedicalHistory}/>*/}
                    {/*    <Route path="/term" component={Term}/>*/}
                    {/*    <Route path="/appointment" component={Appointment}/>*/}
                    {/*</Switch>*/}
                    <Grid container spacing={3}>
                        {adminRole &&
                        <Grid item xs={7}>
                            <RouteAuthenticated path="/dashboard/adminHome" exact component={AdminDashboard}/>
                        </Grid>
                        }
                        {doctorRole &&
                        <Grid item xs={7}>
                            <RouteAuthenticated path="/dashboard/doctorHome" exact component={DoctorDashboard}/>
                        </Grid>
                        }

                        {userRole &&
                        <Grid item xs={7}>
                            <RouteAuthenticated path="/dashboard/patientHome" exact component={PatientDasboard}/>
                        </Grid>
                        }
                        <Grid item xs={12}>
                            <Paper className={classes.paper}>

                            </Paper>
                        </Grid>
                    </Grid>
                    <Box pt={6}>
                        <Copyright />
                    </Box>
                </Container>
            </main>
        </div>
    );
}