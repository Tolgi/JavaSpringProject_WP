import React from 'react';
import '../specialization.css';
import Button from "@material-ui/core/Button";
import EditIcon from "@material-ui/icons/Edit";
import DeleteIcon from "@material-ui/icons/Delete";
import {makeStyles} from "@material-ui/core/styles";


const SingleSpecialization = (props) => {
    const useStyles = makeStyles((theme) => ({
        button: {
            margin: theme.spacing(1),
        },
    }));
    const classes = useStyles();

    return (
        <tr key={props.specialization.id}>
            <td>{props.specialization.id}</td>
            <td>{props.specialization.name}</td>
            <td>{props.specialization.creationTime.replace('T', ' ').slice(0, 16)}</td>
            <td>
                <Button
                    variant="contained"
                    color="primary"
                    size="small"
                    className={classes.button}
                    startIcon={<EditIcon>edit</EditIcon>}
                    href={`/specialization/edit/${props.specialization.id}`}
                >
                    Edit
                </Button>

                <Button
                    variant="contained"
                    color="secondary"
                    size="small"
                    startIcon={<DeleteIcon />}
                    onClick={()=>props.onDelete(props.specialization.id)}
                >
                    Delete
                </Button>

            </td>
        </tr>
    );
}

export default SingleSpecialization;
