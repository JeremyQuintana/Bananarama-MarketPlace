import React, { Component } from "react";
import { Link } from "react-router-dom";
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
    var username = sessionStorage.getItem('authenticatedUser');
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
     ChatService.addChat(sessionStorage.getItem('authenticatedUser'), "user2",newMessageToOutPut.text);
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
              <Returnchats> </Returnchats>
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
  }
  create_new_message = message => {
    return (
      <div className="message">
        <div className="username_id">{sessionStorage.getItem('authenticatedUser')}</div>
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
    var user1= "user1";
    var user2= "user2";
    window.setInterval(function(){
      // change this vvv
      ChatService.loadAllChats("user1", "user2").then(
        response => {
          this.setState({ newmessages: response.data }) 
        
        }
      ).catch(error=>console.log("RUNNING REFRESH CAHT", error));
      // to look like
      // ChatService.loadChatsAfter(lastChat);
      // this "lastChat" object is the one sent to you from backend
      // eg: when you call loadAllChats (you get a list of chats)

    }, 100000);
  }
}










class Returnchats extends Component {

  
  constructor(props) {
    super(props);

    // State stores the posts from backend
    this.state = {
      newmessages: [],
        
    }
  
    

  this.refreshChatLog()
  

   }

 //random

render() {
var retVal = [];


for (var r = 0; r < this.state.newmessages.length; r++) {

  retVal.push(
    <div className="message">
     <span className="username_id">{this.state.newmessages[r].sender}</span><br></br>
     
     <span className="message-sender">{this.state.newmessages[r].text}</span><br></br>
      </div>

);
}

    return retVal;
}


 

  refreshChatLog() {
    
   // window.setInterval(function(){
      // change this vvv
    //
    //this is for the current logged in user
    //var user1= sessionStorage.getItem('authenticatedUser');

    var user1= "user1";
    var user2= "user2";
      ChatService.loadAllChats(user1, user2).then(
        response => {
          this.setState({ newmessages: response.data }) 
        
        }
      ).catch(error=>console.log("RUNNING REFRESH CAHT", error));

          
        //  console.log(response.data);
        }
         
      
    
     
      
      // to look like
      // ChatService.loadChatsAfter(lastChat);
      // this "lastChat" object is the one sent to you from backend
      // eg: when you call loadAllChats (you get a list of chats)

//   }, 100000);
  }


  export default withRouter(ChatComponent);



