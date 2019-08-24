import React, { Component } from "react";
import { Link } from "react-router-dom";
import { withRouter } from "react-router-dom";
import "./Home.css";

class HomeComponent extends Component {
  render() {
    let retVal = (
      <div className="Body">
        <App1></App1>
        <Grid></Grid>
        <div className="container"></div>
      </div>
    );
    return retVal;
  }
}

function App1() {
  return (
    <div className="App1">
      <img src={require("./BANANA.png")} />
    </div>
  );
}

function Grid() {
  return (
    <div class="grid-container">
      <div class="grid-item">Post <img src={require("./big-bill.png")} /></div>
      <div class="grid-item">MarketPlace <img src={require("./market-basket-.png")} /></div>
      <div class="grid-item">Chat <img src={require("./chat.png")} /></div>
      <div class="grid-item">Account <img src={require("./avatar.png")} /></div>
    </div>
  );
}

export default withRouter(HomeComponent);
