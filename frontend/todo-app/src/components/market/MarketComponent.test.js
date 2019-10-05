import React from 'react';
import ReactDOM from 'react-dom';
import { shallow, configure } from 'enzyme';
import MarketComponent from './MarketComponent';
import Adapter from 'enzyme-adapter-react-16';
import { cleanup, render, fireEvent } from '@testing-library/react'
import { BrowserRouter as Router } from 'react-router-dom';

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
        expect(market.find('div').length).toEqual(2);
        expect(market.find('h1').length).toEqual(1);
        expect(market.find('Items').length).toEqual(1);
    });
});

describe('<MarketComponent />', () => {
    it('no search states show all posts', () => {
      const spy = jest.spyOn(MarketComponent.WrappedComponent.prototype, 'getAllPosts');
      jest.spyOn(window, 'alert').mockImplementation(() => {});
      const { getByTestId } = render(<Router><MarketComponent/></Router>);
      expect(MarketComponent.WrappedComponent.prototype.getAllPosts).toHaveBeenCalled();
    });
});

// describe('<MarketComponent />', () => {
//     it('search states show filtered posts', () => {
//       const spy = jest.spyOn(MarketComponent.WrappedComponent.prototype, 'getAllPosts');
//       jest.spyOn(window, 'alert').mockImplementation(() => {});
//       const matchMock = {
//           params: {
//             searchDescription: "anyDescription",
//             searchCategory: "anyCategory",
//             searchSort: "anySort"
//           }
//       }
//       const { getByTestId } = render(<Router><MarketComponent match={matchMock}/></Router>);
//       expect(MarketComponent.WrappedComponent.prototype.getAllPosts).toHaveBeenCalled();
//     });
// });
