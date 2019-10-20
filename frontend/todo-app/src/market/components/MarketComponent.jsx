import React, { Component } from 'react'

import { withRouter } from "react-router-dom";
import MarketDataService from "../DataServices/MarketDataService.js"
import ItemsComponent from "./ItemsComponent"
import '../css/Market.css';

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

    //set all search categories to nothing
    var searchDescription = null;
    var searchCategory = null;
    var searchSort = null;
    //if the search isnt empty then set them
    if(this.props.match != null){
      searchDescription = this.props.match.params.searchDescription;
      searchCategory = this.props.match.params.searchCategory;
      searchSort = this.props.match.params.searchSort;
    }

    /*
    if the search isnt completely empty then determine whether to get all
    posts or specific search categorised one*/
    if (searchDescription == null && searchCategory == null && searchSort == null){
      this.getAllPosts();
    } else {
      this.refreshSearchPostsSort(searchDescription, searchCategory, searchSort);
    }

    this.handleChange = this.handleChange.bind(this)
    this.submitPost = this.submitPost.bind(this)

    //when the url changes or when a search is submited then refresh the page
    if(this.props.history != null){
      this.unlisten = this.props.history.listen((location, action) => {
        window.location.reload();
      });
    }
  }

  render() {
    // Simply return a heading, and div that will contain the posts
    let retVal = (

    //reders the search functionality
    //then renders list of items marketplace
    <div className="market">
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

          < input type="image" src={require("../images/search.png")} value="Submit" border="0" alt="Submit" className="refineItem"/>
        </form>

        <h1 className="marketTitle">Browse Marketplace</h1>
        <div className="items">
          <ItemsComponent history={this.props.history} backPostings={this.state.backPostings}></ItemsComponent>
        </div>
      </div>
    );
    return retVal;
  }

  //function that handles any change in text search
  handleChange(event){
      this.setState(
      {
        [event.target.name] : event.target.value
      }
    )
  }

  //function that handles the event when the submit search button is pressed
  submitPost(event){
    event.preventDefault();

    this.submitSearch();
  }

  //functionality that handles that handles the change in search
  submitSearch(){
    var searchDescription = this.state.description;
    var searchCategory = this.state.category;
    var searchSort = this.state.sort;
    this.props.history.push(`/market/searchBy/` + searchDescription + `/` + searchCategory + `/` + searchSort);
  }

  // update the postings array with backend data based on search criteria
  refreshSearchPostsSort(description, category, sort) {
    MarketDataService.retrieveSearchByPostsSort(description, category, sort).then(
        response => {
            this.setState({ backPostings: response.data })
        }
    ).catch(error => console.log("network error"));
  }

  //update the postings on no search criteria hence getting all postings
  getAllPosts(){
    MarketDataService.retrieveAllPosts().then(
        response => {
            this.setState({ backPostings: response.data })
        }
    ).catch(error => console.log("network error"));
  }

}





export default withRouter(MarketComponent)
