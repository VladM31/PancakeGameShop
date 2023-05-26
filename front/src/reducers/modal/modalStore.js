import {createSlice} from "@reduxjs/toolkit";


const modalSlice = createSlice({
    name: "modal",
    initialState: {
        isVideoModal: false,
        videoModalUrl: "",
    },
    reducers: {
        openVideoModal: (state, action) => {
            state.isVideoModal = true;
        },
        closeVideoModal: (state, action) => {
            state.isVideoModal = false;
        },
        setVideoModalUrl: (state, action) => {
            state.videoModalUrl = action.payload;
        }
    },
});

export const {openVideoModal, closeVideoModal, setVideoModalUrl} = modalSlice.actions;

export default modalSlice.reducer;