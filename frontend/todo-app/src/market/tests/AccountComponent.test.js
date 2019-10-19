import React from 'react';
import ReactDOM from 'react-dom';
import { shallow, configure } from 'enzyme';
import AccountComponent from '../components/AccountComponent';
import Adapter from 'enzyme-adapter-react-16';

var defaultPosts = [
    { id: 1, title: '1', category: 1, photo: 1, description: 1, price: 1, ownerId: 1 }
];

var defaultChats = [
    { senderId: 's1234567', receiverId: 's7654321' },
    { senderId: 's1234567', receiverId: 's5678910' }
];

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
        expect(account.find('div').length).toEqual(13);
        expect(account.find('h1').length).toEqual(1);
        expect(account.find('h3').length).toEqual(4);

        expect(account.find('ItemsComponent').length).toEqual(0);
        expect(account.find('div.alert').length).toEqual(4);
    });
});

describe('<AccountComponent />', () => {
    it('renders all item groups concurrently', () => {
        const account = shallow(<AccountComponent.WrappedComponent />);
        account.setState({
            pastPosts: defaultPosts,
            currentPosts: defaultPosts,
            deletedPosts: defaultPosts,
            allChats: defaultChats
        });
        expect(account.find('ItemsComponent').length).toEqual(3);
        expect(account.find('ChatListComponent').length).toEqual(1);
        expect(account.find('div.alert').length).toEqual(0);
    });
});

describe('<AccountComponent />', () => {
    it('renders past post group', () => {
        const account = shallow(<AccountComponent.WrappedComponent />);
        account.setState({ pastPosts: defaultPosts });
        expect(account.find('ItemsComponent').length).toEqual(1);
        expect(account.find('div.alert').length).toEqual(3);
    });
});

describe('<AccountComponent />', () => {
    it('renders current post group', () => {
        const account = shallow(<AccountComponent.WrappedComponent />);
        account.setState({ currentPosts: defaultPosts });
        expect(account.find('ItemsComponent').length).toEqual(1);
        expect(account.find('div.alert').length).toEqual(3);
    });
});

describe('<AccountComponent />', () => {
    it('renders deleted post group', () => {
        const account = shallow(<AccountComponent.WrappedComponent />);
        account.setState({ deletedPosts: defaultPosts });
        expect(account.find('ItemsComponent').length).toEqual(1);
        expect(account.find('div.alert').length).toEqual(3);
    });
});

describe('<AccountComponent />', () => {
    it('renders chats group', () => {
        const account = shallow(<AccountComponent.WrappedComponent />);
        account.setState({ allChats: defaultChats });
        expect(account.find('ChatListComponent').length).toEqual(1);
        expect(account.find('div.alert').length).toEqual(3);
    });
});
