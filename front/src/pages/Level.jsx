import { Box, Button, Typography } from '@mui/material';
import React, { useEffect } from 'react';
import ImageCarousel from '../components/Carousels/ImagesCarousel';
import {getLevelById} from "../api/levels/api";
import {useParams} from "react-router-dom";
import {getGameById} from "../api/games/api";
import {isPastDate} from "../helpers/Date";
import {addToCart} from "../reducers/cart/cartStore";
import {useDispatch} from "react-redux";

function Level() {
  const {levelId, gameId} = useParams();

  const [level, setLevel] = React.useState({});
  const [releaseDate, setReleaseDate] = React.useState('');

  const dispatch = useDispatch();

  async function getGameDate() {
    const { content } = await getGameById(gameId);
    setReleaseDate(content[0].releaseDate);
  }

  async function getLevel() {
    const { content: level } = await getLevelById(levelId);
    setLevel(level[0])
  }

  const buyHandler = (e, item) => {
    e.stopPropagation();
    dispatch(addToCart(item))
  };

  useEffect(() => {
    getGameDate();
    getLevel();
  }, [])

  return (
    <Box>
      <Box sx={{display: 'flex', flexDirection: 'column', width: 1000, backgroundColor: '#B55D9C', padding: '40px', borderRadius: '15px',}}>
        <ImageCarousel height={'400'} images={level.images ? level.images : []} />
        <Box sx={{display: 'flex', flexDirection: 'column'}}>
          <Typography align='center' sx={{marginTop: '10px'}} color='white' variant='h5'>{level.name}</Typography>
          <Box sx={{display: 'flex', flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center'}}>
            {releaseDate ? isPastDate(releaseDate) ? (
                <Box sx={{
                  display: 'flex',
                  width: '100%',
                  justifyContent: 'space-between',
                }}>
                  <Typography variant="h5" color="white">Ціна {level.price}$</Typography>
                  <Button onClick={(e) => buyHandler(e, {
                    levelId: level.levelId,
                    price: level.price,
                    name: level.name,
                    mainImage: level.mainImage,
                  })} variant="contained" color="inherit">В
                    корзину</Button>
                </Box>
            ) : (
                <Typography align={'right'} sx={{width: '100%'}} variant="h5" color="white">Гра ще в
                  розробці</Typography>
            ) : null}
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