import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';


import MarketPlace from './MarketPlace';


it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<MarketPlace />, div);
  ReactDOM.unmountComponentAtNode(div);
});
