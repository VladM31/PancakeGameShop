import React, { useContext } from 'react';
import { CurrencyContext } from '../../context/CurrencyContext';
import { Typography } from "@mui/material";

const Product = ({ priceInUSD, variant = 'h5', sx, discount = 0 }) => {
    const { currency, rate, currencySymbols } = useContext(CurrencyContext);
    const priceInCurrentCurrency = priceInUSD * (rate.value || 0);
    const currencySymbol = currencySymbols[currency] || currency; // если символа нет, используем код валюты
    const discountedPrice = discount > 0 ? priceInCurrentCurrency * ((100-discount)/100) : priceInCurrentCurrency;

    return (
        <Typography color={'white'} variant={variant} sx={sx}>
            Ціна: {discountedPrice.toFixed(2)} {currencySymbol}
        </Typography>
    )
};

export default Product;