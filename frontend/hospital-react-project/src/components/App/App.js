import React from 'react';
import './App.css';
import Home from "../Home/home";
import {BrowserRouter as Router, Switch, Route, Link} from 'react-router-dom';
import Header from "../Header/header";
import Specialization from "../Specialization/specializations";
import Doctor from "../Doctor/doctors";
import Patient from "../Patient/patients";
import MedicalHistory from "../MedicalHistory/medicalHistory";
import Term from "../Term/terms";
import Appointment from "../Appointment/appointments";
import Dashboard from "../Dashboard/Dashboard";

function App() {


    return (
      <Router>
          <div className="App">
              <Dashboard/>
              {/*<Header/>*/}
              <div className="content">

              </div>
          </div>
      </Router>

  );
}

export default App;
