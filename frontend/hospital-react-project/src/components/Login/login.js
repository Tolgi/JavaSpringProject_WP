import React, {useState} from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import {useHistory} from "react-router-dom";
import AuthService from "../../authentication/axiosAuthRepository";



const LogIn = (props) => {

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

    const useStylesLogIn = makeStyles((theme) => ({
        paperLogin: {
            marginTop: theme.spacing(8),
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
        },
        avatarLogin: {
            margin: theme.spacing(1),
            backgroundColor: theme.palette.secondary.main,
        },
        formLogin: {
            width: '100%', // Fix IE 11 issue.
            marginTop: theme.spacing(1),
        },
        submitLogin: {
            margin: theme.spacing(3, 0, 2),
        },
    }));

    const classes = useStylesLogIn();

    const[user, setUser] = useState({});
    const[message, setMessage] = useState('');
    const history = useHistory();

    const onFormSubmit = (e) => {
        e.preventDefault();

        AuthService.login(user.username, user.password,
        ).then(response => {
           // console.log(response.data);
            localStorage.setItem("user", JSON.stringify(response.data));
            history.push("/dashboard");
            history.go();

        },error => {
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


    return (
        <Container component="main" maxWidth="xs">
            <CssBaseline />
            <div className={classes.paperLogin}>
                <Avatar className={classes.avatarLogin}>
                    <LockOutlinedIcon />
                </Avatar>
                <Typography component="h1" variant="h5">
                    Sign in
                </Typography>
                <form className={classes.formLogin} onSubmit={onFormSubmit} noValidate>
                    <TextField
                        onChange={handleTermOnChange}
                        name="username"
                        variant="outlined"
                        required
                        fullWidth
                        id="username"
                        label="Username"
                        autoFocus
                    />
                    <TextField
                        onChange={handleTermOnChange}
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        name="password"
                        label="Password"
                        type="password"
                        id="password"
                        autoComplete="current-password"
                    />
                    <label
                        style={{display:"none", color:"red"}}
                        id="error"
                    >{message}</label>

                    <FormControlLabel
                        control={<Checkbox value="remember" color="primary" />}
                        label="Remember me"
                    />
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        color="primary"
                        className={classes.submitLogin}
                    >
                        Sign In
                    </Button>
                    <Grid container>
                        <Grid item xs>
                            <Link href="#" variant="body2">
                                Forgot password?
                            </Link>
                        </Grid>
                        <Grid item>
                            <Link href="/signUpPatient" variant="body2">
                                {"Don't have an account? Sign Up"}
                            </Link>
                        </Grid>
                    </Grid>
                </form>
            </div>
            <Box mt={8}>
                <Copyright />
            </Box>
        </Container>
    );
};

export default LogIn;