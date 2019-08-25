import React, { Component } from 'react';
//import FirstComponent from './components/learning-examples/FirstComponent'
//import SecondComponent from './components/learning-examples/SecondComponent'
//import ThirdComponent from './components/learning-examples/ThirdComponent'
//import Counter from './components/counter/Counter'
import MarketPlaceApp from './components/market/MarketPlaceApp'
import Post_item from './project_frontend/pages/Post_item.jsx'
import './MarketPlace.css';
import './bootstrap.css';

// This is called by the index file, which is the startup page
class MarketPlace extends Component {
  render() {
    // Render the MarketPlaceApp
    return (
      <div className="MarketPlace">
        <MarketPlaceApp />
      </div>
    );
  }
}


export default MarketPlace;
