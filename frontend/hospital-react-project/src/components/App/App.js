import React from 'react';
import logo from '../../logo.svg';
import './App.css';
import Nav from '../Nav/nav.js';
import Specialization from "../Specialization/specializations";
import Home from "../Home/home";
import {BrowserRouter as Router, Switch, Route, Link} from 'react-router-dom';
import SpecializationEdit from "../Specialization/SpecializationEdit/specializationEdit";
import Header from "../Header/header";

function App() {
  return (
      <Router>
          <div className="App">
              <Header/>
              <div className="content">
                  <Switch>
                      <Route path="/" exact component={Home}/>
                      <Route path="/specialization" component={Specialization}/>
                      <Route path="/specialization/edit/:specializationId" component={SpecializationEdit}/>
                  </Switch>
              </div>
          </div>
      </Router>

  );
}

export default App;
