import axios from "axios";
import {QueryBuilder} from "../../helpers/QueryBuilder";
import Cookies from "js-cookie";

const baseURL = "http://localhost:8010/api/v1";

export const getGames = async () => {
    const url = new QueryBuilder(`${baseURL}`)
        .setPath('/games')
        .build();
    const { data } = await axios.get(url);
    return data;
};

export const getGameById = async (gameId) => {
    const url = new QueryBuilder(`${baseURL}`)
        .setPath('/games/levels')
        .addParam('gameIds', gameId)
        .build();

    const { data } = await axios.get(url);
    return data;
};