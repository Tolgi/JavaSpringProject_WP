import React  from 'react';
import { Link } from 'react-router-dom';
import Button from '@material-ui/core/Button';
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';
import DetailsIcon from '@material-ui/icons/Details';
import { makeStyles } from '@material-ui/core/styles';


const SinglePatient = (props) => {
    const useStyles = makeStyles((theme) => ({
        button: {
            margin: theme.spacing(1),
        },
    }));
    const classes = useStyles();

    return (
        <tr key={props.patient.id}>
            <td>{props.patient.id}</td>
            <td>{props.patient.name}</td>
            <td>{props.patient.ssn}</td>
            <td>{props.patient.contactNo}</td>
            <td>{props.patient.creationDate}</td>
            <td>
                {/*<button onClick={() => props.onEdit(props.patient.id, "edit")} className="btn btn-primary a-btn-slide-text">*/}
                {/*    <span className="glyphicon glyphicon-remove" aria-hidden="true"></span>*/}
                {/*    <span><strong>Edit</strong></span>*/}
                {/*</button>*/}

                <Button
                    variant="contained"
                    color="primary"
                    size="small"
                    className={classes.button}
                    startIcon={<EditIcon>edit</EditIcon>}
                    onClick={() => props.onEdit(props.patient.id, "edit")}
                >
                    Edit
                </Button>


                {/*<button onClick={()=>props.onDelete(props.patient.id)} className="btn btn-primary a-btn-slide-text">*/}
                {/*    <span className="glyphicon glyphicon-remove" aria-hidden="true"></span>*/}
                {/*    <span><strong>Delete</strong></span>*/}
                {/*</button>*/}

                <Button
                    variant="contained"
                    color="secondary"
                    size="small"
                    startIcon={<DeleteIcon />}
                    onClick={()=>props.onDelete(props.patient.id)}
                >
                    Delete
                </Button>

                {/*<button onClick={()=>props.onDetails(props.patient.id, "details")} className="btn btn-primary a-btn-slide-text">*/}
                {/*    <span className="glyphicon glyphicon-remove" aria-hidden="true"></span>*/}
                {/*    <span><strong>Details</strong></span>*/}
                {/*</button>*/}

                <Button
                    variant="contained"
                    color="primary"
                    size="small"
                    className={classes.button}
                    startIcon={<DetailsIcon>details</DetailsIcon>}
                    onClick={()=>props.onDetails(props.patient.id, "details")}
                >
                    Details
                </Button>

            </td>
        </tr>
    );
}

export default SinglePatient;
