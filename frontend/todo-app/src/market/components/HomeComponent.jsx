import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import "../css/Home.css";

class HomeComponent extends Component {
  render() {
    let retVal = (
      <div className="home">
        <h1>HOME</h1>
        <Grid history = {this.props.history}></Grid>
        <div className="banana">
          <img alt="banana" src={require("../images/background_ban.jpeg")} />
        </div>
      </div>

    );
    return retVal;
  }

  //changes history url when routing is changed
  routeChange(x) {
    this.props.history.push(x);
  }
}

//grid of objects that allow navigation around the marketplace
//routes to different page when clicked
const Grid = (props) => {
  return (
    <div className="grid-container-home">
      <div className="grid-item" onClick={() => props.history.push('/post')}>
        <h2>Post</h2>
        <img className= "img-fluid" alt="postImg" src={require("../images/big-bill.png")} />
      </div>
      <div className="grid-item" onClick={() => props.history.push('/market')}>
        <h2>MarketPlace</h2>
        <img className= "img-fluid" alt="marketImg" src={require("../images/market-basket-.png")} />
      </div>
      <div className="grid-item" onClick={() => props.history.push('/chat')}>
        <h2>Chat</h2>
        <img className= "img-fluid" alt="chatImg" src={require("../images/chat.png")} />
      </div>
      <div className="grid-item" onClick={() => props.history.push('/account')}>
        <h2>Account</h2>
        <img className= "img-fluid" alt="accountImg" src={require("../images/avatar.png")} />
      </div>
    </div>
  );
}

export default withRouter(HomeComponent);
