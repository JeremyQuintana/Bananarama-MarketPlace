import axios from 'axios'
import { API_URL, JPA_API_URL } from '../../Constants'

// This is a data service for getting market items data from the backend
class ChatService {

    loadAllChats(user1, user2) {

        // var params = new URLSearchParams();
        // params.append('user1', user1);
        // params.append('user2', user2);
        // return axios.get(`${API_URL}/chat`, params);
        return axios.get(`${API_URL}/chat/${user1}and${user2}`);
    }

    // you have to pass in a chat object sent from backend
    // eg: from a list of chats 
    loadChatsAfter(lastChat) {
        var user1 = lastChat.sender;
        var user2 = lastChat.receiver;
        var text = lastChat.text;
        var id = lastChat.id;

        return axios.get(`${API_URL}/chat/${user1}and${user2}afterID${id}`)
    }

    addChat(sender, receiver, text) {
        return axios.post(`${API_URL}/chat`, {
            sender, 
            receiver, 
            text
        });
    }

    deleteChats(user1, user2) {
        // alert('deleting');
        return axios.delete(`${API_URL}/chat/${user1}and${user2}`);
    }

    // returns list of ownerId's that have previously interacted with the owner
    userList(owner) {
        return axios.get(`${API_URL}/chat/${owner}`);
    }


    testFunction(){
    return axios.get(`${API_URL}/chat`);
    }

}
export default new ChatService()
