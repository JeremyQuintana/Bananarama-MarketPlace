import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import ItemsComponent from "./ItemsComponent"
import ChatListComponent from "./ChatListComponent"
import "../css/Account.css"

import MarketDataService from "../DataServices/MarketDataService.js"
import ChatService from "../DataServices/ChatService.js"


class AccountComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            pastPosts: [],
            currentPosts: [],
            deletedPosts: [],
            allChats: [],
        }

        this.refreshPosts("SOLD");
        this.refreshPosts("AVAILABLE");
        this.refreshPosts("DELETED");
        this.refreshChatHistory();
    }

    render() {
        var pastPostItems = <div className="container alert alert-warning">No past posts found!</div>
        if (this.state.pastPosts.length !== 0) {
            pastPostItems = <ItemsComponent history={this.props.history} backPostings={this.state.pastPosts}></ItemsComponent>
        }
        var currentPostItems = <div className="container alert alert-warning">No current posts found!</div>
        if (this.state.currentPosts.length !== 0) {
            currentPostItems = <ItemsComponent history={this.props.history} backPostings={this.state.currentPosts}></ItemsComponent>
        }
        var deletedPostItems = <div className="container alert alert-warning">No deleted posts found!</div>
        if (this.state.deletedPosts.length !== 0) {
            deletedPostItems = <ItemsComponent history={this.props.history} backPostings={this.state.deletedPosts}></ItemsComponent>
        }
        var chatHistory =
          <div className="container alert alert-warning">No chats found!</div>
        if(this.state.allChats.length > 1){

            chatHistory = <ChatListComponent history={this.props.history} chats={this.state.allChats} historyMode = {true}></ChatListComponent>
        }

        let retVal = (
          <div className="account">
            <h1 className="marketTitle">Account</h1>
            <div className="container">
              <h3 className="marketTitle">Your Past Sales</h3>
              <div className="container">
                {pastPostItems}
              </div>
            </div>
            <div className="container">
              <h3 className="marketTitle">Your Current Sales</h3>
              <div className="container">
                {currentPostItems}
              </div>
            </div>
            <div className="container">
              <h3 className="marketTitle">Your Deleted Sales</h3>
              <div className="container">
                {deletedPostItems}
              </div>
            </div>
            <div className="container">
              <h3 className="marketTitle">Your Chats</h3>
              <div className="container">
                {chatHistory}
              </div>
            </div>
          </div>
        );
        return retVal;
    }

    refreshPosts(status) {
        MarketDataService.retrievePostsBySeller(sessionStorage.getItem('authenticatedUser'), status).then(
            response => {
                switch (status)
                {
                    case "AVAILABLE" : this.setState({ currentPosts: response.data }); break;
                    case "SOLD" : this.setState({ pastPosts: response.data });      break;
                    case "DELETED" : this.setState({ deletedPosts: response.data }); break;
                }
            }
        );
    }

    refreshChatHistory(){
        ChatService.userList(sessionStorage.getItem('authenticatedUser')).then(
            (response) => {
                this.setState({allChats: response.data});
                // FOR TESTING:
                /*this.setState({allChats: [
                    { senderId: 's1234567', receiverId: 's7654321' },
                    { senderId: 's1234567', receiverId: 's5678910' }]});*/
            }
        );
    }
}

export default withRouter(AccountComponent);
