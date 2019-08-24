import React, { Component } from 'react';

class Post_item extends Component {
  constructor(props){
    super(props)

    this.state = {
      item_description: '',
      item_name: '',
      item_cost: ''
    }

    this.handleChange = this.handleChange.bind(this)
  }

  render() {
    return (
      <div className="Post_item">
        <form>
          <label>Item Description: <textarea name="item_description" placeholder="Item Description" value={this.state.item_description} onChange={this.handleChange} /></label><br />
          <label>Item Name: <input required type="text" name="item_name" placeholder="Item Name" value={this.state.item_name} onChange={this.handleChange} /></label><br />
          <label>Item Cost: $<input required type="number" name="item_cost" placeholder="Item Cost" min="0.01" step="0.01" pattern="\d.\d" value={this.state.item_cost} onChange={this.handleChange} /></label><br />
          <label>Item Image: <input type="file" name="item_img" accept="image/*" /></label><br />
          <input type="submit" value="Submit" />
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
}

export default Post_item
