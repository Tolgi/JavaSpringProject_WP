import React, {useEffect, useState} from 'react';
import Button from '@material-ui/core/Button';
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';
import DetailsIcon from '@material-ui/icons/Details';
import { makeStyles } from '@material-ui/core/styles';
import AuthService from "../../../authentication/axiosAuthRepository";
import AddIcon from "@material-ui/icons/Add";


const SinglePatient = (props) => {

    const [user, setUser] = useState({});
    const [userRole, setUserRole] = useState(false);
    const [adminRole, setAdminRole] = useState(false);
    const [doctorRole, setDoctorRole] = useState(false);

    useEffect(() => {
        const currentUser = AuthService.getCurrentUser();

        if(currentUser){
            setUserRole(currentUser.roles.includes("ROLE_USER"));
            setAdminRole(currentUser.roles.includes("ROLE_ADMIN"));
            setDoctorRole(currentUser.roles.includes("ROLE_DOCTOR"));
            setUser(currentUser);
        }
    }, []);

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
            <td>{props.patient.creationDate.replace('T', ' ').slice(0, 16)}</td>
            <td>
                {adminRole &&
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

                }
                {adminRole &&
                <Button
                    variant="contained"
                    color="secondary"
                    size="small"
                    startIcon={<DeleteIcon />}
                    onClick={()=>props.onDelete(props.patient.id)}
                >
                    Delete
                </Button>
                }

                {adminRole || doctorRole &&
                <Button
                    variant="contained"
                    color="primary"
                    size="small"
                    className={classes.button}
                    startIcon={<DetailsIcon>details</DetailsIcon>}
                    onClick={() => props.onDetails(props.patient.id, "details")}
                >
                    Details
                </Button>
                }

                {doctorRole &&
                <Button variant="contained" size="small" startIcon={<AddIcon>add</AddIcon>} color="secondary" href={`/dashboard/medicalHistory/add/${props.patient.id}`}>
                    Add medical histories
                </Button>
                }

                {doctorRole &&
                <Button variant="contained" size="small" color="primary" href={`/dashboard/medicalHistory/list/${props.patient.id}`}>
                    Show histories
                </Button>
                }
            </td>
        </tr>
    );
}

export default SinglePatient;
