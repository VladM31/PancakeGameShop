import {configureStore} from "@reduxjs/toolkit";
import userReducer from "../reducers/user/userStore";
import cartReducer from "../reducers/cart/cartStore";
import modalReducer from "../reducers/modal/modalStore";

const store = configureStore({
  reducer: {
    userStore: userReducer,
    cartStore: cartReducer,
    modalStore: modalReducer,
  }
});

export default store;
