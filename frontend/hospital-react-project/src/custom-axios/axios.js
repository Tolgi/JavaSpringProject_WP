import axios from 'axios';
import authHeader from "../authentication/authenticationHeader";

const instance = axios.create({
    baseURL: 'http://localhost:8080',
    headers: authHeader(),
});

export default instance;