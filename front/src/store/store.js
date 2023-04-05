import {configureStore} from "@reduxjs/toolkit";
import userReducer from "../reducers/user/userStore";

const store = configureStore({
  reducer: {
    userStore: userReducer,
  }
});

export default store;
