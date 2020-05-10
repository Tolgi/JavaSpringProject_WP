import React  from 'react';


const SingleMedicalHistory = (props) => {

    return (
        <tr key={props.medicalHistory.id}>
            <td>{props.medicalHistory.id}</td>
            <td>{props.medicalHistory.bloodPressure}</td>
            <td>{props.medicalHistory.weight}</td>
            <td>{props.medicalHistory.bloodSugar}</td>
            <td>{props.medicalHistory.bodyTemperature}</td>
            <td>{props.medicalHistory.medicalPrescription}</td>
            <td>{props.medicalHistory.visitDate}</td>
        </tr>
    );
}

export default SingleMedicalHistory;
