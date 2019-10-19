import React from 'react';
import ReactDOM from 'react-dom';
import MarketDataService from '../DataServices/MarketDataService';
import AuthenticationService from '../DataServices/AuthenticationService.js'

beforeEach(() => {
    AuthenticationService
            .executeJwtAuthenticationService("sept", "dummy")
            .then((response) => {
                AuthenticationService.registerSuccessfulLoginForJwt("sept", response.data.token)
                this.props.history.push(`/welcome/${"sept"}`)
            }).catch(() => {
                this.setState({ showSuccessMessage: false })
                this.setState({ hasLoginFailed: true })
            })

})

it('successfully retrieved zero data from backend', () => {
  const returnedData = MarketDataService.retrieveAllPosts();
  expect(returnedData.length).toBe(undefined);
//   expect(returnedData).toBe(null);
});
