/**
 * @author tanxin
 * @date $
 * @Description: state
 */
import {createStore, combineReducers, applyMiddleware} from "redux";
import thunk from 'redux-thunk';

import {userReducer} from './user/reducer.js'
import {numReducer} from "./page1/reducer";


const store = createStore(
  combineReducers({
    userReducer,
    numReducer,
  }),
  applyMiddleware(thunk)
);

export default store;
