import axios from '../custom-axios/axios'
import qs from 'qs'

const SpecializationService = {

    fetchSpecializations: ()=> {
        return axios.get("/api/specialization");
    },

    getSpecialization: (id) => {
        return axios.get("/api/specialization/" + id);
    },

    addSpecialization: (specializationName) => {
        const newSpecialization = {
            "name": specializationName
        }
        const formParams = qs.stringify(newSpecialization);
        return axios.post("/api/specialization",formParams, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            }
        });
    },


    updateSpecialization: (specialization) => {
        const specializationId = specialization.id;
        const formParams = qs.stringify(specialization); //from javascript obj to JSON obj
        return axios.patch("/api/specialization/edit/" + specializationId, formParams, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            }
        });
    },

    deleteSpecialization: (id) => {
        const specializationId = id;
        return axios.delete("/api/specialization/" + id);
    }


};

export default SpecializationService;