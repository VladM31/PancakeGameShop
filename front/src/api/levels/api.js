import axios from "axios";
const baseURL = "http://localhost:8010/api/v1";

export const getLevels = async (page = 0) => {
    const { data } = await axios.get(`${baseURL}/levels`, {
        params: {page, size: 3}
    });
    return data;
};

export const getLevelById = async (id) => {
    const { data } = await axios.get(`${baseURL}/levels`, {
        params: {levelIds: id}
    });
    return data;
};

export const getLevelsByGameIdAndPage = async (id, page = 0) => {
    const { data } = await axios.get(`${baseURL}/levels`, {
        params: {gameIds: id, page, size: 3}
    });
    return data;
}