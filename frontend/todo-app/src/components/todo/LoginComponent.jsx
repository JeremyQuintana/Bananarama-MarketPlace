//Reference: https://www.npmjs.com/package/react-google-login

import React, { Component } from "react";
import AuthenticationService from "./AuthenticationService.js";
import GoogleLogin from "react-google-login";
import config from "./config.json";
import "./LoginLogout.css";

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
      username: "sept",
      password: "",
      hasLoginFailed: false,
      showSuccessMessage: false,
      loginError: loginErrorTemp
    };

    this.handleChange = this.handleChange.bind(this);
    this.loginClicked = this.loginClicked.bind(this);

    this.responseGoogle = this.responseGoogle.bind(this);
  }

    async responseGoogle(res) {
    console.log("THIS IS THE GOOGLE INFO", res);
    console.log("THIS IS THE ULTIMATE TEST");
    var idToken = res.getAuthResponse().id_token;

    AuthenticationService.executeGoogleJwtAuthenticationService(idToken)
      .then(response => {
        AuthenticationService.registerSuccessfulLoginForJwt(
          res
            .getBasicProfile()
            .getEmail()
            .substring(0, 8),
          response.data.token
        );
        this.props.history.push(`/home/${this.state.username}`);
      })
      .catch(() => {
        this.setState({ showSuccessMessage: false });
        this.setState({ hasLoginFailed: true });
      });

    //      await this.props.googleauth(res.accessToken);
  }

  handleChange(event) {
    //console.log(this.state);
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  loginClicked() {

    AuthenticationService.executeJwtAuthenticationService(
      this.state.username,
      this.state.password
    )
      .then(response => {
        AuthenticationService.registerSuccessfulLoginForJwt(
          this.state.username,
          response.data.token
        );
        this.props.history.push(`/home/${this.state.username}`);
      })
      .catch(() => {
        this.setState({ showSuccessMessage: false });
        this.setState({ hasLoginFailed: true });
      });
  }

  render() {
    return (
      <div className="container">
        <div classNmae="inside_container">
          <div className="heading">
             <div className="banana">
                <img src={require("./banana.png")} width="200" height="300" />
              </div>
            <div className="App1">
          
              <img src={require("./RMIT.png")} width="200" height="300" />
            </div>

            <h3>BananaPlace</h3>

            <h4>By Bananarama.</h4>

            {/*<ShowInvalidCredentials hasLoginFailed={this.state.hasLoginFailed}/>*/}
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
            {/*<ShowLoginSuccessMessage showSuccessMessage={this.state.showSuccessMessage}/>*/}

            <div className="btn btn-danger">
              <GoogleLogin
                clientId={config.googleclientid}
                buttonText="LOGIN"
                onSuccess={this.responseGoogle}
                onFailure={this.responseGoogle}
              />
            </div>
          </div>
        </div>
      </div>
    );
  }
}
export default LoginComponent;
