import React, {useState, useEffect} from 'react';
import { Route, useHistory} from 'react-router-dom';
import UserService from "../../repository/axiosUserRepository";
import UserList from "./UserList/userList";

const User = ({match}) => {

    const [users, setUsers] = useState([]);
    const history = useHistory();


    useEffect(() => {
        loadUsers();
    }, []);


    const loadUsers = () => {
        UserService.fetchAllUsers().then((response) => {
            setUsers(response.data);
        });
    };


    return (

        <div>
            <Route path={`${match.path}/list`} exact render={(props) => <UserList users={users}/>}/>
        </div>


    );
}

export default User;
