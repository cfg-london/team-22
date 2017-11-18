import React, {Component} from 'react';
//
class Profile extends Component {
  render(){
    return(
      <div className="profile">
        <img src={require('../resources/testImage.jpg')} alt="Dylan is sexy" />
      </div>
    )
  }
}

export default Profile;
