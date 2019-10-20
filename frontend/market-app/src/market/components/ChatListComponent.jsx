
import React, { Component } from 'react'

import ChatService from "../DataServices/ChatService.js"

import "../css/ChatList.css"

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
      historyMode: this.props.historyMode
    }
  }

  render() {
    var retVal = [];
    // loop through the chats from backend
    for (var r = 1; r < this.state.chats.length; r++) {
      let receiverId = this.state.chats[r]

      // Append the row of chat information
      retVal.push(
        <div className="chatListItem container" onClick={() => this.routeChange(receiverId)}>
          <span className="postTitle">{receiverId}</span>
          {this.state.historyMode && <input className = "chatDeleteIcon" type="image" alt="delete" src={require("../images/delete.svg")} onClick={(event) => this.permDeleteChat(event, sessionStorage.getItem('authenticatedUser'), receiverId)}></input>}
          <br></br>
        </div>
      );
    }
    return retVal;
  }

  //method for when a user clicks on a post, route them to post page
  //redirects them to the relevant chat page
  routeChange(receiverId) {
    this.props.history.push("/chat/" + receiverId);
  }

  //function called when delete button is pressed on the chat
  permDeleteChat(event, senderId, receiverId) {
    //required to stop the change to new page
    event.stopPropagation();

    //delete all message sent by the user to the reciever
    ChatService.deleteChats(senderId, receiverId).then(
      (response) => {
        alert("Your messages to" + receiverId + " have been PERMANENTLY DELETED");
        window.location.reload();
      }
    );

  }

}
export default ChatListComponent
