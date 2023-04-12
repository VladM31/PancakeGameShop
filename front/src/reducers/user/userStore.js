import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import * as api from "./api";

// Async actions using redux-thunk
export const checkUser = createAsyncThunk("user/checkUser", async (phoneNumber) => {
    try {
        const data = await api.isRegistered(phoneNumber);
        console.log(data);
    } catch (error) {
        console.log(error);
    }
});

export const registerUser = createAsyncThunk("user/registerUser", async (user ) => {
    try {
        const data = await api.register(user);
        console.log(data);
    } catch (error) {
        console.log(error);
    }
});

export const loginUser = createAsyncThunk("user/login", async (payload) => {
    try {
        const result = await api.login(payload.phoneNumber, payload.password);
        return { success: true, message: 'Login successful', user: result.data.user, token: {value: result.data.tokenValue, expiresIn: result.data.tokenExpirationTime} };
    } catch (error) {
        return { success: false, message: error.response.data };
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
        initUser: (state, action) => {
            state.user = action.payload;
        },
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
        extraReducers: (builder) => {
            builder
                .addCase(loginUser.fulfilled, (state, action) => {
                    if (action.payload.success) {
                        state.user = action.payload.user;
                        state.token = action.payload.token;
                    }
                })
            // Обработка состояния для других асинхронных функций (если необходимо)
            // .addCase(checkUser.fulfilled, ...)
            // .addCase(registerUser.fulfilled, ...)
        },
    },
});

export const { initToken, logout } = userSlice.actions;

export default userSlice.reducer;