import React from 'react';
import ReactDOM from 'react-dom';
import Post_item from '../pages/Post_item';

it('renders post item page', () => {
  const div = document.createElement('div');
  ReactDOM.render(<Post_item />, div);
  ReactDOM.unmountComponentAtNode(div);
});

it('renders all needed inputs' () => {

});

it('rejects current session user id' () => {

});

it('rejects negative cost' () => {

});

it('rejects no cost' () => {

});

it('rejects no name' () => {

});

it('disallows text in cost' () => {

});
