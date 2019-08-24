import axios from 'axios'
import { API_URL } from '../../Constants'

class postBackend{

  postItemBackend(description, name, cost){
    return axios.post(`${API_URL}/postitem`, {
      description,
      name,
      cost
    })
  }

}

export default new postBackend()
