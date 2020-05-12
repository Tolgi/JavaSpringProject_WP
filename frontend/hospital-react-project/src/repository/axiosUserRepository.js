import axios from '../custom-axios/axios'
import qs from 'qs'

const UserService = {

    fetchAllUsers: () => {
        return axios.get("/api/user");
    },

    getNumberOfUsers: () => {
        return axios.get("/api/user/number");
    },

    fetchLogs: ()=> {
        return axios.get("logs");
    }

};

export default UserService;