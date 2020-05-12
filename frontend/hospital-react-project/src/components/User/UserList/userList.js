import React from 'react';
import { Divider } from '@material-ui/core';
import {MDBBtn, MDBDataTable, MDBIcon} from "mdbreact";

const UserList = (props) => {

    const data = {
        columns: [
            {
                label: 'ID',
                field: 'id',
                sort: 'asc',
                width: 150
            },
            {
                label: 'Username',
                field: 'username',
                sort: 'asc',
                width: 270
            },
            {
                label: 'Email',
                field: 'email',
                sort: 'asc',
                width: 200
            },

        ],
        rows: [

            ...props.users.map((user) => ({
                id: user.id,
                username: user.username,
                email: user.email,
                })

            )
        ]
    };



    return (
        <div>
            <h4>ADMIN | MANAGE USERS</h4>
            <Divider />
            <br />


            <div className="table-wrapper">
                <MDBDataTable
                    responsive
                    striped
                    hover
                    data={data}
                />
            </div>
        </div>

    );
}

export default UserList;
