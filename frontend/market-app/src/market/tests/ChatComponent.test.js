import React from 'react';
import ReactDOM from 'react-dom';
import ChatComponent from '../components/ChatComponent';
import { shallow, configure } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

beforeAll(() => {
    configure({ adapter: new Adapter() })
})

describe('<ChatComponent />', () => {
    it('renders without crashing', () => {
        const div = document.createElement('div');
        ReactDOM.render(<ChatComponent.WrappedComponent currentChats={[]} match={{ params: [] }} />, div);
        ReactDOM.unmountComponentAtNode(div);
    });
});


describe('<ChatComponent />', () => {
    it('renders basic structure correctly', () => {
        const chat = shallow(<ChatComponent.WrappedComponent currentChats={[]} match={{ params: [] }} />);
        expect(chat.find('div').length).toEqual(7);
        expect(chat.find('h2').length).toEqual(1);
        expect(chat.find('div.alert').length).toEqual(1);

    });
});

describe('<ChatComponent />', () => {
    it('renders a sub components on params match', () => {
        const chat = shallow(<ChatComponent.WrappedComponent currentChats={1,2,3} match={{ params: {receiverId: 1}}} />);
        expect(chat.find('MessageObjects').length).toEqual(1);
        expect(chat.find('MessageList').length).toEqual(1);

    });
});
