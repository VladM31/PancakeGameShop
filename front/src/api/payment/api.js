import axios from "axios";
import {QueryBuilder} from "../../helpers/QueryBuilder";
import Cookies from "js-cookie";

const baseURL = "http://localhost:8010/api/v1";

export const purchase = async (gameIds, levelIds, email, phoneNumber, creditCard) => {
    const url = new QueryBuilder(`${baseURL}`)
        .setPath('/payment/buy')
        .build();

    try {
        const { data } = await axios.post(url, {
            gameIds,
            levelIds,
            email,
            phoneNumber,
            creditCard
        }, {
            headers: {
                Authorization: `Bearer ${JSON.parse(Cookies.get('token')).value}`,
            },
        });
        return data;
    } catch (e) {
        console.log(e)
    }
};