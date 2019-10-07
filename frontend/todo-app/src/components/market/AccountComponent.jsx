import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import ItemsComponent from "./ItemsComponent"



class AccountComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            pastPosts: [],
            currentPosts: [],
            deletedPosts: [],
            allChats: [],
          }
    }

    render() {
        let retVal = (
            <div className="container">
                <h1 className="marketTitle">Account</h1>
                <div className="container">
                    <h1 className="marketTitle">Your Past Sales</h1>
                    <div className="container">
                        <ItemsComponent history={this.props.history} backPostings={this.state.pastPosts}></ItemsComponent>
                    </div>
                </div>
                <div className="container">
                    <h1 className="marketTitle">Your Current Sales</h1>
                    <div className="container">
                        <ItemsComponent history={this.props.history} backPostings={this.state.currentPosts}></ItemsComponent>
                    </div>
                </div>
                <div className="container">
                    <h1 className="marketTitle">Your Deleted Sales</h1>
                    <div className="container">
                        <ItemsComponent history={this.props.history} backPostings={this.state.deletedPosts}></ItemsComponent>
                    </div>
                </div>
                {/*<div className="container">
                    <h1 className="marketTitle">Your Chats</h1>
                    <div className="container">
                        <ChatsComponent history={this.props.history} backPostings={this.state.allChats}></ChatsComponent>
                    </div>
                </div>*/}
            </div>
        );
        return retVal;
    }

}

export default withRouter(AccountComponent);
