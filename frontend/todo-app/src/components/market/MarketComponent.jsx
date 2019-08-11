import React, { Component } from 'react'

class MarketComponent extends Component {


    render() {
        let retVal = (
            <div>
                <h1>Browse Marketplace</h1>
                <div className="container">
                    <Items></Items>
                </div>
            </div>
        );
        return retVal;
    }

    
}

class Items extends Component {
    render(){
        return (
            <div className="container">
            <img src = '/rmit.jpg' height='50px'></img><span>Item 1 Title</span> <br></br>
            <span>Item 1 Description</span> <br></br>
            <span>Item 1 seller</span> <br></br>
             <br></br>
             <img src = '/rmit.jpg' height='50px'></img><span>Item 2 Title</span> <br></br>
            <span>Item 2 Description</span> <br></br>
            <span>Item 2 seller</span> <br></br>
             <br></br>
            </div>

        )
    }
}

export default MarketComponent