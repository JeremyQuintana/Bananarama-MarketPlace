import React, { Component } from 'react'
import { Route, Redirect } from 'react-router-dom'
import AuthenticationService from '../DataServices/AuthenticationService.js'

class AuthenticatedRoute extends Component {
  //component that simply checks if the user is logged in and if not is sent
  //to the login page
  render() {
      if (AuthenticationService.isUserLoggedIn()) {
          return <Route {...this.props} />
      } else {
          return <Redirect to={{
              pathname: "/login",
              state: { loginError: true }
            }} />
      }

  }
}

export default AuthenticatedRoute
