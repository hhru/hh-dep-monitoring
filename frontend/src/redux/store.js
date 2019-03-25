import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';

import { repositoriesReducer, initialState } from './repositoriesReducer';

export default createStore(repositoriesReducer, initialState, applyMiddleware(thunk));
