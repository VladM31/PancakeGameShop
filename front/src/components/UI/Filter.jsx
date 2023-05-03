import React, {useEffect, useState} from 'react';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import {debounce} from 'lodash';
import {withStyles} from "@mui/styles";
import {getLevelsByFilter} from "../../api/levels/api";

const CssTextField = withStyles({
    root: {
        '& label': {
            color: 'white',
        },
        '&:hover label': {
            color: '#00000044',
        },
        '& label.Mui-focused': {
            color: '#000000',
        },
        '& .MuiInput-underline:after': {
            borderBottomColor: '#00000044',
        },
        '& .MuiOutlinedInput-root': {
            '& fieldset': {
                borderColor: 'white',
            },
            '&:hover fieldset': {
                borderColor: '#00000044',
            },
            '&.Mui-focused fieldset': {
                borderColor: '#00000044',
            },
        },
    },
})(TextField);

const Filter = ({onFilter, gameId, currentPage}) => {
    const [name, setName] = useState('');
    const [minPrice, setMinPrice] = useState(undefined);
    const [maxPrice, setMaxPrice] = useState(undefined);

    const handleFilter = async () => {
        const filterRes = await getLevelsByFilter(gameId, {name, minPrice, maxPrice}, currentPage - 1);
        onFilter(filterRes);
    };

    useEffect(() => {
        const debouncedOnFilter = debounce(() => handleFilter(), 1000);
        debouncedOnFilter();
        return () => debouncedOnFilter.cancel();
    }, [name, minPrice, maxPrice, onFilter]);

    return (
        <Box sx={{width: 325, marginLeft: '20px', backgroundColor: '#B55D9C', padding: '40px', borderRadius: '15px',}}>
            <Typography variant="h5" color={'white'}>Filter Items</Typography>
            <CssTextField
                label="Name"
                value={name}
                onChange={(e) => setName(e.target.value)}
                fullWidth
                margin="normal"
            />
            <Box
                display="flex"
                justifyContent="space-between"
                alignItems="center"
            >
                <CssTextField
                    label="Min Price"
                    type="number"
                    value={minPrice}
                    sx={{mr: 1}}
                    onChange={(e) => e.target.value < 0  ? setMinPrice(0) : setMinPrice(e.target.value)}
                    fullWidth
                    margin="normal"
                />
                <CssTextField
                    label="Max Price"
                    type="number"
                    value={maxPrice}
                    onChange={(e) => e.target.value < 0 ? setMaxPrice(0) : setMaxPrice(e.target.value)}
                    fullWidth
                    margin="normal"
                />
            </Box>
        </Box>
    );
};

export default Filter;