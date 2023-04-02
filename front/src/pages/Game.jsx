// Game.jsx
import React, { useEffect } from 'react';
import { useParams } from 'react-router-dom';
import ImageCarousel from '../components/Carousels/ImagesCarousel';
import { Box, Button, Typography } from '@mui/material';
import SmallGameOrLevelCard from '../components/Cards/SmallGameOrLevelCard';
import Filter from '../components/UI/Filter';

function Game() {
  const { id } = useParams();

  const [game, setGame] = React.useState({
    id: 3,
    genres: ['Action', 'RPG'],
    name: 'S.T.A.L.K.E.R. 2',
    description: 'Minecraft (від англ. mine craft — досл. «шахтарське ремесло») — відеогра від незалежної студії Mojang 2011 року жанру «пісочниця» у відкритому світі з виглядом від першої/третьої особи. Гра започаткувала однойменну серію, для всіх творів якої характерний мінімалістичний кубічний дизайн.',
    images: [
      'https://via.placeholder.com/300x200/001F3F/FFFFFF/?text=Image1',
      'https://via.placeholder.com/300x200/0074D9/FFFFFF/?text=Image2',
      'https://via.placeholder.com/300x200/7FDBFF/FFFFFF/?text=Image3',
      'https://via.placeholder.com/300x200/39CCCC/FFFFFF/?text=Image4',
    ],
    minAgeRating: 6,
    releaseDate: '2024-03-26',
    price: 50, 
  });
  const [levels, setLevels] = React.useState({
    currentPage: 0,
    results: [
      { id: 1, name: 'Level 1', price: 15, image: 'https://via.placeholder.com/300x200/001F3F/FFFFFF/?text=Image1' },
      { id: 2, name: 'Level 2', price: 20, image: 'https://via.placeholder.com/300x200/001F3F/FFFFFF/?text=Image2' },
      { id: 3, name: 'Level 3', price: 35, image: 'https://via.placeholder.com/300x200/001F3F/FFFFFF/?text=Image3' },
    ],
    pages: 3,
  });

  // function getLevels(gameId) {
  //   // fetch(`http://localhost:3000/api/levels/${gameId}`)
  //   //   .then((response) => response.json())
  //   //   .then((data) => console.log(data))
  //   //   .catch((error) => console.log(error));
  // }

  // function getGame(gameId) {
  //   // fetch(`http://localhost:3000/api/games/${gameId}`)
  //   //   .then((response) => response.json())
  //   //   .then((data) => console.log(data))
  //   //   .catch((error) => console.log(error));
  // }

  // useEffect(() => {
  //   setGame(getGame(id));
  //   setLevels(getLevels(id));
  // }, [id])

  return (
    <>
      <Box>
        <Box sx={{ display: 'flex', width: 1000, heigh: 400, backgroundColor: '#B55D9C', padding: '40px', borderRadius: '15px',}}>
          <ImageCarousel images={game.images} /> 
          <Box sx={{ display: 'flex', width: '50%', justifyContent: 'space-between', flexDirection: 'column', marginLeft: '20px' }}>
            <Box sx={{ display: 'flex', gap: '10px', flexDirection: 'column' }}>
              <Typography color={'white'} variant='h4' align='center'>{game.name}</Typography>
              <Typography color={'white'} variant='h5'>Рейтинг: {game.minAgeRating}+</Typography>
              <Typography color={'white'} variant='h5'>Дата виходу: {game.releaseDate}</Typography>
              <Typography color={'white'} variant='h5'>Ціна: {game.price}$</Typography>
              <Typography color={'white'} variant='h5'>Жанри: {game.genres.join(', ')}</Typography>
            </Box>
            <Box alignSelf={'end'}>
              <Button onClick={(e) => {e.stopPropagation();}} variant="contained" color="inherit">
                В корзину
              </Button>
            </Box>
          </Box>
        </Box>
        <Typography sx={{marginTop: '20px'}} align='center' variant='h3' color={'white'}>
          Опис
        </Typography>
        <Box sx={{ width: 1000, heigh: 400, backgroundColor: '#B55D9C', padding: '40px', borderRadius: '15px',}}>
          <Typography variant='h6' color={'white'}>
          {game.description}
          </Typography>
        </Box>
        <Typography sx={{marginTop: '20px'}} align='center' variant='h3' color={'white'}>
          Рівні гри
        </Typography>
        <Box sx={{display: 'flex'}}>
          <Box>
              { levels.results.map((level) => (
                <SmallGameOrLevelCard cardType='gamepage' key={level.id} id={level.id} mainImage={level.image} name={level.name} price={level.price} />
              )) }
          </Box>
          <Box>
                <Filter />
          </Box>
        </Box>
      </Box>
    </>
  );
}

export default Game;