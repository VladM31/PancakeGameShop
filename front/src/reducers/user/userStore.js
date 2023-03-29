import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

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
	  initUser: async (state) => {
     try {
      let { data } = await axios.get('')
      if(!Object.keys(data).length) {
        data = state.user;
      }
      state.user = data;
     } catch(e) {
      console.log(e);
     }
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