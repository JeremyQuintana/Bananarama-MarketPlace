import axios from 'axios'
import { API_URL, JPA_API_URL } from '../../Constants'

// This is a data service for getting market items data from the backend
class MarketDataService {
    // Get the posts from the backend
    retrieveAllPosts() {

        return axios.get(`${API_URL}/posts`);
    }

    retrieveSearchByPostsSort(description, category, sort) {

        return axios.get(`${API_URL}/posts/searchBy/${description}/${category}/${sort}`);

    }

    retrieveSpecificPost(postId){
        return axios.get(`${API_URL}/posts/${postId}`);
    }


    updateExistingPost(id, description, title, price, category, photo, ownerId) {

        return axios.put(`${API_URL}/posts/${id}`, {
            id,
            description,
            title,
            price,
            category,
            photo, 
            ownerId
        })
    }

    updateExistingPost(status) {

        return axios.put(`${API_URL}/posts/${id}`, {
            status
        })
    }

}
export const googleauth = data => {
    return async dispatch => {
        console.log('NO MORE CORS?', data)
    }
}
export default new MarketDataService()
