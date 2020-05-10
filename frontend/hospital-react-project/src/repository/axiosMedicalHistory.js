import axios from '../custom-axios/axios'
import qs from 'qs'

const MedicalHistoryService = {

    fetchMedicalHistoriesByPatient: (patientId)=> {
        return axios.get("/api/medicalHistory/"+ patientId);
    },

    addMedicalHistory: (newMedicalHistory) => {
        const formParams = qs.stringify(newMedicalHistory);
        return axios.post("/api/medicalHistory",formParams, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            }
        });
    },

    deleteMedicalHistory: (id) => {
        const medicalHistoryId = id;
        return axios.delete("/api/medicalHistory/" + id);
    }


};

export default MedicalHistoryService;