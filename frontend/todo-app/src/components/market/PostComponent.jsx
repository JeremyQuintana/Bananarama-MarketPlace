import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { withRouter } from "react-router-dom";
import MarketComponent from "./MarketComponent.jsx";
import MarketDataService from "../../api/market/MarketDataService.js"

var allPostings = [
    ["1", "Confetti", "Beautiful multicolored confetti. Used but like new.", "georgemichael99", "$20"],
    ["2", "Green Capsicum", "Giant green capsicum. Found it at the beach. Still contains some sand.", "DONNYT1946", "$100"],
    ["3", "Small Blue Star", "Blue star, fell from the sky into my backyard. Fits in pocket. Still warm.", "not_an_alien", "$0.50"],
    ["4", "Vines - 50ft", "Green climbing vines. Organic, just cut. Perfect for a wedding.", "tree_hater", "$25.47"]
];

class PostComponent extends Component {


    constructor(props) {
        super(props);
        //this.allPostings = allPostings;
        
        
        this.state = {
            backPostings: [[]],
            // backPostings: allPostings,
        }
        this.refreshPosts()
        // console.log("HELLO");
        // console.log("hello" + this.state.backPostings);
        // console.log("hello")
    }

    render() {
        let retVal;
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
                    <div className="container postSeller"><Link to="chat/seller1">Contact Seller</Link></div>


                </div>
            );
        } else {
            retVal = null;
        }

        return retVal;
    }

    refreshPosts() {

        this.allPostings = MarketDataService.retrieveAllPosts().then(
            response => {
                //console.log(response.data);
                this.setState({ backPostings: response.data })
            }
        ).catch(error => console.log("network error"));
    }

}




export default PostComponent