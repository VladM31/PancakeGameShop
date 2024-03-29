import axios from "axios";
import {QueryBuilder} from "../../helpers/QueryBuilder";

const baseURL = "http://localhost:8010/api/v1";

export const getLevels = async (page = 0) => {
    const url = new QueryBuilder(`${baseURL}`)
        .setPath('/levels')
        .addParam('page', page)
        .addParam('size', 3)
        .build();
    try {
        const {data} = await axios.get(url);
        return data;
    } catch (e) {
        console.log(e)
    }
};

export const getLevelById = async (id) => {
    const url = new QueryBuilder(`${baseURL}`)
        .setPath('/levels')
        .addParam('levelIds', id)
        .build();
    try {
        const {data} = await axios.get(url);
        return data;
    } catch (e) {
        console.log(e)
    }
};

export const getLevelsByGameIdAndPage = async (id, page = 0) => {
    const url = new QueryBuilder(`${baseURL}`)
        .setPath('/levels')
        .addParam('gameIds', id)
        .addParam('page', page)
        .addParam('size', 3)
        .build();
    try {
        const {data} = await axios.get(url);
        return data;
    } catch (e) {
        console.log(e)
    }
}

export const getLevelsByFilter = async (gameId, filterParams, page = 0) => {
    const urlBuilder = new QueryBuilder(`${baseURL}`)
        .setPath('/levels')
        .addParam('gameIds', gameId)
        .addParam('name', filterParams.name)
        .addParam('page', page)
        .addParam('size', 3);

    if (filterParams.minPrice && filterParams.minPrice >= 0) {
        urlBuilder.addParam('price.from', filterParams.minPrice);
    }

    if (filterParams.maxPrice && filterParams.maxPrice > 0) {
        urlBuilder.addParam('price.to', filterParams.maxPrice);
    }

    const url = urlBuilder.build();

    try {
        const {data} = await axios.get(url);
        return data;
    } catch (e) {
        console.log(e);
    }
}