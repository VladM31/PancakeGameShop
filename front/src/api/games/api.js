import axios from "axios";
import {QueryBuilder} from "../../helpers/QueryBuilder";
import Cookies from "js-cookie";
import { saveAs } from 'file-saver';

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

export const downloadGame = async (id, name, platform) => {
    const url = new QueryBuilder(`${baseURL}`)
        .setPath(`/games/${id}/file/${platform}`)
        .build();
    console.log(url);
    try {
        const file = await axios.get(url, {
            headers: {
                Authorization: `Bearer ${JSON.parse(Cookies.get('token')).value}`
            },
            responseType: 'blob'
        })
        saveAs(new Blob([file.data]), `${name}.zip`);
    }catch (e) {
        console.log(e)
    }

}