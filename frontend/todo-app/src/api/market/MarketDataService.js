import axios from 'axios'
import { API_URL, JPA_API_URL } from '../../Constants'

// This is a data service for getting market items data from the backend
class MarketDataService {
    // Get the posts from the backend
    retrieveAllPosts() {

        return axios.get(`${API_URL}/posts`);
    }



}
export const googleauth = data => { return async dispatch => {
console.log('NO MORE CORS?', data)
}}
export default new MarketDataService()
