import axios from '../custom-axios/axios'
import qs from 'qs'

const UserService = {

    fetchAllUsers: () => {
        return axios.get("/api/user");
    },

    getNumberOfUsers: () => {
        return axios.get("/api/user/number");
    }

};

export default UserService;