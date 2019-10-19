import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import "../css/Home.css";

class HomeComponent extends Component {
  constructor(props){
    super(props);
  }

  render() {
    let retVal = (
      <div className="home">
        <h1>HOME</h1>
        <Grid history = {this.props.history}></Grid>
        <div className="banana">
          <img src={require("../images/background_ban.jpeg")} />
        </div>
      </div>

    );
    return retVal;
  }

  routeChange(x) {
    this.props.history.push(x);
  }
}


const Grid = (props) => {
  return (
    <div className="grid-container-home">
      <div className="grid-item" onClick={() => props.history.push('/post')}>
        <h2>Post</h2>
        <img className= "img-fluid" src={require("../images/big-bill.png")} />
      </div>
      <div className="grid-item" onClick={() => props.history.push('/market')}>
        <h2>MarketPlace</h2>
        <img className= "img-fluid" src={require("../images/market-basket-.png")} />
      </div>
      <div className="grid-item" onClick={() => props.history.push('/chat')}>
        <h2>Chat</h2>
        <img className= "img-fluid" src={require("../images/chat.png")} />
      </div>
      <div className="grid-item" onClick={() => props.history.push('/account')}>
        <h2>Account</h2>
        <img className= "img-fluid" src={require("../images/avatar.png")} />
      </div>
    </div>
  );
}

export default withRouter(HomeComponent);
