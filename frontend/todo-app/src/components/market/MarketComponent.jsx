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
      backPostings: [],
      description: '',
      category: '',
      sort: ''
    }

    var searchDescription = this.props.match.params.searchDescription;
    var searchCategory = this.props.match.params.searchCategory;
    var searchSort = this.props.match.params.searchSort;

    if (searchDescription == null && searchCategory == null && searchSort == null){
      this.refreshPosts();
    } else if (searchCategory != null && searchSort == null){
      this.refreshsearchPosts(searchDescription, searchCategory);
    } else if (searchSort != null){
      this.refreshsearchPostsSort(searchDescription, searchCategory, searchSort);
    }

    this.handleChange = this.handleChange.bind(this)
    this.submitPost = this.submitPost.bind(this)

    this.unlisten = this.props.history.listen((location, action) => {
      window.location.reload();
    });
  }


  render() {
        // Simply return a heading, and div that will contain the posts

        let retVal = (


      <div>
          <form onSubmit={this.submitPost} refs="form" className="Search_item">
                <select required name="category" className="refineItem" onChange={this.handleChange} value={this.state.category}>
                  <option value="">Choose Category</option>
                  <option value="all">All</option>
                  <option value="Exceptionally Random">Exceptionally Random</option>
                  <option value="Ridiculously Complicated">Ridiculously Complicated</option>
                  <option value="Annoyingly Unnexplained">Annoyingly Unnexplained</option>
                  <option value="Disturbingly Simple">Disturbingly Simple</option>
                  <option value="Spectularly Failing">Spectacularly Failing</option>
                </select>
                <input type="text" name="description" id="description" className="refineItem" placeholder="Search" onChange={this.handleChange} value={this.state.description} />
                <select name="sort" className="refineItem" onChange={this.handleChange} value={this.state.sort}>
                  <option value="">Sort</option>
                  <option value="Low">Price Low-High</option>
                  <option value="High">Price High-Low</option>
                  <option value="New">By Date New</option>
                  <option value="Old">By Date Old</option>

                </select>
                < input type="image" src={require("./search.png")} value="Submit" border="0" alt="Submit" className="refineItem"/>
          </form>


                <h1 className="marketTitle">Browse Marketplace</h1>
                <div className="container">
                    <Items history={this.props.history} backPostings={this.state.backPostings}></Items>
                </div>
            </div>
        );
       return retVal;
    }

     handleChange(event){

        this.setState(
        {
          [event.target.name] : event.target.value
        }
      )
    }



    submitPost(event){
      event.preventDefault();

      if (this.state.sort == ""){
        this.submitSearch();
      } else{
        this.submitSort();
      }
    }

    submitSearch(){
      var searchDescription = this.state.description;
      var searchCategory = this.state.category;
      this.props.history.push(`/market/searchBy/` + searchDescription + `/` + searchCategory);
    }

    submitSort(){
      var searchSort = this.state.sort;
      var searchDescription = this.state.description;
      var searchCategory = this.state.category;
      this.props.history.push(`/market/searchBy/` + searchDescription + `/` + searchCategory + `/` + searchSort);
    }

    // update the postings array with backend data
    refreshPosts() {

        MarketDataService.retrieveAllPosts().then(
            response => {
                this.setState({ backPostings: response.data })
            }
        ).catch(error => console.log("network error"));
    }

    refreshsearchPosts(description, category) {
        MarketDataService.retrievesearchByPosts(description, category).then(
            response => {
                this.setState({ backPostings: response.data })
            }
        ).catch(error => console.log("network error WTF!"));
    }

    refreshsearchPostsSort(description, category, sort) {
          MarketDataService. retrievesearchByPostsSort(description, category, sort).then(
              response => {
                  this.setState({ backPostings: response.data })
              }
          ).catch(error => console.log("network error WTF!"));
    }

}

// Helper class to render  the post rows
class Items extends Component {
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
            // Append the row of post information
            retVal.push(
                <div className="posting container" onClick={() => this.routeChange(postId)}>
                    <span className="postTitle"><img src={'post_images/' + this.state.backPostings[r].photo + '.jpg'}></img>{this.state.backPostings[r].title}</span> <br></br>
                    <span className="postDescription">{this.state.backPostings[r].description}</span> <br></br>
                    <span className="postPrice">{this.state.backPostings[r].price}</span> <br></br>
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



export default withRouter(MarketComponent)
