import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { withRouter } from "react-router-dom";

var allPostings = [
    ["1", "Item 1 Title", "Item 1 Description", "Item 1 Seller", "$Item 1 Price"],
    ["2", "Item 2 Title", "Item 2 Description", "Item 2 Seller", "$Item 2 Price"],
    ["3", "Item 3 Title", "Item 3 Description", "Item 3 Seller", "$Item 3 Price"],
    ["4", "Item 4 Title", "Item 4 Description", "Item 4 Seller", "$Item 4 Price"]
];

class PostComponent extends Component {

    constructor(props) {
        super(props)
    }

    render() {
        let retVal = (
            
            <div>
                <h1 className="marketTitle">{allPostings[parseInt(this.props.match.params.postID)-1][1]}</h1>
                <div className="container postDescription">
                    <img src={'../post_images/' + allPostings[parseInt(this.props.match.params.postID)-1][0] + '.jpg'}></img>
                    {allPostings[parseInt(this.props.match.params.postID)-1][2]}
                </div>
                <div className="container postPrice">
                    {allPostings[parseInt(this.props.match.params.postID)-1][4]}
                </div>
                <div className="container postSeller">
                    {allPostings[parseInt(this.props.match.params.postID)-1][3]}
                </div>
                <div className="container postSeller"><Link to="chat/seller1">Contact Seller</Link></div>


            </div>
        );
        return retVal;
    }


}




export default PostComponent