import React, { Component } from 'react'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'

import HeaderComponent from './HeaderComponent.jsx'
import MarketComponent from './MarketComponent'
import ChatComponent from './ChatComponent'
import HomeComponent from './HomeComponent'
import PostComponent from './PostComponent.jsx';
import AccountComponent from './AccountComponent.jsx'

import AuthenticatedRoute from './AuthenticatedRoute.jsx'
import LoginComponent from './LoginComponent.jsx'
import PostItem from './PostItem.jsx';
import "../css/Market.css";

// This component organises all the other components together
class MarketPlaceApp extends Component {
    render() {
      // Return the divs with the header, footer and routes for different pages
      return (
        <div className="MarketPlaceApp">
          <Router>
            <>
              <HeaderComponent />
              <Switch>
                <AuthenticatedRoute path = "/home/:name" exact component = {HomeComponent}/>
                <AuthenticatedRoute path="/market" exact component={MarketComponent} />
                <AuthenticatedRoute path="/market/searchBy/:searchDescription/:searchCategory" exact component={MarketComponent} />
                <AuthenticatedRoute path="/market/searchBy/:searchDescription/:searchCategory/:searchSort" exact component={MarketComponent} />
                <AuthenticatedRoute path="/market/searchBy//:searchCategory/:searchSort" exact component={MarketComponent} />
                <AuthenticatedRoute path="/market/searchBy//:searchCategory" exact component={MarketComponent} />
                <AuthenticatedRoute path="/market/:postID" exact component={PostComponent} />
                <AuthenticatedRoute path="/chat" exact component={ChatComponent} />
                <AuthenticatedRoute path="/chat/:receiverId" exact component={ChatComponent} />
                <AuthenticatedRoute path="/post" component={PostItem} />
                <AuthenticatedRoute path="/account" component={AccountComponent} />

                <Route path="/" exact component={LoginComponent} />
                <Route path="/login" component={LoginComponent} />
                <Route path="*" exact component={LoginComponent} />
              </Switch>
            </>
          </Router>
        </div>
        )
    }
}

export default MarketPlaceApp
