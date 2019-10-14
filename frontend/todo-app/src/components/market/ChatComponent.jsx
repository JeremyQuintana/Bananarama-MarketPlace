import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import ChatService from "../../api/Chat/ChatService.js"
import PropTypes from "prop-types";
import ChatListComponent from "./ChatListComponent"
import "./Chat.css";


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
    };

    this.refreshChatHistory();
  }

  refreshChatHistory() {
    ChatService.userList(sessionStorage.getItem('authenticatedUser')).then(
      (response) => {
        this.setState({ currentChats: response.data });
        // FOR TESTING: 
        // this.setState({currentChats: [
        //     { senderId: 's3707187', receiverId: 'user1' },
        //     { senderId: 's3707187', receiverId: 'user2' }]});
      }
    );
  }


  handleInput = e => {
    const itemText = e.target.value;

    // needs to be some username at some poiunt
    //var username = "thisUsername";
    // var username = sessionStorage.getItem('authenticatedUser');
    var username = sessionStorage.getItem('authenticatedUser')

    const message = { text: itemText, username };
    this.setState({
      message,
      username
    });
  };
  addMessage = e => {
    e.preventDefault();

    const newMessageToOutPut = this.state.message;
    if (newMessageToOutPut.text !== "") {
      const messages = [...this.state.messages, newMessageToOutPut];
      console.log(newMessageToOutPut);

      // calling it here 
      // ChatService.addChat("user1","user2",newMessageToOutPut.text);
      ChatService.addChat(sessionStorage.getItem('authenticatedUser'), this.props.match.params.receiverId, newMessageToOutPut.text);
      this.setState({
        messages: messages,
        message: { text: "", key: "" }
      });
    }
  };
  render() {
    var chatHistory = <div className="container alert alert-warning">No chats found!</div>
    if (this.state.currentChats.length > 1) {
      // console.log(this.state.currentChats[1])
      chatHistory = <ChatListComponent history={this.props.history} chats={this.state.currentChats} historyMode={false}></ChatListComponent>
    }
    return (


      <div className="grid-container">
        <div className="container">
          <h1>Chats</h1>
          <div className="container chatUserList">
            {chatHistory}
          </div>

        </div>
        <div className="grid-item">
          <div className="chat">
            <div className="message-list">

              <MessageObjects
                match={this.props.match}
                allMeassages={this.state.messages}
                deleteItem={this.deleteItem}
              />
            </div>

            <MessageList
              addMessage={this.addMessage}
              inputElement={this.inputElement}
              handleInput={this.handleInput}
              message={this.state.message}

            />
            {/* <TestChats> </TestChats> */}
          </div>

        </div>


      </div>

    );
  }
}


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
          <img alt="bananaLogo" src={require("./banana_icon.png")} />{" "}
        </button>
      </form>
    );
  }
}

class MessageObjects extends Component {
  static contextTypes = {
    router: PropTypes.object
  }

  constructor(props, context) {
    super(props, context);

    this.state = {
      newmessages: [],

    }
  }
  create_new_message = message => {
    var outerClassName = "message";
    var innerClassName = "message-text"
    if (message.receiver === sessionStorage.getItem('authenticatedUser')) {
      var outerClassName = "message_receiver";
      innerClassName = "receiver_message-text"
    }
    return (
      <div className={outerClassName}>
        <div className="username_id">{sessionStorage.getItem('authenticatedUser')}</div>
        <div className={innerClassName} key={message.key}>
          {message.text}
        </div>
      </div>
    );
  };

  componentDidMount() {
    this.fetchMessages()
    this.timer = setInterval(() => this.fetchMessages(), 5000);
  }

  componentWillUnmount() {
    this.timer = null;
  }


  fetchMessages() {
    console.log("poll")
    ChatService.loadAllChats(sessionStorage.getItem('authenticatedUser'), this.props.match.params.receiverId).then(
      response => {
        this.setState({ newmessages: response.data })

      }
    ).catch(error => console.log("refreshing chat failed", error));
  }


  render() {

    var retVal = [];
    for (var r = 0; r < this.state.newmessages.length; r++) {
      retVal.push(

        this.create_new_message(this.state.newmessages[r])

      );
    }

    return retVal;


  }


}













export default withRouter(ChatComponent);



