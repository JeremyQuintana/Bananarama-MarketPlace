import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import "./Home.css";

class HomeComponent extends Component {
  constructor(props){
    super(props);
  }

  render() {
    let retVal = (
      <div className="homeContainer">
            <h1>Home</h1>

         <div className="inner">
        <Grid history = {this.props.history}></Grid>
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
    <div>
    <div class="grid-container">
      <div class="grid-item" onClick={() => props.history.push('/post')}>Post <img class = "img-fluid" src={require("./big-bill.png")} /></div>
      <div class="grid-item" onClick={() => props.history.push('/market')}>MarketPlace <img class = "img-fluid" src={require("./market-basket-.png")} /></div>
      <div class="grid-item" onClick={() => props.history.push('/chat')}>Chat <img class = "img-fluid" src={require("./chat.png")} /></div>
      <div class="grid-item" onClick={() => props.history.push('/account')}>Account <img class = "img-fluid" src={require("./avatar.png")} /></div>
          </div>

  <div className="App2">
      <img class = "img-fluid" src={require("./background_ban.jpeg")} />
    </div>
    </div>
  );
}

export default withRouter(HomeComponent);
