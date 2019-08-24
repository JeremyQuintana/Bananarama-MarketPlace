import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { withRouter } from "react-router-dom";
import MarketDataService from "../../api/market/MarketDataService.js"

// Old hardcoded array for dev
// var allPostings = [
//     ["1", "Confetti", "Beautiful multicolored confetti. Used but like new.", "georgemichael99", "$20"],
//     ["2", "Green Capsicum", "Giant green capsicum. Found it at the beach. Still contains some sand.", "DONNYT1946", "$100"],
//     ["3", "Small Blue Star", "Blue star, fell from the sky into my backyard. Fits in pocket. Still warm.", "not_an_alien", "$0.50"],
//     ["4", "Vines - 50ft", "Green climbing vines. Organic, just cut. Perfect for a wedding.", "tree_hater", "$25.47"]
// ];

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
                //console.log(response.data);
                this.setState({ backPostings: response.data })
            }
        ).catch(error => console.log("network error"));
    }

}



export default withRouter(MarketComponent)