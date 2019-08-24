import React, { Component } from 'react';
//import FirstComponent from './components/learning-examples/FirstComponent'
//import SecondComponent from './components/learning-examples/SecondComponent'
//import ThirdComponent from './components/learning-examples/ThirdComponent'
//import Counter from './components/counter/Counter'
import MarketPlaceApp from './components/market/MarketPlaceApp'
import './MarketPlace.css';
import './bootstrap.css';

class MarketPlace extends Component {
  render() {
    // Render the MarketPlaceApp
    return (
      <div className="MarketPlace">
        {/*<Counter/>*/}
        <MarketPlaceApp />
      </div>
    );
  }
}

// class LearningComponents extends Component {
//   render() {
//     return (
//       <div className="LearningComponents">
//          My Hello World
//          <FirstComponent></FirstComponent>
//          <SecondComponent></SecondComponent>
//          <ThirdComponent></ThirdComponent>
//       </div>
//     );
//   }
// }

export default MarketPlace;