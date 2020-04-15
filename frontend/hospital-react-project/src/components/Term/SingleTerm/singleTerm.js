import React  from 'react';

const SingleTerm = (props) => {

    return (
        <tr key={props.term.id}>
            <td>{props.term.id}</td>
            <td>{props.term.date}</td>
            <td>{props.term.timeOfAdmission}</td>
            <td>{props.term.status}</td>
        </tr>
    );
}

export default SingleTerm;
