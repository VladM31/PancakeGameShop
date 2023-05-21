import axios from "axios";
import {QueryBuilder} from "../../helpers/QueryBuilder";
import Cookies from "js-cookie";

const baseURL = "http://localhost:8010/api/v1";

export const getPromoCodeDiscount = async (promoCode) => {
    const url = new QueryBuilder(`${baseURL}`)
        .setPath(`/promo-code/${promoCode}/discount`)
        .build();

    try {
        const { data } = await axios.get(url, {
            headers: {
                Authorization: `Bearer ${JSON.parse(Cookies.get('token')).value}`,
            },
        });
        return {success: true, data};
    } catch (e) {
        return {success: false, error: 'Промокод не найден'};
    }
};

export const purchase = async (gameIds, levelIds, email, phoneNumber, creditCard, promoCode) => {
    const url = new QueryBuilder(`${baseURL}`)
        .setPath('/payment/buy')
        .build();

    // Получить скидку для промокода
    const {success, data: discount} = await getPromoCodeDiscount(promoCode);
    if (!success) {
        // обработка ошибки
    }

    try {
        const { data } = await axios.post(url, {
            gameIds,
            levelIds,
            email,
            phoneNumber,
            creditCard,
            discount
        }, {
            headers: {
                Authorization: `Bearer ${JSON.parse(Cookies.get('token')).value}`,
            },
        });
        return {success: true, data};
    } catch (e) {
        return {success: false, error: e.response.data};
    }
};