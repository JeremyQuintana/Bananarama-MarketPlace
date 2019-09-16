import React from 'react';
import ReactDOM from 'react-dom';
import LoginComponent from './LoginComponent';
import { shallow, configure } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

beforeAll(() => {
  configure({ adapter: new Adapter() })
})

describe('<LoginComponent />', () => {
  it('renders without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<LoginComponent />, div);
    ReactDOM.unmountComponentAtNode(div);
  });
});


describe('<LoginComponent />', () => {
  it('renders basic structure', () => {
    // will need to change with google login
    const market = shallow(<LoginComponent />);
    expect(market.find('div').length).toEqual(2);
    expect(market.find('h1').length).toEqual(1);
    expect(market.find('input').length).toEqual(2);
  });
});

describe('<LoginComponent />', () => {
  it('renders no login errors', () => {
    const market = shallow(<LoginComponent />);
    expect(market.find('div.alert').length).toEqual(0);
  });
});