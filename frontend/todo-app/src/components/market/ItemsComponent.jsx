
import React, { Component } from 'react'

import { withRouter } from "react-router-dom";
import './Market.css';

// Helper class to render  the post rows
class ItemsComponent extends Component {
    //solution to making itmes component rerender on state change from parent Component
    //found at https://medium.com/p/387720c3cff8/responses/show
      static getDerivedStateFromProps(props, state){
        if (props.backPostings !== state.backPostings) {
          return {backPostings: props.backPostings};
        }
        return null;
      }
    
      constructor(props) {
          super(props);
          // State stores the posts from backend
          this.state = {
              backPostings: this.props.backPostings,
          }
      }
    
      render() {
          var retVal = [];
          
          // loop through the postings from backend
          for (var r = 0; r < this.state.backPostings.length; r++) {
              let postId = this.state.backPostings[r].id;
              var receivedDescription = this.state.backPostings[r].description
              if(receivedDescription != null){
                var trimmedDescription = receivedDescription.length > 100 ? receivedDescription.substring(0,97) + "..." : receivedDescription;
              }
              // Append the row of post information
              retVal.push(
                  <div className="posting container" onClick={() => this.routeChange(postId)}>
                      <span className="postTitle"><img src={'post_images/' + this.state.backPostings[r].photo + '.jpg'}></img>{this.state.backPostings[r].title}</span> <br></br>
                      <span className="postCategory">{this.state.backPostings[r].category}</span> <br></br>
                      <span className="postDescription">{trimmedDescription}</span> <br></br>
                      <span className="postPrice">${this.state.backPostings[r].price}</span> <br></br>
                      <span className="postSeller">{this.state.backPostings[r].ownerId}</span> <br></br>
    
                      <br></br>
                  </div>
    
            );
          }
    
    
          return retVal;
      }
    
      // Method for when a user clicks on a post, route them to post page
      routeChange(x) {
          this.props.history.push("/market/" + x);
      }
    
    }
export default ItemsComponent