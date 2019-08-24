import React, { Component } from "react";
import { Link } from "react-router-dom";
import { withRouter } from "react-router-dom";
import FooterComponent from "../todo/FooterComponent.jsx";

import "./Chat.css";

const Sending_Data = [
  {
    senderId: "Seller",
    text: "if you want cuzzy"
  },
  {
    senderId: "Seller",
    text: "What did you want to buy ?"
  }
];
const Receiving_Data = [
  {
    senderId: "Seller",
    text: "if you want cuzzy"
  },
  {
    senderId: "Seller",
    text: "What did you want to buy ?"
  }
];

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

function addInputText() {
  var input = document.getElementById("inputtext");
  // Seller will need to be some sort of id to determine whether they are a buyer or seller
  Sending_Data.push({ id: "Seller" });
  Sending_Data.push({ text: input.value });
  input.value = "";
  console.log(Sending_Data);
}

class ChatComponent extends Component {
  render() {
    let retVal = (
      <div>
        <div className="grid-container">
          <div className="grid-item">
            <CuurentChats></CuurentChats>
          </div>
          <div className="grid-item">
            {" "}
            <div className="Body" id="refresh">
              <Chatwindow />
              <InputBox />
              <div className="title"></div>
            </div>
          </div>
        </div>

        <FooterComponent></FooterComponent>
      </div>
    );
    return retVal;
  }
}

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

class InputBox extends React.Component {
  constructor() {
    super();
    this.state = {
      message: ""
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(e) {
    this.setState({
      message: e.target.value
    });
  }

  handleSubmit(e) {
    e.preventDefault();
    console.log(this.state.message);
  }

  handleSubmit(e) {
    e.preventDefault();
    this.props.sendMessage(this.state.message);
  }

  render() {
    return (
      <form onSubmit={this.handleSubmit} className="InputBox">
        <input
          onChange={this.handleChange}
          value={this.state.message}
          type="text"
          id="inputtext"
        />

        <button type="button" id="add" onClick={addInputText}>
          Send{" "}
        </button>
      </form>
    );
  }
}

class Chatwindow extends React.Component {
  render() {
    return (
      <div className="message-list">
        {Sending_Data.map((message, index) => {
          return (
            <div key={index} className="message">
              <div className="message-username">{message.senderId}</div>
              <div className="message-text">{message.text}</div>
            </div>
          );
        })}
      </div>
    );
  }
}

function Message() {
  return (
    <div>
      <div className="message_sender"></div>
      <div className="message_receiver"></div>
    </div>
  );
}

export default withRouter(ChatComponent);
