import React from 'react';
import { Card, CardContent, CardMedia, Typography, Button } from '@mui/material';
import { styled } from '@mui/system';
import ArrowDownwardIcon from '@mui/icons-material/ArrowDownward';
import ArrowUpwardIcon from '@mui/icons-material/ArrowUpward';
import {Link} from 'react-router-dom';
import {useNavigate} from "react-router";

function SmallGameOrLevelCard({
  gameId,
  levelId,
  mainImage,
  name,
  purchasedDate,
  price,
  cardType, // Pass 'cart', 'library', 'gamepage' or any other type as a prop
  onButtonClick,
  showCards,
}) {
  const navigate = useNavigate()
  const StyledCard = styled(Card)(({ theme }) => isLibraryLevelCard ? 
    ({
      backgroundColor: '#B55D9C',
      borderRadius: '15px',
      width: '950px',
      height: '200px',
      scale: '0.8',
    }) :(
    {
      backgroundColor: '#B55D9C',
      borderRadius: '15px',
      width: '950px',
      height: '200px',
    }));

  const isLibraryCard = cardType === 'library';
  const isLibraryLevelCard = cardType === 'libraryLevel';
  const isGamePageCard = cardType === 'gamepage';
  const isCartCard = cardType === 'cart';

  const navigationToGameOrLevel = (e, gameId, levelId) => {
    if(levelId) {
        navigate(`/game/${gameId}/level/${levelId}`)
    } else {
        navigate(`/game/${gameId}`)
    }
    onButtonClick();
    e.stopPropagation();
  }

  return (
    <StyledCard onClick={isCartCard ? e => navigationToGameOrLevel(e, gameId, levelId) : null} sx={isCartCard ? { cursor: 'pointer', marginBottom: '20px' } : { marginBottom: '20px' }}>
      <CardContent sx={{ display: 'flex', flexDirection: 'row' }}>
        <CardMedia component="img" sx={{ borderRadius: '20px', width: '35%' }} height="170" image={mainImage} alt={name} />
        <CardContent sx={{ display: 'flex', width: '65%', justifyContent: 'space-between', alignItems: 'center' }}>
          <CardContent sx={isLibraryCard || isLibraryLevelCard ? { display: 'flex', height: '150px', flexDirection: 'column', justifyContent: 'space-between'} : null}>
            <Typography variant={isLibraryCard || isLibraryLevelCard ? 'h5' : 'h4'} color="white" sx={isLibraryLevelCard ? { mt: 1 } : { textAlign: 'center', mt: 1 }}>{name}</Typography>
            {purchasedDate && <Typography variant="h6" color="white" sx={{ mt: 1 }}>Придбано: {purchasedDate}</Typography>}
          </CardContent>
          <CardContent sx={{ display: 'flex', flexDirection: 'column', height: '105%', justifyContent: 'space-between', p: 2 }}>
            {
              isLibraryCard || isLibraryLevelCard ? (
                <Link style={{ color: '#fff', marginTop: '15px' }} to={isLibraryLevelCard ? `/game/${gameId}/level/${levelId}` :`/game/${gameId}`}>
                    Переглянути
                </Link>
              ) : (
                <Typography variant="h5" color="white">Ціна {price}$</Typography>
              )
            }
            {
              isLibraryCard ? (
                <Button onClick={onButtonClick} color='inherit' variant="contained">
                  {showCards ? (<ArrowUpwardIcon />) : (<ArrowDownwardIcon />)}
                </Button>
              ) : (
                isLibraryLevelCard ? null : !isGamePageCard ? <Button variant="contained" color="error">Видалити</Button> : <Button variant="contained" color="inherit">В корзину</Button>
              )
            }
          </CardContent>
        </CardContent>
      </CardContent>
    </StyledCard>
  );
}

export default SmallGameOrLevelCard;