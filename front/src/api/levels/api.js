import axios from "axios";
import {QueryBuilder} from "../../helpers/QueryBuilder";
const baseURL = "http://localhost:8010/api/v1";

export const getLevels = async (page = 0) => {
    const url = new QueryBuilder(`${baseURL}`)
        .setPath('/levels')
        .addParam('page', page)
        .addParam('size', 3)
        .build();
    const { data } = await axios.get(url);
    return data;
};

export const getLevelById = async (id) => {
    const url = new QueryBuilder(`${baseURL}`)
        .setPath('/levels')
        .addParam('levelIds', id)
        .build();
    const { data } = await axios.get(url);
    return data;
};

export const getLevelsByGameIdAndPage = async (id, page = 0) => {
    const url = new QueryBuilder(`${baseURL}`)
        .setPath('/levels')
        .addParam('gameIds', id)
        .addParam('page', page)
        .addParam('size', 3)
        .build();
    const { data } = await axios.get(url);
    return data;
}