import React from 'react';
import ReactDOM from 'react-dom';
import ItemsComponent from '../components/ItemsComponent';
import { shallow, configure } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

beforeAll(() => {
    configure({ adapter: new Adapter() })
})

describe('<ItemsComponent />', () => {
    it('renders without crashing', () => {
        const div = document.createElement('div');
        ReactDOM.render(<ItemsComponent backPostings={[]} />, div);
        ReactDOM.unmountComponentAtNode(div);
    });
});


describe('<ItemsComponent />', () => {
    it('renders empty initially', () => {
        const items = shallow(<ItemsComponent backPostings={[]} />);
        expect(items.isEmpty).toBeTruthy();
    });
});

describe('<ItemsComponent />', () => {
    it('renders based on state', () => {
        const items = shallow(<ItemsComponent backPostings={[
            { id: 1, title: '1', category: 1, photo: 1, description: 1, price: 1, ownerId: 1 },
        ]} />);
        expect(items.find('span').length).toEqual(4);
    });
});
