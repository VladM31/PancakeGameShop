import React, { createContext, useState, useEffect } from 'react';

export const CurrencyContext = createContext();

export const CurrencyProvider = ({ children }) => {
    const initialCurrency = localStorage.getItem('currency') || 'USD';
    const [currency, setCurrency] = useState(initialCurrency);
    const [rate, setRate] = useState({});
    const currencySymbols = {
        'USD': '$', // US Dollar
        'EUR': '€', // Euro
        'JPY': '¥', // Japanese Yen
        'GBP': '£', // British Pound Sterling
        'AUD': 'A$', // Australian Dollar
        'CAD': 'C$', // Canadian Dollar
        'UAH': '₴', // Ukrainian Hryvnia
        // добавьте любые другие валюты по необходимости
    };

    useEffect(() => {
        localStorage.setItem('currency', currency);
    }, [currency]);

    useEffect(() => {
        fetch('https://api.currencyapi.com/v3/latest?apikey=VYSm83qa0BIT75Huo2DXr5CaBuURjmuVKnlNxYzE')
            .then(response => response.json())
            .then(data =>  {
                console.log(data.data[currency]);
                setRate(data.data[currency])
            })
            .catch(error => console.error(error));
    }, [currency]);

    const value = { currency, setCurrency, rate, currencySymbols };

    return (
        <CurrencyContext.Provider value={value}>
            {children}
        </CurrencyContext.Provider>
    );
};