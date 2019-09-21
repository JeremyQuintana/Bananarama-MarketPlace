import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { withRouter } from "react-router-dom";
import MarketDataService from "../../api/market/MarketDataService.js"
import MarketComponent from './MarketComponent.jsx';


// This is the marketplace browsing component
class SearchComponent extends Component {

  render() {
        
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

 

class NewItems extends Component {

    constructor(props) {
        super(props);
  
        this.state = {
            backSearchPostings: [[]],
          //  category: MarketComponent.category,
          //  description: MarketComponent.description
        }
        this.refreshsearchPosts()
    }
 
  
        render() {
            var retVal = [];
            if (this.state.backSearchPostings != null) {
      
            for (var r = 0; r < this.state.backSearchPostings.length; r++) {
                let postId = this.state.backSearchPostings[r].id;
                console.log("working? render");
               
                
                retVal.push(
                    <div className="posting container" onClick={() => this.routeChange(this.state.description, this.state.category, postId)}>
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
   
   
   
  routeChange(description, category, x) {
      this.props.history.push(`/posts/searchBy/${description}/${category}/` + x);
  }
  

    refreshsearchPosts(description, category) {
        console.log("working?");
        console.log(this.state.category);
        MarketDataService.retrievesearchByPosts(description, category).then(
            response => {
                
                this.setState({ backSearchPostings: response.data })
            }
        ).catch(error => console.log("network error WTF!"));
    }

}



export default withRouter(SearchComponent)



