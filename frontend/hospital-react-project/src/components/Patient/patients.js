import React, {useState, useEffect} from 'react';
import { Route, useHistory} from 'react-router-dom';
import PatientService from "../../repository/axiosPatientRepository";
import PatientList from "./PatientList/patientList";
import PatientAdd from "./PatientAdd/patientAdd";
import PatientEdit from "./PatientEdit/patientEdit";
import PatientDetails from "./PatientDetails/patientDetails";


const Patient = ({match}) => {

    const [patients, setPatients] = useState([]);
    const [patient, setPatient] = useState({});
    const history = useHistory();

    useEffect(() => {
        loadPatients();
    }, []);

    const loadPatients = () => {
        PatientService.fetchPatients().then((response) => {
            setPatients(response.data);
        })
    };

    const deletePatient = (patientId) => {
        setPatients(patients.filter(patient => patient.id !== patientId));
        PatientService.deletePatient(patientId);
    };

    const addPatient = (patient) => {
        PatientService.addPatient(patient).then((response) => {
            const newPatient = response.data;
            const nextState = [...patients, newPatient];
            setPatients(nextState);
        });

        history.push("/patient/list");
        console.log(patient);
    };

    const editPatient = (patientId) => {
      PatientService.updatePatient(patientId).then((response) => {
         const updatedPatient = response.data;
         const nextState = patients.map(patient => {
             if(patient.id === updatedPatient.id){
                 return updatedPatient;
             }
             return patient;
         });

         setPatients(nextState);
         history.push("/patient/list");
      })
    };

    const loadPatient = (patientId, flag) => {
        PatientService.getPatient(patientId).then((response) => {
            const patient = response.data;
            setPatient(patient);
        });

        if(flag === "edit"){
            history.push("/patient/edit/"+patientId);
        }else {
            history.push("/patient/details/"+patientId);
        }
    };

    return (
        <div>
            <h2 className= "col-md-10 font-weight-bold " >Patients</h2>
            <Route path={`${match.path}/list`} exact render={(props) => <PatientList onDetails={loadPatient} onEdit={loadPatient} onDelete={deletePatient} patients={patients}/>}/>
            <Route path={`${match.path}/edit/:id`} exact render={(props) => <PatientEdit patient={patient} setPatient={setPatient} onEdit={editPatient} {...props} />}/>
            <Route path={`${match.path}/add`} exact render={(props) => <PatientAdd onAdd={addPatient} />}/>
            <Route path={`${match.path}/details/:id`} exact render={(props) => <PatientDetails  patient={patient} />}/>
        </div>
    );
}

export default Patient;
