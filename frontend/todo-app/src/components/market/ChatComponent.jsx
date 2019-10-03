import React, { Component } from "react";
import { Link } from "react-router-dom";
import { withRouter } from "react-router-dom";
import ChatService from "../../api/Chat/ChatService.js"
import MarketDataService from "../../api/market/MarketDataService.js"



import "./Chat.css";
const Chats = [
  {
    Chat: "Reuben"
  },
  {
    Chat: "Val"
  },
  {
    Chat: "Mitch"
  }
];


function CuurentChats() {
  return (
    <div>
      {" "}
      {Chats.map((message, index) => {
        return (
          <div key={index} className="chats">
            <div className="chat_text">{message.Chat}</div>
          </div>
        );
      })}
    </div>
  );
}

class ChatComponent extends Component {
  inputElement = React.createRef();
  constructor() {
    super();
    this.state = {
      messages: [],
      message: {
        text: "",
        key: ""
      }
    };
  }
  handleInput = e => {
    const itemText = e.target.value;

    // needs to be some username at some poiunt
    var username = "thisUsername";
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
      ChatService.addText("sender","reciver",newMessageToOutPut.text);

      this.setState({
        messages: messages,
        message: { text: "", key: "" }
      });
    }
  };
  render() {
    return (
     

        <div className="grid-container">
          <div className="grid-item">
                     <h1>Chats     </h1>

            <CuurentChats></CuurentChats>
          </div>
          <div className="grid-item">
            <div className="chat">
              <div className="message-list">

                <MessageObjects
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
  create_new_message = message => {
    return (
      <div className="message">
        <div className="username_id">username</div>
        <div className="message-text" key={message.key}>
          {message.text}
        </div>
      </div>
    );
  };
  render() {
    const messages = this.props.allMeassages;
    const listMessages = messages.map(this.create_new_message);
    this.refreshChatLog();


    return <ul className="messages">{listMessages}</ul>;
  }

      refreshChatLog() {
        var user1 = "user1";
        var user1 = "user2";

        //console.log(this.props.history);
        //this.props.history.push("");

        window.setInterval(function(){

                  ChatService.testFunction().then(

          response => {
           console.log(response[0]); 
        }

          )

}, 10000);


    }
}

export default withRouter(ChatComponent);
