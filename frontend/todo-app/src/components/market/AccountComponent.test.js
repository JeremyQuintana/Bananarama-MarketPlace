import React from 'react';
import ReactDOM from 'react-dom';
import { shallow, configure } from 'enzyme';
import AccountComponent from './AccountComponent';
import Adapter from 'enzyme-adapter-react-16';

var defaultPosts = [[
    { id: 'id', value: '1' },
    { id: 'title', value: '1' },
    { id: 'category', value: '1' },
    { id: 'photo', value: '1' },
    { id: 'description', value: '1' },
    { id: 'price', value: '1' },
    { id: 'ownerId', value: '1' }]];

beforeAll(() => {
    configure({ adapter: new Adapter() })
})

describe('<AccountComponent />', () => {
    it('renders without crashing', () => {
        const div = document.createElement('div');
        ReactDOM.render(<AccountComponent.WrappedComponent />, div);
        ReactDOM.unmountComponentAtNode(div);
    });
});


describe('<AccountComponent />', () => {
    it('renders basic structure', () => {
        const account = shallow(<AccountComponent.WrappedComponent />);
        expect(account.find('div').length).toEqual(10);
        expect(account.find('h1').length).toEqual(1);
        expect(account.find('h3').length).toEqual(3);

        expect(account.find('ItemsComponent').length).toEqual(0);
        expect(account.find('div.alert').length).toEqual(3);
    });
});

describe('<AccountComponent />', () => {
    it('renders all item groups concurrently', () => {
        const account = shallow(<AccountComponent.WrappedComponent />);
        account.setState({
            pastPosts: defaultPosts,
            currentPosts: defaultPosts,
            deletedPosts: defaultPosts
        });
        expect(account.find('ItemsComponent').length).toEqual(3);
        expect(account.find('div.alert').length).toEqual(0);
    });
});

describe('<AccountComponent />', () => {
    it('renders past post group', () => {
        const account = shallow(<AccountComponent.WrappedComponent />);
        account.setState({pastPosts: defaultPosts});
        expect(account.find('ItemsComponent').length).toEqual(1);
        expect(account.find('div.alert').length).toEqual(2);
    });
});

describe('<AccountComponent />', () => {
    it('renders past post group', () => {
        const account = shallow(<AccountComponent.WrappedComponent />);
        account.setState({currentPosts: defaultPosts});
        expect(account.find('ItemsComponent').length).toEqual(1);
        expect(account.find('div.alert').length).toEqual(2);
    });
});

describe('<AccountComponent />', () => {
    it('renders past post group', () => {
        const account = shallow(<AccountComponent.WrappedComponent />);
        account.setState({deletedPosts: defaultPosts});
        expect(account.find('ItemsComponent').length).toEqual(1);
        expect(account.find('div.alert').length).toEqual(2);
    });
});



