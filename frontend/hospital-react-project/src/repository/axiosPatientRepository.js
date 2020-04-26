import axios from '../custom-axios/axios'
import qs from 'qs'

const PatientService = {

    fetchPatients: ()=> {
        return axios.get("/api/patient");
    },

    getPatient: (patientId) => {
        return axios.get("/api/patient?patientId=" + patientId);
    },

    getNumberOfPatients: () => {
        return axios.get("/api/patient/number");
    },


    addPatient: (newPatient) => {
        const formParams = qs.stringify(newPatient);
        return axios.post("/api/patient",formParams, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            }
        });
    },

    updatePatient: (patient) => {
        const patientId = patient.id;
        const formParams = qs.stringify(patient); //from javascript obj to JSON obj
        return axios.patch("/api/patient/edit/" + patientId, formParams, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            }
        });
    },

    deletePatient: (id) => {
        const patientId = id;
        return axios.delete("/api/patient/" + id);
    }

};

export default PatientService;