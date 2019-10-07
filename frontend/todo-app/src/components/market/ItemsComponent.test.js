import React from 'react';
import ReactDOM from 'react-dom';
import ItemsComponent from './ItemsComponent';
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
        const items = shallow(<ItemsComponent backPostings={[[
            { id: 'id', value: '1' },
            { id: 'title', value: '1' },
            { id: 'photo', value: '1' },
            { id: 'description', value: '1' },
            { id: 'price', value: '1' },
            { id: 'ownerId', value: '1' }]]} />);
        expect(items.find('span').length).toEqual(4);
    });
});