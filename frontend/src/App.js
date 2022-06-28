import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ReservationList from './ReservationList';

class App extends Component {
  render() {
    return (
        <Router>
          <Switch>
            <Route path='/' exact={true} component={Home}/>
            <Route path='/reservations' exact={true} component={ReservationList}/>
          </Switch>
        </Router>
    )
  }
}

export default App;