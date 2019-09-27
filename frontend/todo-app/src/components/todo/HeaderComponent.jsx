import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import AuthenticationService from './AuthenticationService.js'
import axios from 'axios'

class HeaderComponent extends Component {
    render() {
        const isUserLoggedIn = AuthenticationService.isUserLoggedIn();
        //console.log(isUserLoggedIn);

        return (
            <header>
                <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                    <div><a href="https://www.rmit.edu.au/" className="navbar-brand">RMIT</a></div>
                    <ul className="navbar-nav">
                        {isUserLoggedIn && <li><Link className="nav-link" to="/home/sept">Home</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link" to="/todos">Todos</Link></li>}

                        {isUserLoggedIn && <li><Link className="nav-link" to="/post">Post</Link></li>}

                        {isUserLoggedIn && <li><Link className="nav-link" to="/market">Market</Link></li>}


                    </ul>
                    <ul className="navbar-nav navbar-collapse justify-content-end">
                        {!isUserLoggedIn && <li><Link className="nav-link" to="/login">Login</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link" to="/logout" onClick={AuthenticationService.logout}>Logout</Link></li>}
                    </ul>
                </nav>
            </header>
        )
    }

    componentDidMount(){
      axios.interceptors.request.use(
          (config) => {
              if (!(sessionStorage.getItem("authenticatedUser") === null)) {
                  config.headers.authorization = sessionStorage.getItem("jwtToken")
              }
              return config
          }
      )
    }
}

export default HeaderComponent
