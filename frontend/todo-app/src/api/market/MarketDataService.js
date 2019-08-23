import axios from 'axios'
import { API_URL, JPA_API_URL } from '../../Constants'

class MarketDataService {

    retrieveAllPosts() {
        //console.log(axios.get(`${API_URL}/posts`))
        return axios.get(`${API_URL}/posts`);
    }



}

export default new MarketDataService()