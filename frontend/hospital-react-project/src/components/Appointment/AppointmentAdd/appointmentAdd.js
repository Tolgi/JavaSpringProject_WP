import React, {useEffect, useState} from 'react';
import DoctorService from "../../../repository/axiosDoctorRepository";
import TermService from "../../../repository/axiosTermRepository";
import {Divider} from "@material-ui/core";
import AuthService from "../../../authentication/axiosAuthRepository";

const AppointmentAdd = (props) => {

    const[doctors, setDoctorsBySpecialization] = useState([]);
    const[termsByDoctor, setTermsByDoctorId] = useState([]);
    const[allDoctors, setAllDoctors] = useState([]);
    const [anyTerms, setAnyTerms] = useState(false);

    const onFormSubmit = (e) => {
        const currentUser = AuthService.getCurrentUser().id;

        e.preventDefault();

        props.onAdd({
            "patientId": currentUser ,
            "status": "active",
            "doctorId": e.target.doctorId.value,
            "termId": e.target.termId.value
        });
    };

    useEffect(() => {
        loadDoctors();
    }, []);

    const loadDoctors = () => {
        DoctorService.fetchDoctors().then((response) => {
            setAllDoctors(response.data);
        });
    };


    const getDoctorBySpecialization = (specializationName) => {
        DoctorService.getDoctorsBySpecialization(specializationName).then((response) => {
            setDoctorsBySpecialization(response.data);
        })
    };

    const getTermsByDoctorId = (doctorId) =>{
        TermService.fetchTermsByDoctorIdAndStatus(doctorId, "free").then((response) => {
            if(response.data){
                setAnyTerms(true);
            }
            setTermsByDoctorId(response.data);
        })
    };

////////////// ON CHANGE METHODS ///////////////
    const setDoctors = (e) => {
        getDoctorBySpecialization(e.target.value);
    };


    const onDoctorChange = (e) => {
        const doctorId = e.target.value;
        getTermsByDoctorId(doctorId);
    };
// ------------------------------------------------

    const optionsSpecializations = props.specializations.map((specialization) => {
        return(
            <option key={specialization.id} value={specialization.name}>{specialization.name}</option>
        );
    });


    const optionsDoctors = doctors.map((doctor) => {
        return(
            <option key={doctor.id} value={doctor.id}>{doctor.name}</option>
        );
    });

    const optionsTerms = termsByDoctor.map((term) => {
        return(
            <option key={term.id} value={term.id}>{term.date} / {term.timeOfAdmission}</option>
        );
    });




    return (

        <div>
            <h4>BOOK APPOINTMENT</h4>
            <Divider />
            <br />
            <div className="card-body" >
                <div className="card-text">
                    <form onSubmit={onFormSubmit} id="editForm">
                        <div className="form-group col-md-8">
                            <label>Doctor specialization</label>
                            <select onChange={setDoctors} name={"id"} className="form-control">
                                <option disabled selected>Choose Doctor specialization</option>
                                {optionsSpecializations}
                            </select>
                        </div>

                        <div className="form-group col-md-8">
                            <label>Doctors</label>
                            <select onClick={onDoctorChange}  name={"doctorId"} className="form-control">
                                <option disabled selected>Choose doctor</option>
                                {optionsDoctors}
                            </select>
                        </div>

                        <div className="form-group col-md-8">
                            <label>Term</label>
                            <select  name={"termId"} className="form-control">
                                <option disabled selected>Choose term</option>
                                {optionsTerms}
                            </select>
                        </div>

                        <button type="submit" id="editbtn" className="btn btn-primary  col-md-2 editButton">Add</button>
                    </form>
                </div>
            </div>

        </div>
    );

};

export default AppointmentAdd;
