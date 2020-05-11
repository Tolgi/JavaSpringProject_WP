import React, {useState, useEffect} from 'react';
import { Route, useHistory} from 'react-router-dom';
import DoctorService from "../../repository/axiosDoctorRepository";
import DoctorList from "./DoctorList/doctorList";
import DoctorEdit from "./DoctorEdit/doctorEdit";
import DoctorAdd from "./DoctorAdd/doctorAdd";
import SpecializationService from "../../repository/axiosSpecializationRepository";
import DoctorDetails from "./DoctorDetails/doctorDetails";

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
            history.push(`/dashboard/doctor/details/${updatedDoctor.id}`);
        })
    };

    const loadSpecializations = () => {
        SpecializationService.fetchSpecializations().then((response) =>{
            setSpecializations(response.data);
        })
    };

    const loadDoctor = (doctorId, flag) => {
        DoctorService.getDoctor(doctorId).then((response) => {
            setOneDoctor(response.data);
            setOneSpecialization(response.data.specialization);
        });

        history.push("/dashboard/doctor/edit/"+doctorId);
    };


    const loadDoctorDetails = (doctorId) => {
        DoctorService.getDoctor(doctorId).then((response) => {
            setOneDoctor(response.data);
            setOneSpecialization(response.data.specialization);
        });

        history.push("/dashboard/doctor/details/"+doctorId);

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
            <Route path={`${match.path}/list`} exact render={(props) => <DoctorList onEdit={loadDoctor} onDelete={deleteDoctor} doctors={doctors}/>}/>
            <Route path={`${match.path}/edit/:id`} exact render={(props) => <DoctorEdit onLoad={loadDoctor} specialization={oneSpecialization} specializations={specializations} doctor={oneDoctor} setDoctor={setOneDoctor} onEdit={editDoctor} {...props} />}/>
            <Route path={`${match.path}/add`} exact render={(props) => <DoctorAdd  specializations={specializations} onAdd={addDoctor} />}/>
            <Route path={`${match.path}/details/:id`} exact render={(props) => <DoctorDetails onLoad={loadDoctorDetails} doctor={oneDoctor} {...props} />}/>
        </div>


    );
}

export default Doctor;
