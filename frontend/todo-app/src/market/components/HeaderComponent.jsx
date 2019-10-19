import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import AuthenticationService from "../DataServices/AuthenticationService.js"
import axios from 'axios'

class HeaderComponent extends Component {

    render() {
        axios.interceptors.request.use(
            (config) => {
                if (!(sessionStorage.getItem("authenticatedUser") == null)) {
                    config.headers.authorization = sessionStorage.getItem("jwtToken")
                }
                return config
            }
        )
        const isUserLoggedIn = AuthenticationService.isUserLoggedIn();
        //console.log(isUserLoggedIn);

        return (
            <header>
                <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                    <div><a href="https://www.rmit.edu.au/" className="navbar-brand">RMIT</a></div>
                    <ul className="navbar-nav">
                        {isUserLoggedIn && <li><Link className="nav-link" to={"/home/" + sessionStorage.getItem("authenticatedUser")}>Home</Link></li>}


                        {isUserLoggedIn && <li><Link className="nav-link" to="/post">Post</Link></li>}

                        {isUserLoggedIn && <li><Link className="nav-link" to="/market">Market</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link" to="/account">Account</Link></li>}


                    </ul>
                    <ul className="navbar-nav navbar-collapse justify-content-end">
                        {!isUserLoggedIn && <li><Link className="nav-link" to="/login">Login</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link" to="/login" onClick={AuthenticationService.logout}>Logout</Link></li>}
                    </ul>
                </nav>
            </header>
        )
    }
}

export default HeaderComponent
