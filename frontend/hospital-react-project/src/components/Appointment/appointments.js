import React, {useState, useEffect} from 'react';
import { Route, useHistory} from 'react-router-dom';
import AppointmentService from "../../repository/axiosAppointmentRepository";

import AppointmentList from "./AppointmentList/appointmentList";
import AllAppointmentList from "./AppointmentList/allAppointmentsList";
import SpecializationService from "../../repository/axiosSpecializationRepository";
import DoctorAdd from "../Doctor/DoctorAdd/doctorAdd";
import AppointmentAdd from "./AppointmentAdd/appointmentAdd";



const Appointment = ({match}) => {

    const [appointmentsBy, setAppointmentsBy] = useState([]);
    const [appointments, setAppointments] = useState([]);
    const [specializations, setSpecializations] = useState([]);
    const history = useHistory();

    useEffect(() => {
         loadAppointments();
         loadSpecializations();
    }, []);

    const loadAppointments = () => {
      AppointmentService.fetchAppointments().then((response) => {
          setAppointments(response.data);
      })
    };

    const loadAppointmentsByDoctor = (doctorId) => {
        AppointmentService.getAppointmentsByDoctorId(doctorId).then((response) => {
            setAppointmentsBy(response.data)
        })
    };

    const loadAppointmentsByPatient = (patientId) => {
        AppointmentService.getAppointmentsByPatientId(patientId).then((response) => {
            setAppointmentsBy(response.data)
        })
    };

    const addAppointment = (newAppointment) => {
      AppointmentService.addAppointment(newAppointment).then((response) => {
          const newAppointment = response.data;
          const nextState = [...appointments, newAppointment];

          setAppointments(nextState);
      })
    };

    const updateStatus = (appointmentId, status, pathId) => {

        AppointmentService.updateStatus(appointmentId, status).then((response) => {

            const updatedAppointment = response.data;
            const nextState = appointments.map((appointment) => {
                if(appointment.id === appointmentId){
                    return updatedAppointment;
                }

                return appointment;
            });

            setAppointments(nextState);
            window.location.reload();
        });


    };

    const loadSpecializations = () => {
      SpecializationService.fetchSpecializations().then((response) => {
          setSpecializations(response.data);
      })
    };

    return (
        <div>
            <Route path={`${match.path}/list`} exact render={(props) => <AllAppointmentList appointments={appointments}/>}/>
            <Route path={`${match.path}/doctor/:id`} exact render={(props) => <AppointmentList onClick={loadAppointmentsByDoctor} onCancel={updateStatus} flag={"byDoctor"} appointments={appointmentsBy} {...props}/>}/>
            <Route path={`${match.path}/patient/:id`} exact render={(props) => <AppointmentList onClick={loadAppointmentsByPatient} onCancel={updateStatus} flag={"byPatient"} appointments={appointmentsBy} {...props}/>}/>
            <Route path={`${match.path}/add`} exact render={(props) => <AppointmentAdd  specializations={specializations} onAdd={addAppointment} />}/>
        </div>
    );
}

export default Appointment;
