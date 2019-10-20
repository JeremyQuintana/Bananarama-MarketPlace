import axios from 'axios'
import { API_URL } from '../../Constants'

// This is a data service for getting market items data from the backend
class MarketDataService {
    // Get the posts from the backend
    retrieveAllPosts() {

        return axios.get(`${API_URL}/posts`);
    }

    //get posts by search criteria
    retrieveSearchByPostsSort(description, category, sort) {

        return axios.get(`${API_URL}/posts/searchBy/${description}/${category}/${sort}`);

    }

    //get a post
    retrieveSpecificPost(postId){
        return axios.get(`${API_URL}/posts/${postId}`);
    }

    //update existing current post
    updateExistingPost(id, description, title, price, category, photo, ownerId) {
        return axios.put(`${API_URL}/posts/${id}`, {
            description,
            title,
            price,
            category,
            photo,
            ownerId
        })
    }

    //update status of a post
    updatePostStatus(id, status) {
        return axios.post(`${API_URL}/posts/${id}/${status}`)
    }

    //delete a post
    deletePost(id) {
        return axios.delete(`${API_URL}/posts/${id}`)
    }

    //get all posts by a seller
    retrievePostsBySeller(userId, status){
        return axios.get(`${API_URL}/${userId}/posts/${status}`);
    }

}
export default new MarketDataService()
