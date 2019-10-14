
import React, { Component } from 'react'

import { withRouter } from "react-router-dom";
import MarketDataService from "../../api/market/MarketDataService.js"
import ItemsComponent from "./ItemsComponent"
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
  var searchDescription = null;
  var searchCategory = null;
  var searchSort = null;
  if(this.props.match != null){
  var searchDescription = this.props.match.params.searchDescription;
  var searchCategory = this.props.match.params.searchCategory;
  var searchSort = this.props.match.params.searchSort;
  }


  if (searchDescription == null && searchCategory == null && searchSort == null){
    this.getAllPosts();
  } else {
    this.refreshSearchPostsSort(searchDescription, searchCategory, searchSort);
  }

  this.handleChange = this.handleChange.bind(this)
  this.submitPost = this.submitPost.bind(this)
  if(this.props.history != null){
    this.unlisten = this.props.history.listen((location, action) => {
      window.location.reload();
    });
  }

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
                  <ItemsComponent history={this.props.history} backPostings={this.state.backPostings}></ItemsComponent>
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

    this.submitSearch();
  }

  submitSearch(){
    var searchDescription = this.state.description;
    var searchCategory = this.state.category;
    var searchSort = this.state.sort;
    this.props.history.push(`/market/searchBy/` + searchDescription + `/` + searchCategory + `/` + searchSort);
  }

  // update the postings array with backend data
  refreshSearchPostsSort(description, category, sort) {
        MarketDataService.retrieveSearchByPostsSort(description, category, sort).then(
            response => {
                this.setState({ backPostings: response.data })
            }
        ).catch(error => console.log("network error"));
  }

  getAllPosts(){
    MarketDataService.retrieveAllPosts().then(
        response => {
            this.setState({ backPostings: response.data })
        }
    ).catch(error => console.log("network error"));
  }

}





export default withRouter(MarketComponent)
