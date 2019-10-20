import React from 'react';
import ReactDOM from 'react-dom';
import PostItem from '../components/PostItem';
import postBackend from '../DataServices/PostDataService';
import { cleanup, render, fireEvent } from '@testing-library/react'

afterEach(cleanup);

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<PostItem />, div);
  ReactDOM.unmountComponentAtNode(div);
});

it("submit calls submission function", () => {
  const spy = jest.spyOn(PostItem.prototype, 'submitPost');
  jest.spyOn(window, 'alert').mockImplementation(() => {});
  const historyMock = { push: jest.fn() };
  const { getByTestId } = render(<PostItem onSubmit={spy} history={historyMock} />);
  fireEvent.submit(getByTestId("form"));
  expect(PostItem.prototype.submitPost).toHaveBeenCalled();
});

it("submit calls validation function", () => {
  const spy = jest.spyOn(PostItem.prototype, 'validate');
  jest.spyOn(window, 'alert').mockImplementation(() => {});
  const historyMock = { push: jest.fn() };
  const { getByTestId } = render(<PostItem onSubmit={spy} history={historyMock} />);
  fireEvent.submit(getByTestId("form"));
  expect(PostItem.prototype.submitPost).toHaveBeenCalled();
});

it("submit rejects on no cost", () => {
  const spy = jest.spyOn(postBackend, 'postItemBackend');
  jest.spyOn(window, 'alert').mockImplementation(() => {});
  const historyMock = { push: jest.fn() };
  const component = render(<PostItem onSubmit={spy} history={historyMock}/>);
  const { getByTestId } = component;

  const inputName = component.getByLabelText('Item Name:');
  fireEvent.change(inputName, { target: {value: 'a'}});

  fireEvent.submit(getByTestId("form"));
  expect(postBackend.postItemBackend).toHaveBeenCalledTimes(0);
});

it("submit rejects on invalid cost", () => {
  const spy = jest.spyOn(postBackend, 'postItemBackend');
  jest.spyOn(window, 'alert').mockImplementation(() => {});
  const historyMock = { push: jest.fn() };
  const component = render(<PostItem onSubmit={spy} history={historyMock}/>);
  const { getByTestId } = component;

  const inputCost = component.getByLabelText('Item Cost:');
  fireEvent.change(inputCost, { target: {value: 'a'}});

  const inputName = component.getByLabelText('Item Name:');
  fireEvent.change(inputName, { target: {value: 'a'}});

  fireEvent.submit(getByTestId("form"));
  expect(postBackend.postItemBackend).toHaveBeenCalledTimes(0);
});

it("submit rejects on invalid cost", () => {
  const spy = jest.spyOn(postBackend, 'postItemBackend');
  jest.spyOn(window, 'alert').mockImplementation(() => {});
  const historyMock = { push: jest.fn() };
  const component = render(<PostItem onSubmit={spy} history={historyMock}/>);
  const { getByTestId } = component;

  const inputCost = component.getByLabelText('Item Cost:');
  fireEvent.change(inputCost, { target: {value: '00.00'}});

  const inputName = component.getByLabelText('Item Name:');
  fireEvent.change(inputName, { target: {value: 'a'}});

  fireEvent.submit(getByTestId("form"));
  expect(postBackend.postItemBackend).toHaveBeenCalledTimes(0);
});

it("submit rejects on no name", () => {
  const spy = jest.spyOn(postBackend, 'postItemBackend');
  jest.spyOn(window, 'alert').mockImplementation(() => {});
  const historyMock = { push: jest.fn() };
  const component = render(<PostItem onSubmit={spy} history={historyMock}/>);
  const { getByTestId } = component;

  const inputCost = component.getByLabelText('Item Cost:');
  fireEvent.change(inputCost, { target: {value: '1'}});

  fireEvent.submit(getByTestId("form"));
  expect(postBackend.postItemBackend).toHaveBeenCalledTimes(0);
});

it("submit calls to backend function", () => {
  const spy = jest.spyOn(postBackend, 'postItemBackend');
  jest.spyOn(window, 'alert').mockImplementation(() => {});
  const historyMock = { push: jest.fn() };
  const component = render(<PostItem onSubmit={spy} history={historyMock}/>);
  const { getByTestId } = component;

  const inputCost = component.getByLabelText('Item Cost:');
  fireEvent.change(inputCost, { target: {value: '1'}});

  const inputName = component.getByLabelText('Item Name:');
  fireEvent.change(inputName, { target: {value: 'a'}});

  fireEvent.submit(getByTestId("form"));
  expect(postBackend.postItemBackend).toHaveBeenCalledTimes(1);
});
