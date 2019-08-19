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
      <img src={require("./BANANA.png")} />{" "}
    </div>
  );
}

function Grid() {
  return (
    <div class="grid-container">
      <div class="grid-item">Home</div>
      <div class="grid-item">MarketPlace</div>
      <div class="grid-item">Chat</div>
      <div class="grid-item">Account</div>
    </div>
  );
}

export default withRouter(HomeComponent);
