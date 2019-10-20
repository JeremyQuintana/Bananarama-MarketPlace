//Reference: https://www.npmjs.com/package/react-google-login

import React, { Component } from "react";
import AuthenticationService from "../DataServices/AuthenticationService.js";
import GoogleLogin from "react-google-login";
import config from "../config.json";
import "../css/LoginLogout.css";

class LoginComponent extends Component {
  constructor(props) {
    super(props);
    var loginErrorTemp = false;
    if (props.location == null || props.location.state == null) {
      loginErrorTemp = false;
    } else {
      loginErrorTemp = props.location.state.loginError;
      this.props.location.state.loginError = false;
    }
    this.state = {
      username: "",
      password: "",
      hasLoginFailed: false,
      showSuccessMessage: false,
      loginError: loginErrorTemp
    };

    this.responseGoogle = this.responseGoogle.bind(this);
  }

  //render all components
  render() {
    return (
      <div className="loginComponent">
        <div className="details">
          <img className="institute" src={require("../images/RMIT.png")} alt="RMIT"/>
          <h4>By: Bananarama</h4>
        </div>
        <div className="bananaLogo">
          <img className="banana" src={require("../images/banana.png")} alt="banana"/>
          <div className="title">
            <h1>Banana<br/>
            Place</h1>
          </div>
        </div>
        {this.state.hasLoginFailed && (
          <div className="alert alert-warning">
            Invalid Credentials or something is wrong
          </div>
        )}
        {this.state.loginError && (
          <div className="alert alert-warning">
            You must be logged in to access that!
          </div>
        )}
        {this.state.showSuccessMessage && <div>Login Sucessful</div>}

        <GoogleLogin
          clientId={config.googleclientid}
          buttonText="LOGIN"
          onSuccess={this.responseGoogle}
          onFailure={this.responseGoogle}
        />
      </div>
    );
  }

  //function that handles the response from google after google login
  async responseGoogle(res) {
    var idToken = res.getAuthResponse().id_token;

    //authenticates email backend
    AuthenticationService.executeGoogleJwtAuthenticationService(idToken)
      .then(response => {
        //if authenticated backend then set the succesfull lgoin
        AuthenticationService.registerSuccessfulLoginForJwt(
          res
            .getBasicProfile()
            .getEmail()
            .substring(0, 8),
          response.data.token
        );
        this.props.history.push(`/home/${sessionStorage.getItem("authenticatedUser")}`);
      })
      .catch(() => {
        //else show an error message
        this.setState({ showSuccessMessage: false });
        this.setState({ hasLoginFailed: true });
      });
  }

}
export default LoginComponent;
