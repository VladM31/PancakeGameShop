import axios from "axios";
import {QueryBuilder} from "../../helpers/QueryBuilder";
import Cookies from "js-cookie";

const baseURL = "http://localhost:8010/api/v1";

export const getGames = async () => {
    const url = new QueryBuilder(`${baseURL}`)
        .setPath('/games')
        .build();
    try {
        const {data} = await axios.get(url);
        return data;
    } catch (e) {
        console.log(e);
    }
};

export const getGameById = async (gameId) => {
    const url = new QueryBuilder(`${baseURL}`)
        .setPath('/games/levels')
        .addParam('gameIds', gameId)
        .build();

    try {
        const {data} = await axios.get(url);
        return data;
    } catch (e) {
        console.log(e);
    }
};

export const getBoughtContent = async () => {
    const url = new QueryBuilder(`${baseURL}`)
        .setPath('/bought-content/details')
        .build();

    if(!Cookies.get('token')) return {content: undefined};

    try {
        const {data} = await axios.get(url, {
            headers: {
                Authorization: `Bearer ${JSON.parse(Cookies.get('token')).value}`
            }
        });
        return data;
    } catch (e) {
        console.log(e);
    }
}