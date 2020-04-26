import React, {useEffect, useState} from 'react';
import Container from "@material-ui/core/Container";
import Grid from "@material-ui/core/Grid";
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import GroupIcon from '@material-ui/icons/Group';
import EventNoteIcon from '@material-ui/icons/EventNote';
import DoctorService from "../../repository/axiosDoctorRepository";
import PatientService from "../../repository/axiosPatientRepository";
import AppointmentService from "../../repository/axiosAppointmentRepository";




const AdminDashboard = (props) =>  {

    const useStyles = makeStyles({
        root: {
            minWidth: 275,
        },
        bullet: {
            display: 'inline-block',
            margin: '0 2 0 0px',
            transform: 'scale(0.8)',
        },
        title: {
            fontSize: 30,
        },
        pos: {
            marginBottom: 12,
        },
        purple: {
            color: "#9575cd",
            backgroundColor: "#b39ddb ",
        },
        blue: {
            color: '#7986cb',
            backgroundColor: "#9fa8da ",
        },
        darkBlue: {
            color: '#42a5f5',
            backgroundColor: "#90caf9 ",
        },
        yellow: {
            color: '#fdd835',
            backgroundColor: "#e6ee9c",
        },
    });

    const [numberDoctors, setNumberDoctors] = useState('');
    const [numberPatients, setNumberPatients] = useState('');
    const [numberAppointments, setNumberAppointments] = useState('');
    const classes = useStyles();
    const bull = <span className={classes.bullet}>•</span>;

    const getNumberOfDoctors = () =>{
        DoctorService.getNumberOfDoctors().then((response) => {
            setNumberDoctors(response.data);
        })
    };

    const getNumberOfPatients = () =>{
        PatientService.getNumberOfPatients().then((response) => {
            setNumberPatients(response.data);
        })
    };

    const getNumberOfAppointments = () =>{
        AppointmentService.getNumberOfAppointments().then((response) => {
            setNumberAppointments(response.data);
        })
    };


    useEffect(()=>{
        getNumberOfDoctors();
        getNumberOfPatients();
        getNumberOfAppointments();
    },[]);

    return (
    <div >
        <div />
        <Container maxWidth="lg">
            <Grid container spacing={10}>
                <Grid item >
                        <Card className={classes.root} variant="outlined">
                            <CardContent className={classes.purple}>
                                <GroupIcon style={{fontSize: '150px'}}></GroupIcon>
                                <Typography className={classes.title}  color="textSecondary" gutterBottom>
                                    Manage Doctors
                                </Typography>
                            </CardContent>
                            <CardActions>
                                <Typography variant={"body1"} color="primary" gutterBottom>
                                   Total Doctors: {numberDoctors}
                                </Typography>
                            </CardActions>
                        </Card>
                </Grid>
                <Grid item >
                    <Card className={classes.root} variant="outlined">
                        <CardContent className={classes.blue}>
                            <GroupIcon style={{fontSize: '150px'}}></GroupIcon>
                            <Typography className={classes.title} color="textSecondary" gutterBottom>
                                Manage Patients
                            </Typography>
                        </CardContent>
                        <CardActions>
                            <Typography variant={"body1"} color="primary" gutterBottom>
                                Total Patients: {numberPatients}
                            </Typography>
                        </CardActions>
                    </Card>
                </Grid>
                <Grid item >
                    <Card className={classes.root} variant="outlined">
                        <CardContent className={classes.darkBlue}>
                            <GroupIcon style={{fontSize: '150px'}}></GroupIcon>
                            <Typography className={classes.title} color="textSecondary" gutterBottom>
                                Manage Users
                            </Typography>
                        </CardContent>
                        <CardActions>
                            <Typography variant={"body1"} color="primary" gutterBottom>
                                Total Users:
                            </Typography>
                        </CardActions>
                    </Card>
                </Grid>
                <Grid item >
                    <Card className={classes.root} variant="outlined">
                        <CardContent className={classes.yellow}>
                            <EventNoteIcon style={{fontSize: '150px'}}></EventNoteIcon>
                            <Typography className={classes.title} color="textSecondary" gutterBottom>
                                Appointments
                            </Typography>
                        </CardContent>
                        <CardActions>
                            <Typography variant={"body1"} color="primary" gutterBottom>
                                Total Appointments: {numberAppointments}
                            </Typography>
                        </CardActions>
                    </Card>
                </Grid>
            </Grid>
        </Container>
    </div>
    )
};

export default AdminDashboard;