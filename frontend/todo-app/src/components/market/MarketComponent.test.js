import React from 'react';
import ReactDOM from 'react-dom';
import { shallow, configure } from 'enzyme';
import MarketComponent from './MarketComponent';
import Adapter from 'enzyme-adapter-react-16';

beforeAll(() => {
    configure({ adapter: new Adapter() })
})

describe('<MarketComponent />', () => {
    it('renders without crashing', () => {
        const div = document.createElement('div');
        ReactDOM.render(<MarketComponent.WrappedComponent />, div);
        ReactDOM.unmountComponentAtNode(div);
    });
});


describe('<MarketComponent />', () => {
    it('renders basic structure', () => {
        const market = shallow(<MarketComponent.WrappedComponent />);
        expect(market.find('div').length).toEqual(4);
        expect(market.find('h1').length).toEqual(1);
        expect(market.find('Items').length).toEqual(1);
    });
});