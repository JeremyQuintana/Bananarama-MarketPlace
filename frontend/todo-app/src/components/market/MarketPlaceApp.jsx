import React, {Component} from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'

import HeaderComponent from '../todo/HeaderComponent.jsx'
import FooterComponent from '../todo/FooterComponent.jsx'
import MarketComponent from './MarketComponent'


class MarketPlaceApp extends Component {
    render() {
        return (
            <div className="MarketPlaceApp">
                <Router>
                    <>
                        <HeaderComponent/>
                        <Switch>
                           <Route path = "/" exact component = {MarketComponent}/>
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