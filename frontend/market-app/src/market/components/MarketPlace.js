import React, { Component } from 'react';
import MarketPlaceApp from './MarketPlaceApp'
import '../css/MarketPlace.css';
import '../css/bootstrap.css';

// This is called by the index file, which is the startup page
// called by the index
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
