import React from 'react';
import './App.css';
import {BrowserRouter as Router, Switch, Route, Redirect} from 'react-router-dom';
import Dashboard from "../Dashboard/Dashboard";
import Landing from "../Landing/landing";
import RouteUnauthenticated from "../Rotes/UnauthenticatedRoute";
import RouteAuthenticated from "../Rotes/AuthenticatedRoute";
import LogIn from "../Login/login";
import LogOut from "../Logout/logout";
import SignUpPatient from "../SignUp/signUpPatient";
import SignUpDoctor from "../SignUp/signUpDoctor";

function App() {

// -------------will be fixed soon------------//
    return (
      <Router>
          <div className="App">
              <Switch>
                  <Route path='/' exact render={(props) => <Landing/>}/>
                  <Route path='/logout' exact render={(props) => <LogOut/>}/>
                  <RouteUnauthenticated path="/signUpPatient" component={SignUpPatient} />
                  <RouteUnauthenticated path="/signUpDoctor" component={SignUpDoctor} />
                  <RouteUnauthenticated path="/login" component={LogIn} />
                  <RouteAuthenticated path="/dashboard" component={Dashboard} />
                  <Redirect to="/" />
              </Switch>

          </div>
      </Router>

  );
}

export default App;
