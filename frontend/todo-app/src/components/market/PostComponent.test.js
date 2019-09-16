import React from 'react';
import ReactDOM from 'react-dom';
import PostComponent from './PostComponent';
import { shallow, configure } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

beforeAll(() => {
  configure({ adapter: new Adapter() })
})

describe('<PostComponent />', () => {
  it('renders without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<PostComponent />, div);
    ReactDOM.unmountComponentAtNode(div);
  });
});


describe('<PostComponent />', () => {
  it('renders empty initially', () => {
    const market = shallow(<PostComponent />);
    expect(market.isEmpty).toBeTruthy();
  });
});