import React, { Component } from 'react'

import { withRouter } from "react-router-dom";
import MarketDataService from "../../api/market/MarketDataService.js"

import './Market.css';
import toBackend from '../../project_frontend/toBackend/postBackend.js'

// This is the marketplace browsing component
class MarketComponent extends Component {

  constructor(props){
    super(props)

    this.state = {
      item_category: '',
      search_words: ''
    }
  

  this.handleChange = this.handleChange.bind(this)
  this.submitPost = this.submitPost.bind(this)
  }
  render() {
        // Simply return a heading, and div that will contain the posts
       let retVal = (
        
         
      <div>
          <div className="Search_item">
          <form onSubmit={this.submitPost} refs="form">
                <select required name="item_category" className="inputnot" onChange={this.handleChange} value={this.state.item_category}>
                  <option value="">Choose Category</option>
                  <option value="All">All</option>
                  <option value="Exceptionally Random">Exceptionally Random</option>
                  <option value="Ridiculously Complicated">Ridiculously Complicated</option>
                  <option value="Annoyingly Unnexplained">Annoyingly Unnexplained</option>
                  <option value="Disturbingly Simple">Disturbingly Simple</option>
                  <option value="Spectularly Failing">Spectacularly Failing</option>
                </select>
               
                <div class="input-icons"> 
                <input type="text" name="search_words" className="inputfield"  value={this.state.search_words} onChange={this.handleChange} />
               
                <i class="icon">< input type="image" src={require("./search.png")} value="Submit" border="0" alt="Submit" /></i>
                
          </div>
          </form>
          
        </div>
        

                <h1 className="marketTitle">Browse Marketplace</h1>
                <div className="container">
                    <Items history={this.props.history}></Items>
                </div>
            </div>
        );
       return retVal;
    }

     handleChange(event){
      event.preventDefault();
      this.setState(
        {
          [event.target.name] : event.target.value
        }
      )
    }
  
  
    submitPost(event){
      toBackend.searchItemBackend(this.state.search_words, this.state.item_category);
      
      this.props.history.push('/market/searchBy');
    }

}

// Helper class to render the post rows
class Items extends Component {

    constructor(props) {
        super(props);
        // State stores the posts from backend
        this.state = {
            backPostings: [[]],
        }
        this.refreshPosts()
    }
    
    render() {
        var retVal = [];
        // loop through the postings from backend
        for (var r = 0; r < this.state.backPostings.length; r++) {
            let postId = this.state.backPostings[r][0];
            // Append the row of post information
            retVal.push(
                <div className="posting container" onClick={() => this.routeChange(postId)}>
                    <span className="postTitle"><img src={'post_images/' + this.state.backPostings[r][0] + '.jpg'}></img>{this.state.backPostings[r][1]}</span> <br></br>
                    <span className="postDescription">{this.state.backPostings[r][2]}</span> <br></br>
                    <span className="postPrice">{this.state.backPostings[r][4]}</span> <br></br>
                    <span className="postSeller">{this.state.backPostings[r][3]}</span> <br></br>

                    <br></br>
                </div>
               

            );
        }


        return retVal;
    }
    // Method for when a user clicks on a post, route them to post page
    routeChange(x) {
        this.props.history.push("/posts/" + x);
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



export default withRouter(MarketComponent)
