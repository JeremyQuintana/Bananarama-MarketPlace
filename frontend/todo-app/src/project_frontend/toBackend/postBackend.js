import axios from 'axios'
import { API_URL } from '../../Constants'

class postBackend{

  postItemBackend(description, name, cost, catagory, photo){
    return axios.post(`${API_URL}/postitem`, {
      description,
      name,
      cost,
      catagory,
      photo
    })
  }

}

export default new postBackend()
