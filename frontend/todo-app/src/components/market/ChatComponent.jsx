import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import ChatService from "../../api/Chat/ChatService.js"
import PropTypes from "prop-types";





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
    //var username = "thisUsername";
    // var username = sessionStorage.getItem('authenticatedUser');
    var username = "user1";

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
      ChatService.addChat("user1", "user2", newMessageToOutPut.text);
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
    return (
      <div className="message">
        <div className="username_id">{"user1"}</div>
        <div className="message-text" key={message.key}>
          {message.text}
        </div>
      </div>
    );
  };

  componentDidMount() {
    this.fetchMessages()
    this.timer = setInterval(() => this.fetchMessages(), 500000);
  }

  componentWillUnmount() {
    this.timer = null;
  }


  fetchMessages() {
    console.log("poll")
    ChatService.loadAllChats("user1", "user2").then(
      response => {
        this.setState({ newmessages: response.data })

      }
    ).catch(error => console.log("refreshing chat failed", error));
  }


  render() {
    const messages = this.props.allMeassages;
    const listMessages = messages.map(this.create_new_message);

  //  this.fetchMessages();


    //return <ul className="messages">{listMessages}</ul>;

    var retVal = [];
    for (var r = 0; r < this.state.newmessages.length; r++) {
      retVal.push(
        // <div className="message">
        //   <span className="messages username_id">{this.state.newmessages[r].sender}</span><br></br>

        //   <span className="messages message-sender">{this.state.newmessages[r].text}</span><br></br>
        // </div>
        this.create_new_message(this.state.newmessages[r])

      );
    }

    return retVal;


  }

  
}













export default withRouter(ChatComponent);



