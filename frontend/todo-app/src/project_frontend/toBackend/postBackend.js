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


  searchItemBackend(description, category){
    return axios.post(`${API_URL}/searchitem`, {

      description,
      category
    })
  }
  sortItemBackend(description, category, sort){
    return axios.post(`${API_URL}/searchitemsort`, {

      description,
      category,
      sort
    })
  }
}

export default new postBackend()
