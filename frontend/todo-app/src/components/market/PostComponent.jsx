import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import Post_item from '../../project_frontend/pages/Post_item.jsx'
import './PostComponent.css'
import MarketDataService from "../../api/market/MarketDataService.js"
import postBackend from '../../project_frontend/toBackend/postBackend.js';


// This class is for an individual post's page
class PostComponent extends Component {


    constructor(props) {
        super(props);

        // State stores the posts from backend
        this.state = {
            postInfo: null,
            editMode: false,
        }
        this.retrieveItemInfo()

        this.setEdit = this.setEdit.bind(this);
        this.clearEdit = this.clearEdit.bind(this);
        this.saveInfo = this.saveInfo.bind(this);
        this.updateDelete = this.updateDelete.bind(this);
        this.updateSold = this.updateSold.bind(this);
        this.updatePermDelete = this.updatePermDelete.bind(this);
    }
  // 
                          
                          // <button onClick={this.setDelete}>Delete</button></div>
    render() {
        let retVal;
        // get the row from the backend array, based on the postID param in props
        // create a div with all the singular posts information
        if (this.state.postInfo != null) {
            if (!this.state.editMode) {
                retVal = (

                    <div className="topFix">
                        <h1 className="marketTitle">{this.state.postInfo.title}</h1>
                        <div className="container postCategory">
                            {this.state.postInfo.category}
                        </div>
                        <div className="container postDescription">
                            <img src={'../post_images/' + this.state.postInfo.photo + '.jpg'}></img>
                            {/*PLACEHOLDER IMAGE*/}
                            {/*<img src={'../post_images/1.jpg'}></img>*/}
                            {this.state.postInfo.description}
                        </div>
                        <div className="container postPrice">
                            ${this.state.postInfo.price}
                        </div>
                        <div className="container postSeller">
                            {this.state.postInfo.ownerId}
                        </div>
                        <div className="container postSeller"><Link to="/chat/" action="replace">Contact Seller</Link></div>
                        {(sessionStorage.getItem('authenticatedUser') == this.state.postInfo.ownerId) &&
                          <div className="container"> <button onClick={this.setEdit}>Edit Post</button>
                          <button onClick={this.updateSold}>Sold</button>
                          <button onClick={this.updateDelete}>Delete</button>
                          <input name="Delete" id="Delete" type="image" className="DeleterefineItem"
                             src={require("./delete.png")}
                            alt ="Delete"   onClick={this.updatePermDelete} /></div>
                        
                        }

                    </div>
                );
            } else {
                retVal = (
                    <div className="topFix">
                        <Post_item existingId = {this.props.match.params.postID} history={this.props.history}></Post_item>
                        <div className="container centerFix"><button onClick={this.clearEdit}>Cancel</button> </div>

                    </div>
                )

            }

        } else {
            retVal = null;
        }

        return retVal;
    }




    setEdit() {

        this.setState({ editMode: true });
        //console.log(this.state.editMode);
    }
    clearEdit() {
        this.setState({ editMode: false });
    }





    updateDelete() {
        this.updateStatus("DELETED");
    }
    updateSold() {
        this.updateStatus("SOLD");
    }

    updateStatus(status) {
        var postID= this.state.postInfo.id;
        
        MarketDataService.updatePostStatus(postID, status);
        alert("Your Post Has Been Marked As " + status);
        this.props.history.push(`/home/${sessionStorage.getItem("authenticatedUser")}`);
    }

    updatePermDelete() {
        var posttID= this.state.postInfo.id;
    
        postBackend.updatePermDeletePost(posttID).then(
            response => {
      
              alert("Your Post Has Been Deleted FOREVER");
              this.props.history.push(`/home/${sessionStorage.getItem("authenticatedUser")}`);
            }
          );
    }

    saveInfo() {
        // send to backend and redir/show success message
    }






    // update to mitches code
    // instead of retrieving all posts frontend and doing a search use backed to send only one item of given id
    retrieveItemInfo() {
        if(this.props.match != null){
            MarketDataService.retrieveSpecificPost(this.props.match.params.postID).then(
                response => {
                    this.setState({ postInfo: response.data });
                }
            ).catch(error => console.log("network error"));
        }

    }

}




export default PostComponent
