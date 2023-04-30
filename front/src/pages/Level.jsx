import { Box, Button, Typography } from '@mui/material';
import React, { useEffect } from 'react';
import ImageCarousel from '../components/Carousels/ImagesCarousel';
import {getLevelById} from "../api/levels/api";
import {useParams} from "react-router-dom";

function Level() {
  const {levelId} = useParams();

  const [level, setLevel] = React.useState({
    levelId: 0,
    gameId: 0,
    mainImage: '',
    name: '',
    description: '',
    images: [],
    price: 0, 
  });

  async function getLevel() {
    const { content: level } = await getLevelById(levelId);
    setLevel(level[0])
  }

  useEffect(() => {
    getLevel();
  }, [])

  return (
    <Box>
      <Box sx={{display: 'flex', flexDirection: 'column', width: 1000, backgroundColor: '#B55D9C', padding: '40px', borderRadius: '15px',}}>
        <ImageCarousel height={'400'} images={level.images ? level.images : []} />
        <Box sx={{display: 'flex', flexDirection: 'column'}}>
          <Typography align='center' sx={{marginTop: '10px'}} color='white' variant='h5'>{level.name}</Typography>
          <Box sx={{display: 'flex', flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center'}}>
            <Typography color='white' variant='h5'>Ціна: {level.price}$</Typography>
            <Button sx={{backgroundColor: 'white', color: 'black'}}>В корзину</Button>
          </Box>
        </Box>
      </Box>
      <Typography variant='h5'>Опис</Typography>
      <Box sx={{ width: 1000, backgroundColor: '#B55D9C', padding: '40px', borderRadius: '15px'}}>
        <Typography color='white' variant='h5'>{level.description}</Typography>
      </Box>
    </Box>
  );
}

export default Level;