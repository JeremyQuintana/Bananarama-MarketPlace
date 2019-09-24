import React, { Component } from 'react';

import toBackend from '../toBackend/postBackend.js'
import './Post_item.css'


class Post_item extends Component {
  constructor(props){
    super(props)

    this.state = {
      item_description: '',
      item_name: '',
      item_cost: '',
      item_photo: '',
      item_catagory: ''
    }

    this.handleChange = this.handleChange.bind(this)

    this.submitPost = this.submitPost.bind(this)


  }

  render() {
    return (
      <div className="Post_item">

        <form onSubmit={this.submitPost} refs="form">
          <div className="form">
            <div className="formDefinitions">
              <label htmlFor="item_description" className="definitions">Item Description: </label>
              <label htmlFor="item_name" className="definitions">Item Name: </label>
              <label htmlFor="item_cost" className="definitions">Item Cost: </label>
              <label htmlFor="item_catagory" className="definitions">Item Category: </label>
              <label htmlFor="item_photo" className="definitions"> Item Photo: </label>
            </div>
            <div className="formInputs">
              <textarea required name="item_description" className="input" placeholder="Mushy Explanation" value={this.state.item_description} onChange={this.handleChange} />
              <input required type="text" name="item_name" className="input" placeholder="Banana Name" value={this.state.item_name} onChange={this.handleChange} />
              <input required type="number" name="item_cost" className="input" placeholder="$000.00" min="000.01" step="0.01" pattern="\d.\d" value={this.state.item_cost} onChange={this.handleChange} />
              <select required name="item_catagory" className="input" onChange={this.handleChange} value={this.state.item_catagory}>
                <option value="" default>No Catagory</option>
                <option value="Exceptionally Random">Exceptionally Random</option>
                <option value="Ridiculously Complicated">Ridiculously Complicated</option>
                <option value="Annoyingly Unnexplained">Annoyingly Unnexplained</option>
                <option value="Disturbingly Simple">Disturbingly Simple</option>
                <option value="Spectularly Failing">Spectacularly Failing</option>
              </select>
              <input type="file" name="item_photo" className="input" accept="image/*" value={this.state.item_photo} onChange={this.handleChange}/>
            </div>
          </div>
          <input type="submit" value="Submit" name="itemSubmit" />

        </form>
      </div>
    );
  }

  handleChange(event){
    this.setState(
      {
        [event.target.name] : event.target.value
      }
    )
  }


  submitPost(event){
    toBackend.postItemBackend(this.state.item_description, this.state.item_name, this.state.item_cost, this.state.item_catagory, this.state.item_photo);
    event.preventDefault();
    alert("Your item has been posted");
    this.props.history.push(`/home/${sessionStorage.getItem("authenticatedUser")}`)
  }

}

export default Post_item
