import axios from 'axios'
import { API_URL } from '../../Constants'

class PostDataService{

  //send a new item to backend
  postItemBackend(description, title, price, category, photo){
    return axios.post(`${API_URL}/postitem`, {
      description,
      title,
      price,
      category,
      photo
    })
  }
}

export default new PostDataService()
