import Typography from "@material-ui/core/Typography";
import Link from "@material-ui/core/Link";
import {makeStyles} from "@material-ui/core/styles";
import React, {useEffect, useState} from "react";
import AuthService from "../../authentication/axiosAuthRepository";
import Container from "@material-ui/core/Container";
import CssBaseline from "@material-ui/core/CssBaseline";
import Avatar from "@material-ui/core/Avatar";
import LockOutlinedIcon from "@material-ui/icons/LockOutlined";
import Button from "@material-ui/core/Button";
import Box from "@material-ui/core/Box";

const LogOut = (props) => {

    function Copyright() {
        return (
            <Typography variant="body2" color="textSecondary" align="center">
                {'Copyright © '}
                <Link color="inherit" href="https://material-ui.com/">
                    Your Website
                </Link>{' '}
                {new Date().getFullYear()}
                {'.'}
            </Typography>
        );
    }

    const useStylesLogout = makeStyles((theme) => ({
        paperLogout: {
            marginTop: theme.spacing(8),
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
        },
        avatarLogout: {
            margin: theme.spacing(1),
            backgroundColor: theme.palette.secondary.main,
        },
        formLogout: {
            width: '100%', // Fix IE 11 issue.
            marginTop: theme.spacing(1),
        },
        submitLogout: {
            margin: theme.spacing(3, 0, 2),
        },
    }));

    const classes = useStylesLogout();

    useEffect(() => {
       AuthService.logout();
    },[]);


    return (
        <Container component="main" maxWidth="xs">
            <CssBaseline />
            <div className={classes.paperLogout}>
                <Avatar className={classes.avatarLogout}>
                    <LockOutlinedIcon />
                </Avatar>
                <Typography component="h1" variant="h2">
                    Logged Out
                </Typography>

                <Typography component="h3" variant="h5">
                    You are now logged out.
                    Thank you!
                </Typography>

                <Button  type="submit">
                    <a href="/login">Sign in again</a>
                </Button>

            </div>
            <Box mt={8}>
                <Copyright />
            </Box>
        </Container>
    );
};

export default LogOut;