import React, { Component } from 'react'
import { Link } from 'react-router-dom'

import MarketDataService from "../../api/market/MarketDataService.js"


class PostComponent extends Component {


    constructor(props) {
        super(props);
        
        // State stores the posts from backend
        this.state = {
            backPostings: [[]],
        }
        this.refreshPosts()

    }

    render() {
        let retVal;
        // get the row from the backend array, based on the postID param in props
        // create a div with all the singular posts information
        if (parseInt(this.props.match.params.postID) - 1 < this.state.backPostings.length && parseInt(this.props.match.params.postID) - 1 >= 0) {
            retVal = (

                <div>
                    <h1 className="marketTitle">{this.state.backPostings[parseInt(this.props.match.params.postID) - 1][1]}</h1>
                    <div className="container postDescription">
                        <img src={'../post_images/' + this.state.backPostings[parseInt(this.props.match.params.postID) - 1][0] + '.jpg'}></img>
                        {this.state.backPostings[parseInt(this.props.match.params.postID) - 1][2]}
                    </div>
                    <div className="container postPrice">
                        {this.state.backPostings[parseInt(this.props.match.params.postID) - 1][4]}
                    </div>
                    <div className="container postSeller">
                        {this.state.backPostings[parseInt(this.props.match.params.postID) - 1][3]}
                    </div>
                    <div className="container postSeller"><Link to="/chat/" action="replace">Contact Seller</Link></div>


                </div>
            );
        } else {
            retVal = null;
        }

        return retVal;
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




export default PostComponent