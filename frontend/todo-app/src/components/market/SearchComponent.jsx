import React, { Component } from 'react'

import { withRouter } from "react-router-dom";
import MarketDataService from "../../api/market/MarketDataService.js"



// This is the marketplace browsing component
class SearchComponent extends Component {

  render() {
        // Simply return a heading, and div that will contain the posts
        let retVal = (
          <div>
                <h1 className="searchTitle">Results</h1>
                <div className="container">
                    <Items history={this.props.history}></Items>
                </div>
            </div>
        );
        return retVal;
    }
  }


// Helper class to render the post rows
class Items extends Component {

    constructor(props) {
        super(props);
        // State stores the posts from backend
        this.state = {
            backSearchPostings: [[]],
        }
        this.refreshPosts()
    }
    
    render() {
        var retVal = [];
        // loop through the postings from backend
        for (var r = 0; r < this.state.backSearchPostings.length; r++) {
            let postId = this.state.backSearchPostings[r][0];
            // Append the row of post information
            retVal.push(
                <div className="posting container" onClick={() => this.routeChange(postId)}>
                    <span className="postTitle"><img src={'post_images/' + this.state.backSearchPostings[r][0] + '.jpg'}></img>{this.state.backSearchPostings[r][1]}</span> <br></br>
                    <span className="postDescription">{this.state.backSearchPostings[r][2]}</span> <br></br>
                    <span className="postPrice">{this.state.backSearchPostings[r][4]}</span> <br></br>
                    <span className="postSeller">{this.state.backSearchPostings[r][3]}</span> <br></br>

                    <br></br>
                </div>
               

            );
        }


        return retVal;
    }
    // Method for when a user clicks on a post, route them to post page
    routeChange(x) {
        this.props.history.push("/posts/searchBy/" + x);
    }
    // update the postings array with backend data
    refreshPosts() {

        MarketDataService.retrievesearchByPosts().then(
            response => {
                
                this.setState({ backSearchPostings: response.data })
            }
        ).catch(error => console.log("network error"));
    }

}



export default withRouter(SearchComponent)
