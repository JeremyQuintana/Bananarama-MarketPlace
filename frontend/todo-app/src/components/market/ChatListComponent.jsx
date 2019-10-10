
import React, { Component } from 'react'

import ChatService from "../../api/Chat/ChatService.js"

import './Market.css';

// Helper class to render the chat list
class ChatListComponent extends Component {
  //solution to making itmes component rerender on state change from parent Component
  //found at https://medium.com/p/387720c3cff8/responses/show
  static getDerivedStateFromProps(props, state) {
    if (props.chats !== state.chats) {
      return { chats: props.chats };
    }
    return null;
  }

  constructor(props) {
    super(props);
    // State stores the chats from backend
    this.state = {
      chats: this.props.chats,
    }
  }

  render() {
    var retVal = [];
    // loop through the chats from backend
    for (var r = 0; r < this.state.chats.length; r++) {
      
      let receiverId = this.state.chats[r].receiverId;
      var senderId = this.state.chats[r].senderId;
      console.log(receiverId);
      console.log(senderId);
      //var chatId = receiverId + '/' + senderId;

      // Append the row of chat information
      retVal.push(
        <div className="posting container" /*onClick={() => this.routeChange(chatId)}*/>
          <span className="postTitle">{this.state.chats[r].receiverId}</span> <br></br>
          <input type="image" src={require("./delete.svg")} onClick = {() =>  this.permDeleteChat(senderId, receiverId)}></input> <br></br>
          <button onClick={() => this.routeChange(receiverId)}>View Chat</button>
          <br></br>
        </div>

      );
    }



    return retVal;
  }

  // Method for when a user clicks on a post, route them to post page
  routeChange(receiverId) {
    // Something like this?
    this.props.history.push("/chat/" + receiverId);
  }

  permDeleteChat(senderId, receiverId) {
    ChatService.deleteChats(senderId, receiverId).then(
        (response) => {
            
          alert("Your messages to" + receiverId + " have been PERMANENTLY DELETED");
          this.props.history.push(`/account`);
        }
      );
}

}
export default ChatListComponent