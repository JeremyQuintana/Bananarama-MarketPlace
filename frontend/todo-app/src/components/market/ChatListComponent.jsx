
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
      historyMode: this.props.historyMode
    }
  }

  render() {
    var retVal = [];
    // loop through the chats from backend
    for (var r = 1; r < this.state.chats.length; r++) {
      // console.log(this.state.chats[r] + "MCMODE")
      let receiverId = this.state.chats[r]
      // var senderId = this.state.chats[r].senderId;
      console.log(receiverId);
      // console.log(senderId);
      //var chatId = receiverId + '/' + senderId;

      // Append the row of chat information
      retVal.push(
        <div className="chatListItem container" onClick={() => this.routeChange(receiverId)}>
          <span className="postTitle">{receiverId}</span> 
          {/* <button className = "btn btn-outline-dark wideButton" onClick={() => this.routeChange(receiverId)}>View Chat</button> */}
          {this.state.historyMode && <input className = "chatDeleteIcon" type="image" src={require("./delete.svg")} onClick={(event) => this.permDeleteChat(event, sessionStorage.getItem('authenticatedUser'), receiverId)}></input>}
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

  permDeleteChat(event, senderId, receiverId) {
    event.stopPropagation();

    ChatService.deleteChats(senderId, receiverId).then(
      (response) => {

        alert("Your messages to" + receiverId + " have been PERMANENTLY DELETED");
        window.location.reload();
      }
    );

  }

}
export default ChatListComponent