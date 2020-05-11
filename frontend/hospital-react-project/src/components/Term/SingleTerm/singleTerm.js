import React  from 'react';
import Button from "@material-ui/core/Button";
import EditIcon from "@material-ui/icons/Edit";
import DeleteIcon from "@material-ui/icons/Delete";

const SingleTerm = (props) => {

    return (
        <tr key={props.term.id}>
            <td>{props.term.id}</td>
            <td>{props.term.date}</td>
            <td>{props.term.timeOfAdmission}</td>
            <td>{props.term.status}</td>
            <td>
                <Button
                    variant="contained"
                    color="secondary"
                    size="small"
                    startIcon={<DeleteIcon />}
                    onClick={()=>props.onDelete(props.term.id, props.doctorId)}
                >
                    Delete
                </Button>
            </td>
        </tr>
    );
}

export default SingleTerm;
