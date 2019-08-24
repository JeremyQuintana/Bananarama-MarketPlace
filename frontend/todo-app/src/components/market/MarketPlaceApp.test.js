import React from 'react';
import ReactDOM from 'react-dom';
import MarketPlaceApp from './MarketPlaceApp';

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<MarketPlaceApp />, div);
  ReactDOM.unmountComponentAtNode(div);
});