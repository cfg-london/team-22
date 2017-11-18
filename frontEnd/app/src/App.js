import React, { Component } from 'react';
import './App.css';

//Components
import Profile from './components/profile';
import {Navbar, NavItem, Icon} from 'react-materialize';

class App extends Component {
  render() {
    return (
      <div className="App">
        {/* <header className="App-header">
          <img src={require('./resources/logo-nobelprize_org.png')} className="App-logo" alt="logo" />
        </header> */}
        <div className="colour-blue">
        <Navbar brand={<img src={require('./resources/logo-nobelprize_org.png')} className="App-logo" alt="logo" />} right>
	        <NavItem href='get-started.html'><Icon>search</Icon></NavItem>
	        <NavItem href='get-started.html'><Icon>view_module</Icon></NavItem>
	        <NavItem href='get-started.html'><Icon>refresh</Icon></NavItem>
	        <NavItem href='get-started.html'><Icon>more_vert</Icon></NavItem>
        </Navbar>

        <div className="App-intro">
          <Profile />

        </div>
      </div>
    );
  }
}

export default App;
