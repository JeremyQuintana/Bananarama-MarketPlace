import React from 'react';
import ReactDOM from 'react-dom';
import MarketDataService from '../DataServices/MarketDataService';
import AuthenticationService from '../DataServices/AuthenticationService.js'

it('successfully retrieved zero data from backend', () => {
  const returnedData = MarketDataService.retrieveAllPosts();
  expect(returnedData.length).toBe(undefined);
//   expect(returnedData).toBe(null);
});
