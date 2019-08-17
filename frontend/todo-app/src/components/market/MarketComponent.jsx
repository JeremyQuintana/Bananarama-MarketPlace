import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { withRouter } from "react-router-dom";

var allPostings = [
    ["1", "Item 1 Title", "Item 1 Description", "Item 1 Seller", "$Item 1 Price"],
    ["2", "Item 2 Title", "Item 2 Description", "Item 2 Seller", "$Item 2 Price"],
    ["3", "Item 3 Title", "Item 3 Description", "Item 3 Seller", "$Item 3 Price"],
    ["4", "Item 4 Title", "Item 4 Description", "Item 4 Seller", "$Item 4 Price"]
];

class MarketComponent extends Component {
    render() {
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

class Items extends Component {
    
    render() {
        var retVal = [];
        for (var r = 0; r < allPostings.length; r++) {
            let postId = allPostings[r][0];
            retVal.push(
                <div className="posting container" onClick={() => this.routeChange(postId)}>
                    <span className="postTitle"><img src={'post_images/' + allPostings[r][0] + '.jpg'}></img>{allPostings[r][1]}</span> <br></br>
                    <span className="postDescription">{allPostings[r][2]}</span> <br></br>
                    <span className="postPrice">{allPostings[r][4]}</span> <br></br>
                    <span className="postSeller">{allPostings[r][3]}</span> <br></br>

                    <br></br>
                </div>
               

            );
        }


        return retVal;
    }

    routeChange(x) {
        this.props.history.push("/posts/" + x);
    }
}



export default withRouter(MarketComponent)