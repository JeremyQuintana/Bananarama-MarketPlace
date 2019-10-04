import React from 'react';
import ReactDOM from 'react-dom';
import Post_item from '../pages/Post_item';
import postBackend from '../toBackend/postBackend';
import { cleanup, render, fireEvent } from '@testing-library/react'

afterEach(cleanup);

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<Post_item />, div);
  ReactDOM.unmountComponentAtNode(div);
});

it("submit calls submission function", () => {
  const spy = jest.spyOn(Post_item.prototype, 'submitPost');
  jest.spyOn(window, 'alert').mockImplementation(() => {});
  const historyMock = { push: jest.fn() };
  const { getByTestId } = render(<Post_item onSubmit={spy} history={historyMock} />);
  fireEvent.submit(getByTestId("form"));
  expect(Post_item.prototype.submitPost).toHaveBeenCalled();
});

it("submit calls validation function", () => {
  const spy = jest.spyOn(Post_item.prototype, 'validate');
  jest.spyOn(window, 'alert').mockImplementation(() => {});
  const historyMock = { push: jest.fn() };
  const { getByTestId } = render(<Post_item onSubmit={spy} history={historyMock} />);
  fireEvent.submit(getByTestId("form"));
  expect(Post_item.prototype.submitPost).toHaveBeenCalled();
});

it("submit rejects on no cost", () => {
  const spy = jest.spyOn(postBackend, 'postItemBackend');
  jest.spyOn(window, 'alert').mockImplementation(() => {});
  const historyMock = { push: jest.fn() };
  const component = render(<Post_item onSubmit={spy} history={historyMock}/>);
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
  const component = render(<Post_item onSubmit={spy} history={historyMock}/>);
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
  const component = render(<Post_item onSubmit={spy} history={historyMock}/>);
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
  const component = render(<Post_item onSubmit={spy} history={historyMock}/>);
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
  const component = render(<Post_item onSubmit={spy} history={historyMock}/>);
  const { getByTestId } = component;

  const inputCost = component.getByLabelText('Item Cost:');
  fireEvent.change(inputCost, { target: {value: '1'}});

  const inputName = component.getByLabelText('Item Name:');
  fireEvent.change(inputName, { target: {value: 'a'}});

  fireEvent.submit(getByTestId("form"));
  expect(postBackend.postItemBackend).toHaveBeenCalledTimes(1);
});
