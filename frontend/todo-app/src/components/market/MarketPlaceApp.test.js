import React from 'react';
import ReactDOM from 'react-dom';
import MarketPlaceApp from './MarketPlaceApp';
import { shallow, configure } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

beforeAll(() => {
  configure({ adapter: new Adapter() })
})

describe('<MarketPlaceApp />', () => {
  it('renders without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<MarketPlaceApp />, div);
    ReactDOM.unmountComponentAtNode(div);
  });
});


describe('<MarketPlaceApp />', () => {
  it('renders basic structure', () => {
    const market = shallow(<MarketPlaceApp />);
    expect(market.find('div').length).toEqual(1);
    expect(market.find('div').hasClass('MarketPlaceApp')).toBeTruthy();
  });
});