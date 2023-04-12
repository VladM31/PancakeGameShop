import axios from "axios";

const baseURL = "http://localhost:8005/api/v1/auth";

export const isRegistered = async (phoneNumber) => {
    const { data } = await axios.get(`${baseURL}/is-registered?phoneNumber=${phoneNumber}`);
    return data;
};

export const register = async (user) => {
    const { data } = await axios.post(`${baseURL}/sign-up`, user);
    return data;
};