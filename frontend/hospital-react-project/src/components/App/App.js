import React from 'react';
import './App.css';
import Home from "../Home/home";
import {BrowserRouter as Router, Switch, Route, Link} from 'react-router-dom';
import Header from "../Header/header";
import Specialization from "../Specialization/specializations";
import Doctor from "../Doctor/doctors";

function App() {
  return (
      <Router>
          <div className="App">
              <Header/>
              <div className="content">
                  <Switch>
                      <Route path="/" exact component={Home}/>
                      <Route path="/specialization" component={Specialization}/>
                      <Route path="/doctor" component={Doctor}/>
                  </Switch>
              </div>
          </div>
      </Router>

  );
}

export default App;