//Reference: https://www.npmjs.com/package/react-google-login

import React, { Component } from 'react'
import AuthenticationService from './AuthenticationService.js'
import GoogleLogin from 'react-google-login';
import config from './config.json';
//import MarketDataService from '../../api/market/MarketDataService.js';
//import {googleauth} from '../../api/market/MarketDataService.js';
class LoginComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            username: 'sept',
            password: '',
            hasLoginFailed: false,
            showSuccessMessage: false
        }
        // this.handleUsernameChange = this.handleUsernameChange.bind(this)
        // this.handlePasswordChange = this.handlePasswordChange.bind(this)
        this.handleChange = this.handleChange.bind(this)
        this.loginClicked = this.loginClicked.bind(this)

        this.responseGoogle = this.responseGoogle.bind(this);

    }
    //bunch of crap

    async responseGoogle(res) {

        console.log('THIS IS THE GOOGLE INFO', res);

        var idToken = res.getAuthResponse().id_token;

        AuthenticationService
            .executeGoogleJwtAuthenticationService(idToken)
            .then((response) => {
                AuthenticationService.registerSuccessfulLoginForJwt(res.getBasicProfile().getEmail().substring(0, 8), response.data.token)
                this.props.history.push(`/home/${this.state.username}`)
            }).catch(() => {
                this.setState({ showSuccessMessage: false })
                this.setState({ hasLoginFailed: true })
            })

  //      await this.props.googleauth(res.accessToken);

    }

    handleChange(event) {
        //console.log(this.state);
        this.setState(
            {
                [event.target.name]
                    : event.target.value
            }
        )
    }

    // handleUsernameChange(event) {
    //     console.log(event.target.name);
    //     this.setState(
    //         {
    //             [event.target.name]
    //               :event.target.value
    //         }
    //     )
    // }

    // handlePasswordChange(event) {
    //     console.log(event.target.value);
    //     this.setState({password:event.target.value})
    // }

    loginClicked() {
        // sept,dummy
        // if(this.state.username==='sept' && this.state.password==='dummy'){
        //     AuthenticationService.registerSuccessfulLogin(this.state.username,this.state.password)
        //     this.props.history.push(`/welcome/${this.state.username}`)
        //     //this.setState({showSuccessMessage:true})
        //     //this.setState({hasLoginFailed:false})
        // }
        // else {
        //     this.setState({showSuccessMessage:false})
        //     this.setState({hasLoginFailed:true})
        // }

        // AuthenticationService
        // .executeBasicAuthenticationService(this.state.username, this.state.password)
        // .then(() => {
        //     AuthenticationService.registerSuccessfulLogin(this.state.username,this.state.password)
        //     this.props.history.push(`/welcome/${this.state.username}`)
        // }).catch( () =>{
        //     this.setState({showSuccessMessage:false})
        //     this.setState({hasLoginFailed:true})
        // })
        AuthenticationService
            .executeJwtAuthenticationService(this.state.username, this.state.password)
            .then((response) => {
                AuthenticationService.registerSuccessfulLoginForJwt(this.state.username, response.data.token)
                this.props.history.push(`/home/${this.state.username}`)
            }).catch(() => {
                this.setState({ showSuccessMessage: false })
                this.setState({ hasLoginFailed: true })
            })

    }

    render() {
        return (
            <div>
                <h1>Bananarama Marketplace</h1>
                <div className="container">
                
                    {/*<ShowInvalidCredentials hasLoginFailed={this.state.hasLoginFailed}/>*/}
                    {this.state.hasLoginFailed && <div className="alert alert-warning">Invalid Credentials or something is wrong</div>}
                    {this.state.showSuccessMessage && <div>Login Sucessful</div>}
                    {/*<ShowLoginSuccessMessage showSuccessMessage={this.state.showSuccessMessage}/>*/}
                    <h3>Please Login with your RMIT Google Account</h3>
                    <GoogleLogin
                      clientId={config.googleclientid}
                      buttonText="LOGIN"
                      onSuccess={this.responseGoogle}
                      onFailure={this.responseGoogle}
                    />
                </div>
            </div>
        )
    }
}
export default LoginComponent
