import axios from 'axios'
import { API_URL, JPA_API_URL } from '../../Constants'

// This is a data service for getting market items data from the backend
class ChatService {

    /* don't worry about creating a chat for now */
    /* unfortunately i can't connect to the database (val's ssl config stuff), 
    so may have to hardcode for now */


    // when you load a chat, you can call this
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

}
export default new ChatService()
