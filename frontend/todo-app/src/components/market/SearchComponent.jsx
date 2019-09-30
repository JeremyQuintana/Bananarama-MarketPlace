import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { withRouter } from "react-router-dom";
import MarketDataService from "../../api/market/MarketDataService.js"
import MarketComponent from './MarketComponent.jsx';
import './Market.css';
import toBackend from '../../project_frontend/toBackend/postBackend.js'

// This is the marketplace browsing component
class SearchComponent extends Component {
    constructor(props){
        super(props)
    
        this.state = {
    
          sort: ''
        }
    
    
      this.handleChange = this.handleChange.bind(this)
      this.submitPost = this.submitPost.bind(this)
      }
  render() {
        let retVal = (
          <div>
          <div>
                <h1 className="searchTitle">Results</h1>
               </div> 
               <div>
          <div className="sort_item">
          <form onSubmit={this.submitPost} refs="form">
                <select required name="sort" className="inputsort" onChange={this.handleChange} value={this.state.sort}>
                  <option value="">Sort</option>
                  <option value="Low">Price Low-High</option>
                  <option value="High">Price High-Low</option>
                  <option value="New">By Date New</option>
                  <option value="Old">By Date Old</option>
                 
                </select>

              
                <i class="iconsort">< input type="image" src={require("./searchsort.png")} value="Submit" border="0" alt="Submit" /></i>

          
          </form>

        </div>

                <div className="container">
                    <NewItems match={this.props.match} history={this.props.history}></NewItems>
                </div>
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
      var searchSort = this.state.sort;
      var searchCategorys  = this.props.match.params.searchCategory
      var searchDescriptions  = this.props.match.params.searchDescription
    

      toBackend.sortItemBackend(searchDescriptions, searchCategorys, this.state.sort);
      
      this.props.history.push(`/market/searchBy/` + searchDescriptions + `/` + searchCategorys + `/` + searchSort);
      }

  }



class NewItems extends Component {

    constructor(props) {
        super(props);

        this.state = {
            backSearchPostings: [[]],
         
        }
        this.refreshsearchPosts()
    }


        render() {
            var retVal = [];
            if (this.state.backSearchPostings != null) {

            for (var r = 0; r < this.state.backSearchPostings.length; r++) {
                let postId = this.state.backSearchPostings[r].id;

                retVal.push(
                    <div className="posting container" onClick={() => this.routeChange(postId)}>
                        <span className="postTitle"><img src={'post_images/' + this.state.backSearchPostings[r].photo + '.jpg'}></img>{this.state.backSearchPostings[r].title}</span> <br></br>
                        <span className="postDescription">{this.state.backSearchPostings[r].description}</span> <br></br>
                        <span className="postPrice">{this.state.backSearchPostings[r].price}</span> <br></br>
                        <span className="postSeller">{this.state.backSearchPostings[r].ownerId}</span> <br></br>

                        <br></br>
                    </div>


                );
                }
            }
            else{retVal.push(
                <div>
                <h3 className="searchTitle">No Results Found</h3>
                </div>
            );
                }

        return retVal;
    }



  routeChange(x) {
      this.props.history.push(`/market/` + x);
  }


    refreshsearchPosts(description, category) {
        MarketDataService.retrievesearchByPosts(this.props.match.params.searchDescription, this.props.match.params.searchCategory).then(
            response => {
                this.setState({ backSearchPostings: response.data })
            }
        ).catch(error => console.log("network error WTF!"));
    }

}



export default withRouter(SearchComponent)
