import { Box, Button, Typography } from '@mui/material';
import React, { useEffect } from 'react';
import ImageCarousel from '../components/Carousels/ImagesCarousel';

function Level() {
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
    const level = { 
      levelId: 1,
      gameId: 1,
      mainImage: 'https://m.media-amazon.com/images/M/MV5BMzgyZWEzMDgtMzI0YS00ZDMwLTllNjQtZjE3ZmVkNWM3YzliXkEyXkFqcGdeQXVyMTYxNzI4OTYx._V1_FMjpg_UX1000_.jpg',
      name: 'Minecraft : Чарівне місто',
      description: 'В рівні продемостровано уявне європейське місто в Minecraft, розділене на кілька районів. Кожен район відрізняється унікальними та чудовими пейзажами. Ти зможеш зібрати всіх своїх друзів, видати кожному дім та розпочати виживання. У цьому місті багато вишуканих деталей, які тобі належить дослідити, а інтер\'єри будинків також мають меблі.',
      images: ['https://www.ionos.at/digitalguide/fileadmin/_processed_/1/c/csm_linux-minecraft-server-t_dc835841c1.jpg', 'https://assets2.rockpapershotgun.com/minecraft-house-ideas-hobbit-hole.jpg/BROK/resize/1920x1920%3E/format/jpg/quality/80/minecraft-house-ideas-hobbit-hole.jpg', 'https://www.popsci.com/uploads/2022/02/03/Minecraft-Tips-Parents.jpeg', 'https://ftw.usatoday.com/wp-content/uploads/sites/90/2022/05/Minecraft-attacking-a-SkeletoN.jpg'],
      price: 10, 
    };
    setLevel(level)
  }

  useEffect(() => {
    getLevel();
  }, [])

  return (
    <Box>
      <Box sx={{display: 'flex', flexDirection: 'column', width: 1000, backgroundColor: '#B55D9C', padding: '40px', borderRadius: '15px',}}>
        <ImageCarousel height={'400'} images={level.images} />
        <Box sx={{display: 'flex', flexDirection: 'column'}}>
          <Typography align='center' sx={{marginTop: '10px'}} color='white' variant='h5'>{level.name}</Typography>
          <Box sx={{display: 'flex', flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center'}}>
            <Typography color='white' variant='h5'>Ціна: {level.price}$</Typography>
            <Button sx={{backgroundColor: 'white', color: 'black'}}>У кошик</Button>
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