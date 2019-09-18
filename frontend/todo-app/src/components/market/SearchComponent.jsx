import React, { Component } from 'react'

import { withRouter } from "react-router-dom";
import MarketDataService from "../../api/market/MarketDataService.js"
import MarketComponent from './MarketComponent.jsx';


// This is the marketplace browsing component
class SearchComponent extends Component {

  render() {
        // Simply return a heading, and div that will contain the posts
        let retVal = (
          <div>
                <h1 className="searchTitle">Results</h1>
                <div className="container">
                    <NewItems history={this.props.history}></NewItems>
                </div>
            </div>
        );
        return retVal;
    }
  }

 
// Helper class to render the post rows
class NewItems extends Component {

    constructor(props) {
        super(props);
        // State stores the posts from backend
        this.state = {
            backSearchPostings: [[]],
        }
        this.refreshsearchPosts()
    }
    
  
        render() {
            var retVal = [];
            
            //if(Object.keys(this.state.backSearchPostings.length == 0))
            //(this.state.backSearchPostings[0] OR this.state.errors) aggh wtf
       /*     if(this.state.backSearchPostings[0])
            {
            <div>
            <h3 className="NoresultsTitle">Sorry no items matching the description could be found</h3>
                </div>
        }
            else {*/
            // loop through the postings from backend
            for (var r = 0; r < this.state.backSearchPostings.length; r++) {
                let postId = this.state.backSearchPostings[r].id;
                let description = this.state.backSearchPostings[r].description;
                let item_category = this.state.backSearchPostings[r].item_category;

                // Append the row of post information
                retVal.push(
                    <div className="posting container" onClick={() => this.routeChange(description, item_category, postId)}>
                        <span className="postTitle"><img src={'post_images/' + this.state.backSearchPostings[r].photo + '.jpg'}></img>{this.state.backSearchPostings[r].title}</span> <br></br>
                        <span className="postDescription">{this.state.backSearchPostings[r].description}</span> <br></br>
                        <span className="postPrice">{this.state.backSearchPostings[r].price}</span> <br></br>
                        <span className="postSeller">{this.state.backSearchPostings[r].ownerId}</span> <br></br>
    
                        <br></br>
                    </div>
                   
    
                );
                }
            

        return retVal;
    }
    // Method for when a user clicks on a post, route them to post page
    routeChange(description, item_category, x) {
        this.props.history.push(`/posts/searchBy/${description}${item_category}/` + x);
    }
   
    // update the postings array with backend data
    /*
    refreshsearchPosts() {

        MarketDataService.retrievesearchByPosts(this.props.match.params.olddescription, this.props.match.params.oldcategory).then(
            response => {
                
                this.setState({ backSearchPostings: response.data })
            }
        ).catch(error => console.log("network error WTF!"));
    }*/

    refreshsearchPosts()  {

    MarketDataService.retrieveAllPosts().then(
        response => {
            var i;
            var found = false;
            console.log(this.props.match.params.item_category) 
            for(i = 0; i < response.data.length && !found; i++){
                if(response.data[i].item_category == "Exceptionally Random" ){
                    found = true;
                }
            }
            if(found == true){
                this.setState({ backSearchPostings: response.data[i-1] })
            }
        }
    ).catch(error => console.log("network error WTFFF"));
}


}



export default withRouter(SearchComponent)


