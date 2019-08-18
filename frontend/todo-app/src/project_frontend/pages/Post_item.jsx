import React, { Component } from 'react';
import toBackend from '../toBackend/postBackend.js'
import './Post_item.css'

class Post_item extends Component {
  constructor(props){
    super(props)

    this.state = {
      item_description: '',
      item_name: '',
      item_cost: ''
    }

    this.handleChange = this.handleChange.bind(this)
    this.submitPost = this.submitPost.bind(this)
  }

  render() {
    return (
      <div className="Post_item">
        <form onSubmit={this.submitPost}>
          <div name="form">
            <div class="formDefinitions">
              <label for="item_description" class="definitions">Item Description: </label>
              <label for="item_name" class="definitions">Item Name: </label>
              <label for="item_cost" class="definitions">Item Cost: </label>
            </div>
            <div class="formInputs">
              <textarea name="item_description" class="input" placeholder="Mushy Explination" value={this.state.item_description} onChange={this.handleChange} />
              <input required type="text" name="item_name" class="input" placeholder="Banana Name" value={this.state.item_name} onChange={this.handleChange} />
              <input required type="number" name="item_cost" class="input" placeholder="$000.00" min="000.01" step="0.01" pattern="\d.\d" value={this.state.item_cost} onChange={this.handleChange} />
            </div>
          </div>
          <input type="submit" value="Submit" name="itemSubmit"/>
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
    toBackend.postItemBackend(this.state.item_description, this.state.item_name, this.state.item_cost);
    event.preventDefault();
  }
}

export default Post_item
