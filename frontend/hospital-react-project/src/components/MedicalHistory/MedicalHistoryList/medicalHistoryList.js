import React , {useState, useEffect} from 'react';
import SingleMedicalHistory from "../SingleMedicalHistory/singleMedicalHistory";

const MedicalHistoryList = (props) => {

    useEffect(() =>{
        props.onClick(props.match.params.id);
    },[]);

    const singleMedicalHistory = props.medicalHistories.map((medicalHistory) => {
        return(
            <SingleMedicalHistory  medicalHistory={medicalHistory} key={medicalHistory.id}/>
        );
    });


    return (
        <div>
            <div className="table-wrapper">
                <table className="fl-table">
                    <thead>
                    <tr>
                        <th>#ID</th>
                        <th>Blood Pressure</th>
                        <th>Weight</th>
                        <th>Blood Sugar</th>
                        <th>Body Temperature</th>
                        <th>Medical Prescription</th>
                        <th>Visit Date</th>
                    </tr>
                    </thead>
                    <tbody>
                    {singleMedicalHistory}
                    </tbody>
                </table>
            </div>
        </div>

    );
}

export default MedicalHistoryList;
