import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import ChatService from "../DataServices/ChatService.js"
import PropTypes from "prop-types";
import ChatListComponent from "./ChatListComponent"
import "../css/Chat.css";


class ChatComponent extends Component {
  inputElement = React.createRef();
  constructor(props) {
    super(props);

    this.state = {
      messages: [],
      message: {
        text: "",
        key: ""
      },
      currentChats: [],
      currentReceiverID: ""
    };

    this.refreshChatHistory();
  }

  render() {
    //attain data on the list of chats with the user
    var chatHistory = <div className="container alert alert-warning">No chats found!</div>
    if (this.state.currentChats.length > 1) {
      chatHistory = <ChatListComponent history={this.props.history} chats={this.state.currentChats} historyMode={false}></ChatListComponent>
    }

    //var to determine whether we render the chat screen
    var renderChatSystem = this.props.match.params.receiverId != null;

    //grid 1 that holds the list of chats
    //grid 2 that holds the actual selected chat
    //grid 2 holds the list of messages sent between the two users in the past
    //grid 2 also holds the current message that the user wants to send
    return (
      <div className="communication">
        <div className="grid-item">
          <h2 className="centerFix">Chats</h2>
          <div className="container chatUserList">
            {chatHistory}
          </div>
        </div>
        <div className="grid-item">
          <div className="chat">
            {renderChatSystem && <h2>Chatting with: {this.props.match.params.receiverId}</h2>}
            <div className="message-list" id="messageScrollID">
              <MessageObjects
                match={this.props.match}
                allMeassages={this.state.messages}
                deleteItem={this.deleteItem}
              />
            </div>
              {renderChatSystem && <MessageList
                addMessage={this.addMessage}
                inputElement={this.inputElement}
                handleInput={this.handleInput}
                message={this.state.message}
              />}
          </div>
        </div>
      </div>
    );
  }

  //event handler when a change occurs in the message input
  handleInput = e => {
    const itemText = e.target.value;

    var username = sessionStorage.getItem('authenticatedUser')

    //updates the state of the message with the id of the current user
    const message = { text: itemText, username };
    this.setState({
      message,
      username
    });
  };

  //event handler when the submit message button is pressed
  addMessage = e => {
    e.preventDefault();

    //ensures the message is not empty
    const newMessageToOutPut = this.state.message;
    if (newMessageToOutPut.text !== "") {
      const messages = [...this.state.messages, newMessageToOutPut];

      //submits message of who sent to who towards backend
      ChatService.addChat(sessionStorage.getItem('authenticatedUser'), this.props.match.params.receiverId, newMessageToOutPut.text);
      //resets the message
      this.setState({
        messages: messages,
        message: { text: "", key: "" }
      });

    }
  };

  //gets the chat history again
  //called incrementally
  refreshChatHistory() {
    ChatService.userList(sessionStorage.getItem('authenticatedUser')).then(
      (response) => {
        this.setState({ currentChats: response.data });
      }
    );
  }
}

//component that holds structure for the input to submit a new message
class MessageList extends Component {
  render() {
    return (
      <form onSubmit={this.props.addMessage} className="InputBox">
        <input
          placeholder="Enter Message"
          ref={this.props.inputElement}
          value={this.props.message.text}
          onChange={this.props.handleInput}
        />
        <button type="submit">
          {" "}
          <img alt="bananaLogo" src={require("../images/banana_icon.png")} />{" "}
        </button>
      </form>
    );
  }
}

//component that holds the structure and logic to attain the data history of
//messages recieved from backend
class MessageObjects extends Component {
  __isMounted = false;
  static contextTypes = {
    router: PropTypes.object
  }

  constructor(props, context) {
    super(props, context);

    this.state = {
      newmessages: [],
      switch: false
    }
  }

  //function that places the next message onto the list rendered
  create_new_message(message, showName) {
    //determines the sender reciever to indicate the side the message it needs
    //to be placed at
    var outerClassName = "message message_sender";
    var innerClassName = "message-text"
    if (message.receiver === sessionStorage.getItem('authenticatedUser')) {
      outerClassName = "message_receiver";
      innerClassName = "receiver_message-text"
    }

    //renders the message in html
    return (
      <div className={outerClassName}>
        {showName && <div className="username_id">{message.sender}</div>}
        <div className={innerClassName} key={message.key}>
          {message.text}
        </div>
      </div>
    );
  };

  //if the message history is shown, we refresh it every minute to account
  //for new messages
  componentDidMount() {
    this.__isMounted = true;
    this.fetchMessages()
    this.interval = setInterval(() => this.fetchMessages(), 1000);
  }

  //on unmount stop refreshing the chat history
  componentWillUnmount() {
    this.__isMounted = false;
    clearInterval(this.interval);
    this.interval = null;
  }

  //function to get the messages in backend for the person we have selected
  fetchMessages() {
    ChatService.loadAllChats(sessionStorage.getItem('authenticatedUser'), this.props.match.params.receiverId).then(
      response => {
        //if the amount of messages is different from amount already held by
        //frontend then change the data and re render
        if (this.state.newmessages.length !== response.data.length) {
          this.setState({ newmessages: response.data })
          var chatInput = document.getElementById("messageScrollID")
          chatInput.scrollTop = chatInput.scrollHeight;
        }
      }
    ).catch(error => console.log("refreshing chat failed", error));
  }

  //loops through all the messages after recieving from backend
  //creates a new element that holds the message
  render() {
    var retVal = [];
    for (var r = 0; r < this.state.newmessages.length; r++) {
      var showName = true;
      if (r > 0) {
        if (this.state.newmessages[r - 1].sender === this.state.newmessages[r].sender) {
          showName = false;
        }
      }
      retVal.push(

        this.create_new_message(this.state.newmessages[r], showName)

      );
    }

    return retVal;


  }


}













export default withRouter(ChatComponent);
