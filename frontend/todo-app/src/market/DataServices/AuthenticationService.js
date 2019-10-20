import axios from 'axios'
import { API_URL } from '../../Constants'

export const USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'

class AuthenticationService {

    executeGoogleJwtAuthenticationService(googleUserToken) {
    return axios.post(`${API_URL}/authenticate`, {
        googleUserToken
      })
    }

    registerSuccessfulLoginForJwt(username, token) {
        sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username)
        sessionStorage.setItem("jwtToken", this.createJWTToken(token))
    }

    createJWTToken(token) {
        return 'Bearer ' + token
    }


    logout() {
        sessionStorage.removeItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
        sessionStorage.removeItem("jwtToken");
        this.props.history.push('/');
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
        if (user == null) return false
        return true
    }


}

export default new AuthenticationService()
