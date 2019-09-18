import React, { Component } from 'react'
import { Link } from 'react-router-dom'

import MarketDataService from "../../api/market/MarketDataService.js"

// This class is for an individual post's page
class PostComponent extends Component {


    constructor(props) {
        super(props);

        // State stores the posts from backend
        this.state = {
            postInfo: null,
        }
        this.retrieveItemInfo()

    }

    render() {
        let retVal;
        // get the row from the backend array, based on the postID param in props
        // create a div with all the singular posts information
        if (this.state.postInfo != null) {
            retVal = (

                <div>
                    <h1 className="marketTitle">{this.state.postInfo.title}</h1>
                    <div className="container postDescription">
                        <img src={'../post_images/' + this.state.postInfo.photo + '.jpg'}></img>
                        {this.state.postInfo.description}
                    </div>
                    <div className="container postPrice">
                        {this.state.postInfo.price}
                    </div>
                    <div className="container postSeller">
                        {this.state.postInfo.ownerId}
                    </div>
                    <div className="container postSeller"><Link to="/chat/" action="replace">Contact Seller</Link></div>


                </div>
            );
        } else {
            retVal = null;
        }

        return retVal;
    }

    // update to mitches code
    // instead of retrieving all posts frontend and doing a search use backed to send only one item of given id
    retrieveItemInfo() {

        MarketDataService.retrieveSpecificPost(this.props.match.params.postID).then(
            response => {
                this.setState({ postInfo: response.data });
            }
        ).catch(error => console.log("network error"));
    }

}




export default PostComponent
