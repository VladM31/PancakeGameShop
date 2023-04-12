import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import * as api from "./api";

// Async actions using redux-thunk
export const checkUser = createAsyncThunk("user/checkUser", async (phoneNumber = "380635662050") => {
    try {
        const data = await api.isRegistered(phoneNumber);
        console.log(data);
    } catch (error) {
        console.log(error);
    }
});

export const registerUser = createAsyncThunk("user/registerUser", async (user = { phoneNumber: '380635662050', password: '12345678', firstName: 'Vasya', lastName: 'Petya', nickname: 'zeebro2223', birthDate: '2003-06-05', currency: 'USD' }) => {
    try {
        const data = await api.register(user);
        console.log(data);
    } catch (error) {
        console.log(error);
    }
});

const userSlice = createSlice({
    name: "user",
    initialState: {
        user: {
            id: 0,
            phoneNumber: 0,
            nickname: "",
            firstName: "",
            lastName: "",
            birthDate: "",
            currency: "",
            email: null,
        },
        token: {
            value: "",
            expiresIn: "",
        },
    },
    reducers: {
        initToken: (state, action) => {
            state.token = action.payload;
        },
        logout: (state, action) => {
            state.user = {
                id: 0,
                phoneNumber: 0,
                nickname: "",
                firstName: "",
                lastName: "",
                birthDate: "",
                selectedCurrency: "",
                email: null,
                role: "",
            };
            state.token = action.payload;
        },
    },
});

export const { initToken, logout } = userSlice.actions;

export default userSlice.reducer;