import React, { useEffect, useState } from 'react';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import { Checkbox } from '@mui/material';
import { debounce } from 'lodash';

const Filter = ({ onFilter }) => {
  const [name, setName] = useState('');
  const [minPrice, setMinPrice] = useState('');
  const [maxPrice, setMaxPrice] = useState('');
  const [isBought, setIsBought] = useState(false);

  

  const handleFilter = () => {
	console.log('Filtering...')
    // onFilter({ name, minPrice, maxPrice });
  };

  useEffect(() => {
    const debouncedOnFilter = debounce(() => handleFilter(), 500);
	debouncedOnFilter();
    return () => debouncedOnFilter.cancel();
  }, [name, minPrice, maxPrice, isBought, onFilter]);

  return (
    <Box sx={{ width: 325, marginLeft: '20px', backgroundColor: '#B55D9C', padding: '40px', borderRadius: '15px',}}>
      <Typography variant="h5">Filter Items</Typography>
      <TextField
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
        <TextField
          label="Min Price"
          type="number"
          value={minPrice}
          onChange={(e) => setMinPrice(e.target.value)}
          fullWidth
          margin="normal"
        />
        <TextField
          label="Max Price"
          type="number"
          value={maxPrice}
          onChange={(e) => setMaxPrice(e.target.value)}
          fullWidth
          margin="normal"
        />
		</Box>
		<Box sx={{display: 'flex', justifyContent: 'center', alignContent: 'center'}}>
			<Checkbox
			checked={isBought}
			size="small"
			onChange={(e) => setIsBought(e.target.checked)}
			/>
			<Typography sx={{ display: 'flex', alignItems: 'center' }} variant="body1">Тільки не куплені рівні</Typography>
		</Box>
    </Box>
  );
};

export default Filter;