import React, { Component } from 'react';

import toBackend from '../toBackend/postBackend.js'
import './Post_item.css'
import MarketDataService from '../../api/market/MarketDataService.js';


class Post_item extends Component {
  constructor(props) {
    super(props)

    this.state = {
      item_description: '',
      item_name: '',
      item_cost: '',
      item_photo: '',
      item_catagory: '',
      item_ownerId: ''
    }

    this.updateStateWithPost();
    this.handleChange = this.handleChange.bind(this)
    this.handleImageChange = this.handleImageChange.bind(this)

    this.submitPost = this.submitPost.bind(this)

    this.updateExistingPost = this.updateExistingPost.bind(this)


  }

  render() {
    return (
      <div className="Post_item">

        <form onSubmit={(this.props.existingId == null) ? this.submitPost : this.updateExistingPost} data-testid="form">
          <div className="form">
            <div className="formDefinitions">
              <label htmlFor="item_description" className="definitions">Item Description: </label>
              <label htmlFor="item_name" className="definitions">Item Name: </label>
              <label htmlFor="item_cost" className="definitions">Item Cost: </label>
              <label htmlFor="item_catagory" className="definitions">Item Catagory: </label>
              <label htmlFor="item_photo" className="definitions"> Item Photo: </label>
            </div>
            <div className="formInputs">
              <textarea name="item_description" id="item_description" className="input" placeholder="Mushy Explanation" value={this.state.item_description} onChange={this.handleChange} />
              <input required type="text" name="item_name" id="item_name" className="input" placeholder="Banana Name" value={this.state.item_name} onChange={this.handleChange} />
              <input required type="number" name="item_cost" id="item_cost" className="input" placeholder="$000.00" min="000.01" step="0.01" pattern="\d.\d" value={this.state.item_cost} onChange={this.handleChange} />
              <select name="item_catagory" id="item_catagory" className="input" onChange={this.handleChange} value={this.state.item_catagory}>
                <option value="" default>No Catagory</option>
                <option value="Exceptionally Random">Exceptionally Random</option>
                <option value="Ridiculously Complicated">Ridiculously Complicated</option>
                <option value="Annoyingly Unnexplained">Annoyingly Unnexplained</option>
                <option value="Disturbingly Simple">Disturbingly Simple</option>
                <option value="Spectacularly Failing">Spectacularly Failing</option>
              </select>
              <input type="file" name="item_photo" id="item_photo" className="input" accept="image/*" onChange={this.handleImageChange}/>
            </div>
            {this.props.existingId != null && <div className="container alert alert-warning">If no new photo is uploaded, the existing photo will be kept</div>}
          </div>
          <input type="submit" value="Submit" name="itemSubmit" />

        </form>
      </div>
    );
  }

  handleChange(event) {
    this.setState(
      {
        [event.target.name]: event.target.value
      }
    )
  }

  handleImageChange(event){
    let files=event.target.files;
    let reader = new FileReader();
    reader.readAsDataURL(files[0]);

    reader.onload=(e)=>{
      if (e.target.result.length < 10000){
        this.setState({item_photo: e.target.result})
      } else {
        alert("Image exceeds 100KB and will not be uploaded");
      }
    }
  }


  submitPost(event) {
    event.preventDefault();
    if (this.validate() === true){
     toBackend.postItemBackend(this.state.item_description, this.state.item_name, this.state.item_cost, this.state.item_catagory, this.state.item_photo);
     alert("Your item has been posted");
     this.props.history.push(`/home/${sessionStorage.getItem("authenticatedUser")}`)
    } else {
     alert("Invalid Inputs");
    }
  }

  validate(){
    var costRegex = /^\d*(\.\d{0,2})?$/;
    var costResult = ((costRegex.test(this.state.item_cost)) && (this.state.item_cost > 0));
    var catagoryResult =
          (this.state.item_catagory === "" ||
           this.state.item_catagory === "Exceptionally Random" ||
           this.state.item_catagory === "Ridiculously Complicated" ||
           this.state.item_catagory === "Annoyingly Unnexplained" ||
           this.state.item_catagory === " Disturbingly Simple" ||
           this.state.item_catagory === "Spectacularly Failing")

    var nameResult = !(this.state.item_name === "");

    var photoSize = (this.state.item_photo.length < 100000)

    var valid = (costResult && catagoryResult && nameResult && photoSize) ;

    return valid;
  }

  updateExistingPost(event) {
    console.log("SUCCESS");
    event.preventDefault();
    MarketDataService.updateExistingPost(this.props.existingId, this.state.item_description, this.state.item_name, this.state.item_cost, this.state.item_catagory, this.state.item_photo, this.state.item_ownerId).then(
      response => {

        alert("Your item has been updated");
        this.props.history.push(`/home/${sessionStorage.getItem("authenticatedUser")}`);
        window.location.reload();
      }
    );
  }

  updateStateWithPost() {
    if (this.props.existingId != null) {
      MarketDataService.retrieveSpecificPost(this.props.existingId).then(
        response => {
          console.log(response)
          this.setState({
            item_description: response.data.description,
            item_name: response.data.title,
            item_cost: response.data.price,
            //item_photo: response.data.photo,
            item_catagory: response.data.category,
            item_ownerId: response.data.ownerId
          })
        }
      )

    }
  }

}

export default Post_item
