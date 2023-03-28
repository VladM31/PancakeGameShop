import React from 'react';
import GameCard from '../components/Cards/GameCard';
import { Box } from '@mui/material';

function Main() {
  return (
    <Box>
      <GameCard 
      mainImage='https://m.media-amazon.com/images/M/MV5BMzgyZWEzMDgtMzI0YS00ZDMwLTllNjQtZjE3ZmVkNWM3YzliXkEyXkFqcGdeQXVyMTYxNzI4OTYx._V1_FMjpg_UX1000_.jpg'
      name='Minecraft'
      images={['https://www.ionos.at/digitalguide/fileadmin/_processed_/1/c/csm_linux-minecraft-server-t_dc835841c1.jpg', 'https://assets2.rockpapershotgun.com/minecraft-house-ideas-hobbit-hole.jpg/BROK/resize/1920x1920%3E/format/jpg/quality/80/minecraft-house-ideas-hobbit-hole.jpg', 'https://www.popsci.com/uploads/2022/02/03/Minecraft-Tips-Parents.jpeg', 'https://ftw.usatoday.com/wp-content/uploads/sites/90/2022/05/Minecraft-attacking-a-SkeletoN.jpg']}
      releaseDate='2023-03-26'
      price={50} 
      />
    </Box>
  );
}

export default Main;