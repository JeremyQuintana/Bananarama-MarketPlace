import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { withRouter } from "react-router-dom";
import MarketDataService from "../../api/market/MarketDataService.js"
import MarketComponent from './MarketComponent.jsx';
import './Market.css';
import toBackend from '../../project_frontend/toBackend/postBackend.js'

// This is the marketplace browsing component
class SearchComponentSort extends Component {
   
  render() {
        let retVal = (
            <div>
            <h1 className="searchTitle">Results</h1>
         

                <div className="container">
                    <NewItemsSort match={this.props.match} history={this.props.history}></NewItemsSort>
                </div>
                </div> 
        );
        return retVal;
    }
}



class NewItemsSort extends Component {

    constructor(props) {
        super(props);

        this.state = {
            backSearchPostingsSort: [[]],
         
        }
        this.refreshsearchPostsSort()
    }


        render() {
            var retVal = [];
            if (this.state.backSearchPostingsSort != null) {

            for (var r = 0; r < this.state.backSearchPostingsSort.length; r++) {
                let postId = this.state.backSearchPostingsSort[r].id;

                retVal.push(
                    <div className="posting container" onClick={() => this.routeChange(postId)}>
                        <span className="postTitle"><img src={'post_images/' + this.state.backSearchPostingsSort[r].photo + '.jpg'}></img>{this.state.backSearchPostingsSort[r].title}</span> <br></br>
                        <span className="postDescription">{this.state.backSearchPostingsSort[r].description}</span> <br></br>
                        <span className="postPrice">{this.state.backSearchPostingsSort[r].price}</span> <br></br>
                        <span className="postSeller">{this.state.backSearchPostingsSort[r].ownerId}</span> <br></br>

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


  refreshsearchPostsSort(description, category, sort) {
    var searchSort  = this.props.match.params.sort
        MarketDataService. retrievesearchByPostsSort(this.props.match.params.searchDescription, this.props.match.params.searchCategory, this.props.match.params.searchSort).then(
            response => {
                this.setState({ backSearchPostingsSort: response.data })
            }
        ).catch(error => console.log("network error WTF!"));
    }

}



export default withRouter(SearchComponentSort)
