import axios from 'axios'
import { API_URL, JPA_API_URL } from '../../Constants'

export const USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'

class GoogleAuthenticationService {
    executeGoogleAuthenticationService(token) {
        var username = "sept"
        var password = "dummy"
        return axios.post(`${API_URL}/googleauthenticate`, {
            // this will be sending the google token instead to backend
           //token
           username,
           password
        })
        //return token;
    }

    registerSuccessfulLoginForGoogle(username, token) {
        //sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, token.profileObj.email)
        sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username)
        this.setupAxiosInterceptors(this.createJWTToken(token))
    }

    createJWTToken(token) {
        return 'Bearer ' + token
    }


    setupAxiosInterceptors(token) {

        axios.interceptors.request.use(
            (config) => {
                if (this.isUserLoggedIn()) {
                    config.headers.authorization = token
                }
                return config
            }
        )
    }

    logout() {
        sessionStorage.removeItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
        if (user === null) return false
        return true
    }

    getLoggedInUserName() {
        let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
        if (user === null) return ''
        return user
    }
    //could get loggedinusername picture too
}

export default new GoogleAuthenticationService()