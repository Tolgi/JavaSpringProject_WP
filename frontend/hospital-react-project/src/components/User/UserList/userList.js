import React from 'react';
import { Divider } from '@material-ui/core';
import SingleUser from "../SingleUser/singleUser";

const UserList = (props) => {

    const singleUser = props.users.map((user) => {
        return(
            <SingleUser user={user} key={user.id}/>
        );
    });


    return (
        <div>
            <h4>ADMIN | MANAGE USERS</h4>
            <Divider />
            <br />

            <div className="table-wrapper">
                <table className="fl-table">
                    <thead>
                    <tr>
                        <th>#ID</th>
                        <th>Username</th>
                        <th>Email</th>
                    </tr>
                    </thead>
                    <tbody>
                    {singleUser}
                    </tbody>
                </table>
            </div>
        </div>

    );
}

export default UserList;
