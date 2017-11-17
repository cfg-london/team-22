import React, {Component} from 'react';
import './profile.css';

class Profile extends Component {
  constructor(){
    super();
    this.state = {
      image: require('../resources/mlk-resources/king_photo_01.jpg'),
      body: "Martin Luther King Jr. - (January 15, 1929-April 4, 1968) was born Michael Luther King, Jr., but later had his name changed to Martin. His grandfather began the family's long tenure as pastors of the Ebenezer Baptist Church in Atlanta, serving from 1914 to 1931; his father has served from then until the present, and from 1960 until his death Martin Luther acted as co-pastor. Martin Luther attended segregated public schools in Georgia, graduating from high school at the age of fifteen; he received the B. A. degree in 1948 from Morehouse College, a distinguished Negro institution of Atlanta from which both his father and grandfather had graduated. After three years of theological study at Crozer Theological Seminary in Pennsylvania where he was elected president of a predominantly white senior class, he was awarded the B.D. in 1951. With a fellowship won at Crozer, he enrolled in graduate studies at Boston University, completing his residence for the doctorate in 1953 and receiving the degree in 1955. In Boston he met and married Coretta Scott, a young woman of uncommon intellectual and artistic attainments. Two sons and two daughters were born into the family.",
      wantMore: false
    }
  }

  // TODO: FIX THIS STUFF!
  renderText(s){
    if(s.length > 380){
      if(this.state.wantMore){
        return s;
      } else {
        let output = s.slice(0,300)
        return (
          <p> {output} <a className="learnMore" onClick={ e => this.changeFlag(e) }>Learn more</a> </p>
        )
      }
    } else {
      return (<p> {s} </p>);
    }
  }

  changeFlag() {
   this.setState({ wantMore: !this.state.wantMore });
  }

  render(){
    return(
      <div className="profile">
        <img src={this.state.image} className="profilePic" alt="Dylan is sexy" />
        <div className="motivation">
          {this.renderText(this.state.body)}
        </div>
      </div>
    )
  }
}

export default Profile;
