import React, { Component } from "react";
import { Link } from "react-router-dom";
import { withRouter } from "react-router-dom";
import FooterComponent from "../todo/FooterComponent.jsx";

import "./Chat.css";

const Sending_Data = [
  {
    senderId: "Buyer",
    text: "I want to buy"
  },

  {
    senderId: "Buyer",
    text: "Some Items For uni"
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
    Chat: "Reuben",
  },
    {
    Chat: "Val",
  },
    {
    Chat: "Mitch",
  }
];

class ChatComponent extends Component {
  render() {
    let retVal = (
      <div>
        <div className="ChatHead"></div>

        <div className="Body">
          <CuurentChats></CuurentChats>
          <Chatwindow></Chatwindow>
          <InputBox></InputBox>
         <div className="title"></div>
        </div>
      </div>
    );
    return retVal;
  }
}

function CuurentChats() {
  return <div className="rooms-list"> {Chats.map((message, index) => {
        return (
          <div key={index} className="chats">
            <div className="chat_text">{message.Chat}</div>
          </div>
        );
      })}</div>;
}

function InputBox() {
  return (
    <form className="InputBox">
      <input placeholder="SendMessage" type="text" />
    </form>
  );
}

function Message() {
  return (
    <div>
      <div className="message_sender"></div>
      <div className="message_receiver"></div>
    </div>
  );
}

function Chatwindow() {
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

      {Receiving_Data.map((message, index) => {
        return (
          <div key={index} className="message_receiver">
            <div className="message-username">{message.senderId}</div>
            <div className="receiver_message-text">{message.text}</div>
          </div>
        );
      })}
    </div>
  );
}



export default withRouter(ChatComponent);
