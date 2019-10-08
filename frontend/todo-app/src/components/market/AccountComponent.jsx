import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import ItemsComponent from "./ItemsComponent"
import MarketDataService from "../../api/market/MarketDataService.js"


class AccountComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            pastPosts: [],
            currentPosts: [],
            deletedPosts: [],
            allChats: [],
        }

        this.refreshPastPosts();
        this.refreshCurrentPosts();
        this.refreshDeletedPosts();
        //this.refreshChatHistory();
    }

    render() {
        var pastPostItems = <div className="container alert alert-warning">No past posts found!</div>
        if (this.state.pastPosts.length != 0) {
            pastPostItems = <ItemsComponent history={this.props.history} backPostings={this.state.pastPosts}></ItemsComponent>
        }
        var currentPostItems = <div className="container alert alert-warning">No current posts found!</div>
        if (this.state.currentPosts.length != 0) {
            currentPostItems = <ItemsComponent history={this.props.history} backPostings={this.state.currentPosts}></ItemsComponent>
        }
        var deletedPostItems = <div className="container alert alert-warning">No deleted posts found!</div>
        if (this.state.deletedPosts.length != 0) {
            deletedPostItems = <ItemsComponent history={this.props.history} backPostings={this.state.deletedPosts}></ItemsComponent>
        }
        /*var chatHistory =  <div className="container alert alert-warning">No chats found!</div>
        if(this.state.pastPosts != null){
            chatHistory = <ChatListComponent history={this.props.history} chats={this.state.allChats}></ChatListComponent>
        }*/

        let retVal = (
            <div className="container">
                <h1 className="marketTitle">Account</h1>
                <hr></hr>
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
                {/*<div className="container">
                    <h1 className="marketTitle">Your Chats</h1>
                    <div className="container">
                        {chatHistory}
                    </div>
                </div>*/}
            </div>
        );
        return retVal;
    }

    refreshPastPosts() {
        MarketDataService.retrievePastPostsBySeller(sessionStorage.getItem('authenticatedUser')).then(
            response => {
                this.setState({ pastPosts: response.data })
            }
        ).catch(error => console.log("network error: ", error));
    }

    refreshCurrentPosts() {
        MarketDataService.retrieveCurrentPostsBySeller(sessionStorage.getItem('authenticatedUser')).then(
            response => {
                this.setState({ currentPosts: response.data })
            }
        ).catch(error => console.log("network error: ", error));
    }

    refreshDeletedPosts() {
        MarketDataService.retrieveDeletedPostsBySeller(sessionStorage.getItem('authenticatedUser')).then(
            response => {
                this.setState({ deletedPosts: response.data })
            }
        ).catch(error => console.log("network error: ", error));
    }

    /*refreshChatHistory() {
        MarketDataService.retrieveChatsByUser(sessionStorage.getItem('authenticatedUser')).then(
            response => {
                this.setState({ allChats: response.data })
            }
        ).catch(error => console.log("network error: ", error));
    }*/
}

export default withRouter(AccountComponent);
