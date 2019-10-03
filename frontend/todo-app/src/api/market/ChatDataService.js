import axios from 'axios'
import { API_URL, JPA_API_URL } from '../../Constants'

// This is a data service for getting market items data from the backend
class postBackend{

  postItemBackend(chatid, text){
    return axios.post(`${API_URL}/postitem`, {
      chatid,
      text
    })
  }
}
export default new postBackend()
