import React from 'react';
import ReactDOM from 'react-dom';
import Post_item from '../pages/Post_item';


let container = null;
beforeEach(() => {
  //creates dom componenet to use
  const div = document.createElement('div');
  container = ReactDOM.render(<Post_item />, div);
})

afterEach(() => {

});

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<Post_item />, div);
  ReactDOM.unmountComponentAtNode(div);
});
