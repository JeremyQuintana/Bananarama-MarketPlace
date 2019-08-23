import React from 'react';
import ReactDOM from 'react-dom';
import Post_item from '../pages/Post_item';

let container = null;
beforeEach(() => {
  //creates dom componenet to use
  const div = document.createElement('div');
  container = ReactDOM.render(<Post_item />, div);

  //to change the states
  //gl reuben
  container.state.item_cost = "13";
  console.log(container.state.item_cost);
})

afterEach(() => {

});

it('calls onSubmit prop function when form is submitted', () => {
});

it('renders all needed inputs', () => {

});

it('rejects current session user id', () => {

});

it('rejects negative cost', () => {

});

it('rejects no cost', () => {

});

it('rejects no name', () => {

});

it('disallows text in cost', () => {

});
