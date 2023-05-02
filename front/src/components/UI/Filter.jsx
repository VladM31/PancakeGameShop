import React, {useEffect, useState} from 'react';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import {Checkbox} from '@mui/material';
import {debounce} from 'lodash';
import {withStyles} from "@mui/styles";

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

const Filter = ({onFilter}) => {
    const [name, setName] = useState('');
    const [minPrice, setMinPrice] = useState('');
    const [maxPrice, setMaxPrice] = useState('');
    const [isBought, setIsBought] = useState(false);

    const handleFilter = () => {
        // console.log('Filtering...')
        // onFilter({ name, minPrice, maxPrice });
    };

    useEffect(() => {
        const debouncedOnFilter = debounce(() => handleFilter(), 500);
        debouncedOnFilter();
        return () => debouncedOnFilter.cancel();
    }, [name, minPrice, maxPrice, isBought, onFilter]);

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
                    onChange={(e) => e.target.value < 0 ? setMinPrice(0) : setMinPrice(e.target.value)}
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
            <Box sx={{display: 'flex', justifyContent: 'center', alignContent: 'center'}}>
                <Checkbox
                    checked={isBought}
                    size="small"
                    onChange={(e) => setIsBought(e.target.checked)}
                    sx={{
                        color: 'white',
                        '&.Mui-checked': {
                            color: '#ff8ee1',
                        },
                    }}
                />
                <Typography sx={{display: 'flex', alignItems: 'center'}} color={'white'} variant="body1">Тільки не
                    куплені рівні</Typography>
            </Box>
        </Box>
    );
};

export default Filter;