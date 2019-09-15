import axios from 'axios'
import { API_URL } from '../../Constants'

class postBackend{

  postItemBackend(description, title, price, category, photo){
    return axios.post(`${API_URL}/postitem`, {
      description,
      title,
      price,
      category,
      photo
    })
  }

   
  searchItemBackend(search_words, category){
    return axios.post(`${API_URL}/searchitem`, {
      
      search_words,
      category
    })
  }

}

export default new postBackend()
