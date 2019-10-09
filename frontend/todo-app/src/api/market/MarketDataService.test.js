import React from 'react';
import ReactDOM from 'react-dom';
import MarketDataService from './MarketDataService';
import AuthenticationService from '../../components/todo/AuthenticationService.js'

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

it('successfully retrieved data from backend', () => {
  const returnedData = MarketDataService.retrieveAllPosts();
  expect(returnedData.length).toBeGreaterThan(0);
  expect(returnedData[0].length).toBe(5);
});
