import React  from 'react';
import Button from '@material-ui/core/Button';
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';
import { makeStyles } from '@material-ui/core/styles';


const SingleDoctor = (props) => {
    const useStyles = makeStyles((theme) => ({
        button: {
            margin: theme.spacing(1),
        },
    }));
    const classes = useStyles();

    return (
        <tr key={props.doctor.id}>
            <td>{props.doctor.id}</td>
            <td>{props.doctor.name}</td>
            <td>{props.doctor.specialization.name}</td>
            <td>
                <Button
                    variant="contained"
                    color="primary"
                    size="small"
                    className={classes.button}
                    startIcon={<EditIcon>edit</EditIcon>}
                    onClick={() => props.onEdit(props.doctor.id)}
                >
                    Edit
                </Button>

                <Button
                    variant="contained"
                    color="secondary"
                    size="small"
                    startIcon={<DeleteIcon />}
                    onClick={()=>props.onDelete(props.doctor.id)}
                >
                    Delete
                </Button>
            </td>
        </tr>
    );
}

export default SingleDoctor;
