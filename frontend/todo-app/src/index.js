import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import MarketPlace from './MarketPlace';
import Post_item from './project_frontend/pages/Post_item.jsx'
import * as serviceWorker from './serviceWorker';
import GoogleLogin from 'react-google-login';


//ReactDOM.render(<App />, document.getElementById('root'));
ReactDOM.render(<MarketPlace />, document.getElementById('root'));

const responseGoogle = (response) => {
    console.log(response);
  }
   
  ReactDOM.render(
    <GoogleLogin
      clientId="181217491085-qp9tlug637043leq42ahec33f03k9plt.apps.googleusercontent.com"
      buttonText="Login"
      onSuccess={responseGoogle}
      onFailure={responseGoogle}
      cookiePolicy={'single_host_origin'}
    />,
    document.getElementById("notroot")
  );

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
