import axios from 'axios'
import { API_URL } from '../../Constants'

// This is a data service for getting market items data from the backend
class ChatService {

    //load the entire chat history between two users
    loadAllChats(user1, user2) {
        return axios.get(`${API_URL}/chat/${user1}/${user2}`);
    }

    //load chat history between two user after a given message
    loadChatsAfter(lastChat) {
        var user1 = lastChat.sender;
        var user2 = lastChat.receiver;
        var id = lastChat.id;

        return axios.get(`${API_URL}/chat/${user1}and${user2}afterID${id}`)
    }

    //send message to user through backend
    addChat(sender, receiver, text) {
        return axios.post(`${API_URL}/chat`, {
            sender,
            receiver,
            text
        });
    }

    //delete all messages you sent to a user
    deleteChats(sender, receiver) {
        // alert('deleting');
        return axios.delete(`${API_URL}/chat/${sender}and${receiver}`);
    }

    // returns list of ownerId's that have previously interacted with the owner
    userList(owner) {
        return axios.get(`${API_URL}/chat/${owner}`);
    }
}
export default new ChatService()
