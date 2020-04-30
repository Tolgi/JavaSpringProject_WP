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
import Landing from "../Landing/landing";
import SignIn from "../SignIn/signIn";
import Container from "@material-ui/core/Container";
import SpecializationList from "../Specialization/SpecializationList/specializationList";

function App() {

// -------------will be fixed soon------------//
    return (
      <Router>
          <div className="App">

              {/*<Header/>*/}
              {/*<Dashboard/>*/}
              <Switch>
                  <Route path='/' exact render={(props) => <Landing/>}/>
                  <Route path="/signIn" exact render={(props) => <SignIn/>}/>
              </Switch>

          </div>
      </Router>

  );
}

export default App;
