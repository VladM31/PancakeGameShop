import React, { useContext } from 'react';
import { CurrencyContext } from '../../context/CurrencyContext';
import { Typography } from "@mui/material";

const Product = ({ priceInUSD, variant = 'h5', sx }) => {
    const { currency, rate, currencySymbols } = useContext(CurrencyContext);
    const priceInCurrentCurrency = priceInUSD * (rate.value || 0);
    const currencySymbol = currencySymbols[currency] || currency; // если символа нет, используем код валюты

    return <Typography color={'white'} variant={variant} sx={sx}>Ціна: {priceInCurrentCurrency < 1000 ? priceInCurrentCurrency.toFixed(2) :  priceInCurrentCurrency.toFixed(0)} {currencySymbol}</Typography>
};

export default Product;