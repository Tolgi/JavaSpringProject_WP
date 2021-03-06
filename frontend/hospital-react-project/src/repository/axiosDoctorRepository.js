import axios from '../custom-axios/axios'
import qs from 'qs'

const DoctorService = {

    fetchDoctors: ()=> {
        return axios.get("/api/doctor");
    },

    getDoctor: (id) => {
        return axios.get("/api/doctor?doctorId=" + id);
    },

    getNumberOfDoctors: () => {
        return axios.get("/api/doctor/number");
    },

    getDoctorsBySpecialization: (specializationName) => {
        return axios.get("/api/doctor?specializationName=" + specializationName);
    },

    getPatients: (doctorId) => {
        return axios.get("/api/doctor/" + doctorId+"/patients");
    },

    addDoctor: (newDoctor) => {
        const formParams = qs.stringify(newDoctor);
        return axios.post("/api/doctor",formParams, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            }
        });
    },

    updateDoctor: (doctor) => {
        const doctorId = doctor.id;
        const formParams = qs.stringify(doctor); //from javascript obj to JSON obj
        return axios.patch("/api/doctor/edit/" + doctorId, formParams, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            }
        });
    },

    deleteDoctor: (id) => {
        const doctorId = id;
        return axios.delete("/api/doctor/" + id);
    }


};

export default DoctorService;