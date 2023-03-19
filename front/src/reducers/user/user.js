import { createSlice } from "@reduxjs/toolkit";

export const userSlice = createSlice({
  name: "user",
  initialState: {
    user: {
        id: 0,
        phoneNumber: 0,
        nickname: "",
        firstname: "",
        lastname: "",
        birthDate: "",
        selectedCurrency: "",
        email: null,
        role: "",
		  },
		  token: {
        value: "",
        expiresIn: ""
		  },
  },
  reducers: {
	  initUser: (state, payload) => {
      state.user = payload;
	  },
	  initToken: (state, payload) => {
      state.token = payload;
	  },
	  logout: (state, action) => {
      state = {
        ...state,
        user: {
          id: 0,
          phoneNumber: 0,
          nickname: "",
          firstname: "",
          lastname: "",
          birthDate: "",
          selectedCurrency: "",
          email: null,
          role: "",
        },
        token: action.payload,
      };
	  },
  },
});
  
export const { initUser, initToken, logout } = userSlice.actions;
  
export default userSlice.reducer;