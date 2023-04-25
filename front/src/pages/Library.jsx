import React, { useEffect } from 'react';
import SmallGameOrLevelCard from '../components/Cards/SmallGameOrLevelCard';
import { Box, Typography } from '@mui/material';
import withAuthProtection from "../hoc/withAuthProtection";

function Library() {
  const [boughtGames, setboughtGame] = React.useState([{
    levelId: 0,
    gameId: 0,
    mainImage: '',
    name: '',
    purchasedDate: '',
    price: 0, 
  }]);

  const [boughtLevels, setboughtLevels] = React.useState([{
    levelId: 0,
    gameId: 0,
    mainImage: '',
    name: '',
    purchasedDate: '',
    price: 0, 
  }]);

  const [showCards, setShowCards] = React.useState(false);

  useEffect(() => {
    getBoughtGames();
  }, [])

  async function getBoughtGames() {
    const boughtGames = [{ 
      levelId: 1,
      gameId: 1,
      mainImage: 'https://m.media-amazon.com/images/M/MV5BMzgyZWEzMDgtMzI0YS00ZDMwLTllNjQtZjE3ZmVkNWM3YzliXkEyXkFqcGdeQXVyMTYxNzI4OTYx._V1_FMjpg_UX1000_.jpg',
      purchasedDate: '2021-10-10',
      name: 'Minecraft : Чарівне місто',
    }];
    const boughtLevels = [{ 
      levelId: 1,
      gameId: 1,
      mainImage: 'https://m.media-amazon.com/images/M/MV5BMzgyZWEzMDgtMzI0YS00ZDMwLTllNjQtZjE3ZmVkNWM3YzliXkEyXkFqcGdeQXVyMTYxNzI4OTYx._V1_FMjpg_UX1000_.jpg',
      purchasedDate: '2022-10-10',
      name: 'Level 1',
    }];
    setboughtGame(boughtGames)
    setboughtLevels(boughtLevels)
  }

  const toggleCardList = () => {
    setShowCards(!showCards);
  };

  return (
    <Box>
      <Typography>Бібліотека ігор</Typography>
      <Box sx={{ display: 'flex', flexDirection: 'column', flexWrap: 'wrap', justifyContent: 'space-between' }}>
        { boughtGames.map((game) => (
          <SmallGameOrLevelCard showCards={showCards} key={game.gameId} onButtonClick={toggleCardList} cardType={'library'} gameId={game.gameId} levelId={game.name} name={game.name} purchasedDate={game.purchasedDate} mainImage={game.mainImage}/>
        ))}
        {showCards &&
        boughtLevels.map((level) => (
          <SmallGameOrLevelCard key={level.levelId} onButtonClick={toggleCardList} cardType={'libraryLevel'} gameId={level.gameId} levelId={level.levelId} name={level.name} purchasedDate={level.purchasedDate} mainImage={level.mainImage}/>
        ))}
      </Box>
    </Box>
  );
}

export default withAuthProtection(Library);