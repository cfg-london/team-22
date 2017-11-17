import React, { Component } from 'react';
import './friends.css';

class Friends extends Component {
  constructor(){
    super();
    this.state = {
      friends: ['<img src={require("../resources/testImage.jpg")} className="image" alt="Img" />']
    }
  }

  //What am I supposed to do with the name parameter?
  renderBox(name, imgSrc){
    return(
        '<img src={require(' + imgSrc + ')} className="image" alt="Img" />'
    );
  }


  renderBoxes(){
    let output = "";
    for(var i=0;i<this.state.length;i++){
      output += this.state.friends[i];
    }
    console.log(output);
    return output;
  }

  render(){
    return (
      <div className="Friends">
        {this.renderBoxes()}
      </div>
    )
  }
}

export default Friends;
