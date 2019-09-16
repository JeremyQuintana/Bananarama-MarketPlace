
//Reference: https://www.npmjs.com/package/react-google-login

import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import MarketPlace from './MarketPlace';
import Post_item from './project_frontend/pages/Post_item.jsx'
import * as serviceWorker from './serviceWorker';
//import GoogleLogin from 'react-google-login';
//import config from './config.json';


//ReactDOM.render(<App />, document.getElementById('root'));
ReactDOM.render(<MarketPlace />, document.getElementById('root'));



// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
