import {makeStyles} from "@material-ui/core/styles";
import React from "react";
import Container from "@material-ui/core/Container";
import Grid from "@material-ui/core/Grid";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import Typography from "@material-ui/core/Typography";
import CardActions from "@material-ui/core/CardActions";
import MoodIcon from '@material-ui/icons/Mood';
import FileCopyIcon from '@material-ui/icons/FileCopy';
import AuthService from "../../authentication/axiosAuthRepository";
import PostAddIcon from '@material-ui/icons/PostAdd';


const PatientDasboard = (props) =>  {

    const useStyles = makeStyles({
        root: {
            minWidth: 145,
        },
        bullet: {
            display: 'inline-block',
            margin: '0 2 0 0px',
            transform: 'scale(0.8)',
        },
        title: {
            fontSize: 15,
            textAlign: "center",
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
    const classes = useStyles();

    return (
        <div >
            <div />
            <Container maxWidth="lg">
                <Grid container spacing={6}>
                    <Grid item >
                        <Card className={classes.root} variant="outlined">
                            <CardContent className={classes.purple}>
                                <MoodIcon style={{fontSize: '150px'}}></MoodIcon>
                                <Typography className={classes.title}  color="textSecondary" gutterBottom>
                                    My Profile
                                </Typography>
                            </CardContent>
                            <CardActions>
                                <Typography  variant={"body1"} color="primary" gutterBottom>
                                    <a href={"/dashboard/patient/edit/" + AuthService.getCurrentUser().id}>Update Profile Here</a>
                                </Typography>
                            </CardActions>
                        </Card>
                    </Grid>
                    <Grid item >
                        <Card className={classes.root} variant="outlined">
                            <CardContent className={classes.blue}>
                                <FileCopyIcon style={{fontSize: '150px'}}></FileCopyIcon>
                                <Typography className={classes.title} color="textSecondary" gutterBottom>
                                    My Appointments
                                </Typography>
                            </CardContent>
                            <CardActions>
                                <Typography variant={"body1"} color="primary" gutterBottom>
                                    <a href={"/dashboard/appointment/patient/" + AuthService.getCurrentUser().id}>View Appointment History</a>
                                </Typography>
                            </CardActions>
                        </Card>
                    </Grid>
                    <Grid item >
                        <Card className={classes.root} variant="outlined">
                            <CardContent className={classes.darkBlue}>
                                <PostAddIcon style={{fontSize: '150px'}}></PostAddIcon>
                                <Typography className={classes.title} color="textSecondary" gutterBottom>
                                    Book My Appointment
                                </Typography>
                            </CardContent>
                            <CardActions>
                                <Typography variant={"body1"} color="primary" gutterBottom>
                                    <a href={"/dashboard/appointment/add"}>Book Appointment</a>
                                </Typography>
                            </CardActions>
                        </Card>
                    </Grid>
                </Grid>
            </Container>
        </div>
    )
};

export default PatientDasboard;