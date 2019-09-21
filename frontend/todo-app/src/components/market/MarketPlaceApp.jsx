import React, { Component } from 'react'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'

import HeaderComponent from '../todo/HeaderComponent.jsx'
import FooterComponent from '../todo/FooterComponent.jsx'
import MarketComponent from './MarketComponent'

import ChatComponent from './ChatComponent'

import HomeComponent from './HomeComponent'


import PostComponent from './PostComponent.jsx';
import AccountComponent from './AccountComponent.jsx'

import AuthenticatedRoute from '../todo/AuthenticatedRoute.jsx'
import LoginComponent from '../todo/LoginComponent.jsx'
import ListTodosComponent from '../todo/ListTodosComponent.jsx'
import LogoutComponent from '../todo/LogoutComponent.jsx'
import WelcomeComponent from '../todo/WelcomeComponent.jsx'
import TodoComponent from '../todo/TodoComponent.jsx'
import Post_item from '../../project_frontend/pages/Post_item.jsx';
import SearchComponent from './SearchComponent.jsx';
//   <AuthenticatedRoute path="/posts/searchBy" exact component={SearchComponent} />
//<AuthenticatedRoute path="/posts/searchBy/:description/:item_category" exact component={SearchComponent} />
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
                            <AuthenticatedRoute path="/market/searchBy/:searchDescription/:searchCategory" exact component={SearchComponent} />
                            <AuthenticatedRoute path="/market/:postID" exact component={PostComponent} />
                            <AuthenticatedRoute path="/chat" exact component={ChatComponent} />

                            <Route path="/" exact component={LoginComponent} />
                            <Route path="/login" component={LoginComponent} />
                            <AuthenticatedRoute path="/post" component={Post_item} />
                            <AuthenticatedRoute path="/welcome/:name" component={WelcomeComponent} />
                            <AuthenticatedRoute path="/account" component={AccountComponent} />
                            <AuthenticatedRoute path="/todos/:id" component={TodoComponent} />
                            <AuthenticatedRoute path="/todos" component={ListTodosComponent} />
                            <Route path="/logout" component={LogoutComponent} />
                            <Route path="*" exact component={LoginComponent} />

                        </Switch>
                        <FooterComponent/>
                    </>
                </Router>
            </div>
        )
    }
}

export default MarketPlaceApp
