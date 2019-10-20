import React from 'react';
import ReactDOM from 'react-dom';
import HomeComponent from '../components/HomeComponent';
import { shallow, configure } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

beforeAll(() => {
  configure({ adapter: new Adapter() })
})

describe('<HomeComponent />', () => {
  it('renders without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<HomeComponent.WrappedComponent />, div);
    ReactDOM.unmountComponentAtNode(div);
  });
});


describe('<HomeComponent />', () => {
  it('renders basic structure', () => {
    const market = shallow(<HomeComponent.WrappedComponent />);
    expect(market.find('div').length).toEqual(2);
    expect(market.find('Grid').length).toEqual(1);
    expect(market.find('div').first().hasClass('home')).toBeTruthy();
  });
});
