import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { withRouter } from "react-router-dom";

var allPostings = [
    ["1", "Confetti", "Beautiful multicolored confetti. Used but like new.", "georgemichael99", "$20"],
    ["2", "Green Capsicum", "Giant green capsicum. Found it at the beach. Still contains some sand.", "DONNYT1946", "$100"],
    ["3", "Small Blue Star", "Blue star, fell from the sky into my backyard. Fits in pocket. Still warm.", "not_an_alien", "$0.50"],
    ["4", "Vines - 50ft", "Green climbing vines. Organic, just cut. Perfect for a wedding.", "tree_hater", "$25.47"]
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