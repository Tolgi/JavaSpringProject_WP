import React  from 'react';
import Button from '@material-ui/core/Button';
import DeleteIcon from '@material-ui/icons/Delete';



const SingleUser = (props) => {

    return (
        <tr key={props.user.id}>
            <td>{props.user.id}</td>
            <td>{props.user.username}</td>
            <td>{props.user.email}</td>
        </tr>
    );
}

export default SingleUser;
