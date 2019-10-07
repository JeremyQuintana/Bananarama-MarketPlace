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

  updateDeletePost(id) {

    return axios.post(`${API_URL}/postsdelete`, {
        id            
    })
  }

  updateSoldPost(id) {

    return axios.post(`${API_URL}/postssold`, {
        id            
    })
  }

  updatePermDeletePost(id) {

    return axios.post(`${API_URL}/postspermdelete`, {
        id            
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
