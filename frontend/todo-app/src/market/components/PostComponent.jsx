import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import Post_item from './Post_item.jsx'
import '../css/PostComponent.css'
import MarketDataService from "../DataServices/MarketDataService.js"
import PostDataService from '../DataServices/PostDataService.js';

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
        this.updateAvailable = this.updateAvailable.bind(this);
        this.updatePermDelete = this.updatePermDelete.bind(this);
    }



    render() {
        let retVal;
        // get the row from the backend array, based on the postID param in props
        // create a div with all the singular posts information
        if (this.state.postInfo != null) {
          if (!this.state.editMode) {

            var ItemButtons;
            if (this.state.postInfo.status === "AVAILABLE" && (sessionStorage.getItem('authenticatedUser') === this.state.postInfo.ownerId)) {
              ItemButtons =
                <div className="containerbuttons ">
                  <button class="btn btn-dark" onClick={this.updateSold}>Sold</button>
                  <button class="btn btn-dark" onClick={this.updateDelete}>Delete</button>
                  <input type="image" className="editBtn" src={require("../images/edit.svg")} alt ="edit"   onClick={this.setEdit} />
                </div>
            }
            if ((this.state.postInfo.status === "DELETED" || this.state.postInfo.status === "SOLD") && (sessionStorage.getItem('authenticatedUser') === this.state.postInfo.ownerId)) {
              ItemButtons =
                <div className="containerbuttons ">
                  <button class="btn btn-dark" onClick={this.updateAvailable}>Available</button>
                  <input type="image" className="deleteBtn" src={require("../images/delete.svg")} alt ="Delete"   onClick={this.updatePermDelete} />
                </div>
            }
            if ((this.state.postInfo.status == "DELETED" || this.state.postInfo.status == "SOLD") && (sessionStorage.getItem('authenticatedUser') !== this.state.postInfo.ownerId)) {
              ItemButtons =
                alert("THIS ITEM HAS BEEN "+ this.state.postInfo.status);
                this.props.history.push(`/home/${sessionStorage.getItem("authenticatedUser")}`);
            }

            retVal = (
              <div className="product">
                <h1 className="marketTitle">{this.state.postInfo.title}</h1>
                <div className="postCategory">
                  {this.state.postInfo.category}
                </div>
                <div className="postDescription">
                  <img className="postImages" src={`https://storage.googleapis.com/sept-image-store/${this.state.postInfo.id}`}></img>
                  {this.state.postInfo.description}
                </div>
                <div className="postPrice">
                  ${this.state.postInfo.price}
                </div>
                <div className="postSeller">
                  {this.state.postInfo.ownerId}
                </div>
                <div className="postSeller"><Link to={"/chat/" + this.state.postInfo.ownerId} action="replace">Contact Seller</Link></div>
                {ItemButtons}
              </div>
            );
            } else {
              retVal = (
                <div className="product">
                  <Post_item existingId = {this.props.match.params.postID} history={this.props.history}></Post_item>
                  <div className="cancel"><button className="btn btn-dark cancel" onClick={this.clearEdit}>Cancel</button> </div>
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
    updateAvailable() {
        this.updateStatus("AVAILABLE");
    }

    updateStatus(status) {
        var postID= this.state.postInfo.id;

        MarketDataService.updatePostStatus(postID, status);
        alert("Your Post Has Been Marked As " + status);
        this.props.history.push(`/home/${sessionStorage.getItem("authenticatedUser")}`);
    }

    updatePermDelete() {
        var posttID= this.state.postInfo.id;

        MarketDataService.deletePost(posttID);
        alert("Your Post Has Been PERMANENTLY DELETED");
        this.props.history.push(`/home/${sessionStorage.getItem("authenticatedUser")}`);
    }

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
