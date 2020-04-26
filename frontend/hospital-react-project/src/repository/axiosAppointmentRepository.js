import axios from '../custom-axios/axios'
import qs from 'qs'

const AppointmentService = {

    fetchAppointments: ()=> {
        return axios.get("/api/appointment");
    },

    getAppointment: (id) => {
        return axios.get("/api/appointment?appointmentId=" + id);
    },

    getNumberOfAppointments: () => {
        return axios.get("/api/appointment/number");
    },

    getAppointmentsByPatientId: (id) => {
        return axios.get("/api/appointment?patientId=" + id);
    },

    getAppointmentsByDoctorId: (id) => {
        return axios.get("/api/appointment?doctorId=" + id);
    },


    addAppointment: (newAppointment) => {
        const formParams = qs.stringify(newAppointment);
        return axios.post("/api/appointment",formParams, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            }
        });
    },


    updateStatus: (appointmentId, status) => {

        return axios.patch("/api/appointment/" + appointmentId + "/" + status, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            }
        });
    },


    deleteAppointment: (id) => {
        const appointmentId = id;
        return axios.delete("/api/appointment/" + id);
    }


};

export default AppointmentService;