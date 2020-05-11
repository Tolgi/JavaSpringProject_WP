import React, {useState, useEffect} from 'react';
import { Route, useHistory} from 'react-router-dom';
import PatientService from "../../repository/axiosPatientRepository";
import PatientList from "./PatientList/patientList";
import PatientAdd from "./PatientAdd/patientAdd";
import PatientEdit from "./PatientEdit/patientEdit";
import PatientDetails from "./PatientDetails/patientDetails";
import DoctorService from "../../repository/axiosDoctorRepository";
import PatientListByDoctor from "./PatientListByDoctor/patientListByDoctor";


const Patient = ({match}) => {

    const [patients, setPatients] = useState([]);
    const [patientsByDoctor, setPatientsByDoctor] = useState([]);
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
         history.push(`/dashboard/patient/details/${updatedPatient.id}`);
      })
    };

    const loadPatient = (patientId, flag) => {
        PatientService.getPatient(patientId).then((response) => {
            const patient = response.data;
            setPatient(patient);
        });

        if(flag === "edit"){
            history.push(`/dashboard/patient/edit/${patient.id}`);
        }else {
            history.push(`/dashboard/patient/details/${patient.id}`);
        }
    };

    const loadPatientByDoctor = (doctorId) => {
        DoctorService.getPatients(doctorId).then((response) => {
            setPatientsByDoctor(response.data);
        })
    };

    return (
        <div>
            <Route path={`${match.path}/list`} exact render={(props) => <PatientList  onDetails={loadPatient} onEdit={loadPatient} onDelete={deletePatient} patients={patients} {...props}/>}/>
            <Route path={`${match.path}/list/doctor/:id`} exact render={(props) => <PatientListByDoctor onLoad={loadPatientByDoctor} onDetails={loadPatient} onEdit={loadPatient} onDelete={deletePatient} patients={patientsByDoctor}  {...props}/>}/>
            <Route path={`${match.path}/edit/:id`} exact render={(props) => <PatientEdit onLoad={loadPatient} patient={patient} setPatient={setPatient} onEdit={editPatient} {...props} />}/>
            <Route path={`${match.path}/add`} exact render={(props) => <PatientAdd onAdd={addPatient} />}/>
            <Route path={`${match.path}/details/:id`} exact render={(props) => <PatientDetails onLoad={loadPatient} patient={patient} {...props} />}/>
        </div>
    );
}

export default Patient;
