import React, {useState, useEffect} from 'react';
import { Route, useHistory} from 'react-router-dom';
import DoctorService from "../../repository/axiosDoctorRepository";
import DoctorList from "./DoctorList/doctorList";
import DoctorEdit from "./DoctorEdit/doctorEdit";
import DoctorAdd from "./DoctorAdd/doctorAdd";
import SpecializationService from "../../repository/axiosSpecializationRepository";

const Doctor = ({match}) => {

    const [doctors, setDoctors] = useState([]);
    const [specializations, setSpecializations] = useState([]);
    const [oneDoctor, setOneDoctor] = useState({});
    const [oneSpecialization, setOneSpecialization] = useState({});
    const history = useHistory();


    useEffect(() => {
        loadDoctors();
    }, []);

    useEffect(() => {
        loadSpecializations();
    }, []);



    const loadDoctors = () => {
        DoctorService.fetchDoctors().then((response) => {
            setDoctors(response.data);
        });
    };

    const deleteDoctor = (doctorId) => {
        setDoctors(doctors.filter(doctor => doctor.id !== doctorId));
        DoctorService.deleteDoctor(doctorId);
    };

    const editDoctor = (newDoctor) => {
        DoctorService.updateDoctor(newDoctor).then((response) => {

            const updatedDoctor = response.data;
            const nextState = doctors.map((doctor) => {
                if(doctor.id === updatedDoctor.id){
                    return updatedDoctor;
                }

                return doctor;
            });

            setDoctors(nextState);
            history.push("/doctor/list");
        })
    };

    const loadSpecializations = () => {
        SpecializationService.fetchSpecializations().then((response) =>{
            setSpecializations(response.data);
        })
    };

    const loadDoctor = (doctorId) => {
        DoctorService.getDoctor(doctorId).then((response) => {
            setOneDoctor(response.data);
            setOneSpecialization(response.data.specialization);
        });

        history.push("/doctor/edit/"+doctorId);
    };

    const addDoctor = (newDoctor) => {
        DoctorService.addDoctor(newDoctor).then((response) => {
            const newDoctor = response.data;

            const nextState = [...doctors, newDoctor];
            setDoctors(nextState);
        });

        history.push("/doctor/list");
    };


    return (

        <div>
            <h2 className= "col-md-10 font-weight-bold " >Doctors</h2>
            <Route path={`${match.path}/list`} exact render={(props) => <DoctorList onEdit={loadDoctor} onDelete={deleteDoctor} doctors={doctors}/>}/>
            <Route path={`${match.path}/edit/:id`} exact render={(props) => <DoctorEdit specialization={oneSpecialization} specializations={specializations} doctor={oneDoctor} setDoctor={setOneDoctor} onEdit={editDoctor} {...props} />}/>
            <Route path={`${match.path}/add`} exact render={(props) => <DoctorAdd  specializations={specializations} onAdd={addDoctor} />}/>
            <Route path={`${match.path}/details`} exact render={(props) => <DoctorAdd  specializations={specializations} onAdd={addDoctor} />}/>
        </div>


    );
}

export default Doctor;
