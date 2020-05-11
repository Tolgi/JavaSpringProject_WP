import React, {useState, useEffect} from 'react';
import { Route, useHistory} from 'react-router-dom';
import MedicalHistoryService from "../../repository/axiosMedicalHistory";
import MedicalHistoryList from "./MedicalHistoryList/medicalHistoryList";
import MedicalHistoryAdd from "./MedicalHistoryAdd/medicalHistoryAdd";
import AuthService from "../../authentication/axiosAuthRepository";


const MedicalHistory = ({match}) => {


    const [medicalHistories, setMedicalHistories] = useState([]);
    const history = useHistory();

    const loadMedicalHistories = (patientId) => {
        MedicalHistoryService.fetchMedicalHistoriesByPatient(patientId).then((response) => {
            setMedicalHistories(response.data);
        })
    };

    const deleteMedicalHistory = (medicalHistoryId) => {
        setMedicalHistories(medicalHistories.filter(medicalHistory => medicalHistory.id !== medicalHistoryId));
        MedicalHistoryService.deleteMedicalHistory(medicalHistoryId);
        history.push("/patient/list");
    };

    const addMedicalHistory = (newMedicalHistory) => {
        console.log(newMedicalHistory);
      MedicalHistoryService.addMedicalHistory(newMedicalHistory).then((response) => {
          const medicalHistory = response.data;
          const nextState = [...medicalHistories, medicalHistory];
          setMedicalHistories(nextState);
      });

        history.push("/dashboard/patient/list/doctor/"+AuthService.getCurrentUser().id);

    };

    return (

        <div>
            <Route path={`${match.path}/list/:id`} exact render={(props) => <MedicalHistoryList onClick={loadMedicalHistories} medicalHistories={medicalHistories} {...props} />}/>
            <Route path={`${match.path}/delete/:id`}  render={(props) => deleteMedicalHistory(props.match.params.id)} />
            <Route path={`${match.path}/add/:id`}  render={(props) => <MedicalHistoryAdd onAdd={addMedicalHistory} {...props}/>}/>
        </div>

    );
}

export default MedicalHistory;
