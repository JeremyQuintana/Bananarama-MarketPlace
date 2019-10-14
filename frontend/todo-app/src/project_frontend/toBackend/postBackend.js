import axios from 'axios'
import { API_URL } from '../../Constants'

class postBackend{

  postItemBackend(description, title, price, category, photo){
    
    return axios.post(`${API_URL}/postitem${photo}`, {
      description,
      title,
      price,
      category
    })
  }

// replaced by updatePostStatus in MarketDataService
  // updateDeletePost(id) {

  //   return axios.post(`${API_URL}/postsdelete`, {
  //       id
  //   })
  // }

  // updateSoldPost(id) {

  //   return axios.post(`${API_URL}/postssold`, {
  //       id
  //   })
  // }

  // need to verify owner + delete mapping
  //
  // updatePermDeletePost(id) {

  //   return axios.post(`${API_URL}/postspermdelete`, {
  //       id            
  //   })
  // }


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
