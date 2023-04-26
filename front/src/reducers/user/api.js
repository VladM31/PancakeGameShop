import axios from "axios";
const baseURL = "http://localhost:8005/api/v1/auth";

export const isRegistered = async (phoneNumber) => {
    const { data } = await axios.get(`${baseURL}/is-registered?phoneNumber=${phoneNumber}`);
    return data;
};

export const register = async (user) => {
    try {
        const { data } = await axios.post(`${baseURL}/sign-up`, user);
        if(!data) {
            return {success: false, message: 'This phone number is already registered'};
        }
        return { success: data };
    } catch (e) {
        return { success: false, message: e.response.data };
    }
};

export const tokenToUser = async (token) => {
    const { data } = await axios.get(`${baseURL}/token-to-user`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
    return data;
};

export const login = async (phoneNumber, password) => {
    return await axios.post(`${baseURL}/login`, {phoneNumber, password});
}