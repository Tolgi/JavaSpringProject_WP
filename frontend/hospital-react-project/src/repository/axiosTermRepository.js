import axios from '../custom-axios/axios'
import qs from 'qs'

const TermService = {

    fetchTermsByDoctorId: (doctorId)=> {
        return axios.get("/api/term?doctorId="+doctorId);
    },

    fetchTermsByStatus: (status)=> {
        return axios.get("/api/term?status="+status);
    },

    fetchTermsByDoctorIdAndStatus: (doctorId, status)=> {
        return axios.get("/api/term/"+doctorId+"/"+status);
    },

    fetchAllTerms: () => {
        return axios.get("/api/term");
    },

    addTerm: (newTerm) => {
        const formParams = qs.stringify(newTerm);
        return axios.post("/api/term",formParams, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            }
        });
    },

    deleteTerm: (id) => {
        const termId = id;
        return axios.delete("/api/term/" + id);
    }

};

export default TermService;