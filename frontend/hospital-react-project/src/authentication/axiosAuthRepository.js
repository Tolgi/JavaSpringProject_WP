import axios from '../custom-axios/axios'
import qs from 'qs'

const AuthService = {


    registerPatient: (user) => {

        return axios.post("/api/auth/patient/signup",user, {
            headers: {
                'Content-Type': 'application/json',
            }
        });
    },

    registerDoctor: (user) => {
        return axios.post("/api/auth/doctor/signup",user, {
            headers: {
                'Content-Type': 'application/json',
            }
        });
    },

    login: (username, password) => {
        return axios.post("/api/auth/signin",
            {
                username,
                password
            }, {
                headers: {
                    'Content-Type': 'application/json',
                }
            });
    },

    logout() {
        localStorage.removeItem("user");
    },

    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));;
    },

    isAuthenticated() {
        const user = JSON.parse(localStorage.getItem('user'));

        if (user && user.accessToken) {
            return true;
        } else {
            return false;
        }
    }

};

export default AuthService;