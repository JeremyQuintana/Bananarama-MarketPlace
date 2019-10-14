import axios from 'axios'
import { API_URL } from '../../Constants'

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

  //  updateDeletePost(id) {

 //       return axios.put(`${API_URL}/posts/${id}`, {
  //          id
 //       })
 //   }
//


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

    updatePostStatus(id, status) {
        return axios.post(`${API_URL}/posts/${id}/${status}`)
    }

    deletePost(id) {
        return axios.delete(`${API_URL}/posts/${id}`)
    }

    // java does not recognize different methods with same urls
    retrievePostsBySeller(userId, status){
        return axios.get(`${API_URL}/${userId}/posts/${status}`);
    }

    // these show when you load the chats for each user
    // in the chat page (chat service)
    // retrieveChatsByUser(userId){
    //     return axios.get(`${API_URL}/${userId}/chats`);
    // }


}
export const googleauth = data => {
    return async dispatch => {
        console.log('NO MORE CORS?', data)
    }
}
export default new MarketDataService()
