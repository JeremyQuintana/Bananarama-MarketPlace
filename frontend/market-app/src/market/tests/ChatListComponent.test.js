import React from 'react';
import ReactDOM from 'react-dom';
import ChatListComponent from '../components/ChatListComponent';
import { shallow, configure } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

beforeAll(() => {
    configure({ adapter: new Adapter() })
})

describe('<ChatListComponent />', () => {
    it('renders without crashing', () => {
        const div = document.createElement('div');
        ReactDOM.render(<ChatListComponent chats={[]} />, div);
        ReactDOM.unmountComponentAtNode(div);
    });
});


describe('<ChatListComponent />', () => {
    it('renders empty initially', () => {
        const chatList = shallow(<ChatListComponent chats={[]} />);
        expect(chatList.isEmpty).toBeTruthy();
    });
});

describe('<ChatListComponent />', () => {
    it('renders based on state', () => {
        const chatList = shallow(<ChatListComponent chats={[
            { senderId: 's1234567', receiverId: 's7654321' },
            { senderId: 's1234567', receiverId: 's5678910' }]} />);
        expect(chatList.find('span').length).toEqual(1);
    });
});
