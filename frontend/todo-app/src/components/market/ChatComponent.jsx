import React, { Component } from "react";
import { Link } from "react-router-dom";
import { withRouter } from "react-router-dom";

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
    <div className="rooms-list">
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
    const message = { text: itemText, key: Date.now() };
    this.setState({
      message
    });
  };
  addMessage = e => {
    e.preventDefault();

    const newMessageToOutPut = this.state.message;
    if (newMessageToOutPut.text !== "") {
      const messages = [...this.state.messages, newMessageToOutPut];
      console.log(newMessageToOutPut);

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
                <a1>Chats</a1>
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
        <div className="username_id">USERPLACEHOLDER</div>
        <div className="message-text" key={message.key}>
          {message.text}
        </div>
      </div>
    );
  };
  render() {
    const messages = this.props.allMeassages;
    const listMessages = messages.map(this.create_new_message);

    return <ul className="messages">{listMessages}</ul>;
  }
}

export default withRouter(ChatComponent);
