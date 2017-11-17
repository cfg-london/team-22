import React, { Component } from 'react';
import './App.css';

//Components
import Profile from './components/profile';
// import Friends from './components/friends';

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={require('./resources/logo-nobelprize_org.png')} className="App-logo" alt="logo" />
        </header>
        <div className="App-intro">
          <Profile />

        </div>
      </div>
    );
  }
}

export default App;
