import axios from 'axios'
import { API_URL, JPA_API_URL } from '../../Constants'

// This is a data service for getting market items data from the backend
class ChatService {

    getAllTexts(user1, user2) {
        return axios.get(`${API_URL}/chat/${user1}and${user2}`);
    }

    addText(sender, receiver, text) {
        return axios.post(`${API_URL}/chat`, {
            sender, 
            receiver, 
            text
        });
    }

    testFunction(){
    return axios.get(`${API_URL}/chat`);
    }

}
export default new ChatService()
