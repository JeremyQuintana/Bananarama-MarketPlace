import axios from 'axios'
import { API_URL, JPA_API_URL } from '../../Constants'

class MarketDataService {
    // Get the posts from the backend
    retrieveAllPosts() {
        
        return axios.get(`${API_URL}/posts`);
    }



}

export default new MarketDataService()