import React, { Component } from 'react'

import { withRouter } from "react-router-dom";
import MarketDataService from "../../api/market/MarketDataService.js"

class MarketComponent extends Component {
    render() {
        // Simply return a header, and div that will contain the posts
        let retVal = (
            <div>
                <h1 className="marketTitle">Browse Marketplace</h1>
                <div className="container">
                    <Items history={this.props.history}></Items>
                </div>
            </div>
        );
        return retVal;
    }


}

// Inner class to render the post rows
class Items extends Component {

    constructor(props) {
        super(props);
        // State stores the posts from backend
        this.state = {
            backPostings: [[]],
        }
        this.refreshPosts()
    }
    
    render() {
        var retVal = [];
        // loop through the postings from backend
        for (var r = 0; r < this.state.backPostings.length; r++) {
            let postId = this.state.backPostings[r][0];
            // Append the row of post information
            retVal.push(
                <div className="posting container" onClick={() => this.routeChange(postId)}>
                    <span className="postTitle"><img src={'post_images/' + this.state.backPostings[r][0] + '.jpg'}></img>{this.state.backPostings[r][1]}</span> <br></br>
                    <span className="postDescription">{this.state.backPostings[r][2]}</span> <br></br>
                    <span className="postPrice">{this.state.backPostings[r][4]}</span> <br></br>
                    <span className="postSeller">{this.state.backPostings[r][3]}</span> <br></br>

                    <br></br>
                </div>
               

            );
        }


        return retVal;
    }
    // Method for when a user clicks on a post, route them to post page
    routeChange(x) {
        this.props.history.push("/posts/" + x);
    }
    // update the postings array with backend data
    refreshPosts() {

        MarketDataService.retrieveAllPosts().then(
            response => {
                
                this.setState({ backPostings: response.data })
            }
        ).catch(error => console.log("network error"));
    }

}



export default withRouter(MarketComponent)