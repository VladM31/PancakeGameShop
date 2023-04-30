// slices/cartSlice.js
import { createSlice } from '@reduxjs/toolkit';
import Cookies from 'js-cookie';

const cartSlice = createSlice({
    name: 'cart',
    initialState: {
        items: [],
    },
    reducers: {
        initCart: (state) => {
          const cart = Cookies.get('cart');
            if(cart) {
                state.items = JSON.parse(cart);
            } else {
                state.items = [];
            }
        },
        addToCart: (state, action) => {
            if(state.items.find(item => item.levelId ? item.levelId === action.payload.levelId : item.gameId === action.payload.gameId)) return;
            state.items.push(action.payload);
            Cookies.set('cart', JSON.stringify(state.items));
        },
        removeFromCart: (state, action) => {
            state.items = state.items.filter(item => item.levelId ? item.levelId !== action.payload : item.gameId !== action.payload);
            Cookies.set('cart', JSON.stringify(state.items));
        },
        clearCart: (state) => {
            state.items = [];
        },
    },
});

export const { addToCart, removeFromCart, updateCartItem, clearCart, initCart } = cartSlice.actions;
export default cartSlice.reducer;