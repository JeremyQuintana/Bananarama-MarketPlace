import React from 'react';
import ReactDOM from 'react-dom';
import MarketPlace from '../components/MarketPlace';
import { shallow, configure } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

beforeAll(() => {
  configure({ adapter: new Adapter() })
})

describe('<MarketPlace />', () => {
  it('renders without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<MarketPlace />, div);
    ReactDOM.unmountComponentAtNode(div);
  });
});

describe('<MarketPlace />', () => {
  it('renders basic structure', () => {
    const market = shallow(<MarketPlace />);
    expect(market.find('div').length).toEqual(1);
    expect(marketMarketPlaceApp').length).toEqual(1);
    expect(market.find('div').hasClass('MarketPlace')).toBeTruthy();
  });
});