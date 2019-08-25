import React, { Component } from "react";
import { Link } from "react-router-dom";
import { withRouter } from "react-router-dom";
import "./Home.css";

class HomeComponent extends Component {
  constructor(props){
    super(props);
  }

  render() {
    let retVal = (
      <div className="homeContainer">
        <App1></App1>
        <Grid history = {this.props.history}></Grid>
      </div>
    );
    return retVal;
  }

  routeChange(x) {
    this.props.history.push(x);
  }
}

const App1 = () => {
  return (
    <div className="App1">
      <img src={require("./BANANA.png")} />
    </div>
  );
}

const Grid = (props) => {
  return (
    <div class="grid-container">
      <div class="grid-item" onClick={() => props.history.push('/post')}>Post <img src={require("./big-bill.png")} /></div>
      <div class="grid-item" onClick={() => props.history.push('/market')}>MarketPlace <img src={require("./market-basket-.png")} /></div>
      <div class="grid-item" onClick={() => props.history.push('/chat')}>Chat <img src={require("./chat.png")} /></div>
      <div class="grid-item" onClick={() => props.history.push('/account')}>Account <img src={require("./avatar.png")} /></div>
    </div>
  );
}

export default withRouter(HomeComponent);
