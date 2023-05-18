import {configureStore} from "@reduxjs/toolkit";
import userReducer from "../reducers/user/userStore";
import cartReducer from "../reducers/cart/cartStore";

const store = configureStore({
  reducer: {
    userStore: userReducer,
    cartStore: cartReducer,
  }
});

export default store;
