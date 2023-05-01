import axios from "axios";
const baseURL = "http://localhost:8010/api/v1";

export const getGames = async () => {
    const { data } = await axios.get(`${baseURL}/games`);
    return data;
};

export const getGameById = async (gameId) => {
    const { data } = await axios.get(`${baseURL}/games/levels`, {
        params: {
            gameIds: gameId,
        }
    });
    return data;
}