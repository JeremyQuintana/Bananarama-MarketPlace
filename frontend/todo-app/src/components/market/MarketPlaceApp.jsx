import React, {Component} from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'

import HeaderComponent from '../todo/HeaderComponent.jsx'
import FooterComponent from '../todo/FooterComponent.jsx'
import MarketComponent from './MarketComponent'
import HomeComponent from './HomeComponent'
import PostComponent from './PostComponent.jsx';


class MarketPlaceApp extends Component {
    render() {
        return (
            <div className="MarketPlaceApp">
                <Router>
                    <>
                        <HeaderComponent/>
                        <Switch>
                           <Route path = "/market" exact component = {MarketComponent}/>
                           <Route path = "/home" exact component = {HomeComponent}/>

                           <Route path = "/posts/:postID" exact component = {PostComponent}/>
                        </Switch>
                        <FooterComponent/>
                    </>
                </Router>
                {/*<LoginComponent/>
                <WelcomeComponent/>*/}
            </div>
        )
    }
}

export default MarketPlaceApp