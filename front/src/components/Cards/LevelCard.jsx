import React from 'react';
import { Card, CardContent, CardMedia, Typography, Button } from '@mui/material';
import { styled } from '@mui/system';
import { useNavigate } from 'react-router';
import { useEffect } from 'react';

const StyledCard = styled(Card)(({ theme }) => ({
  cursor: 'pointer',
  backgroundColor: '#B55D9C',
  borderRadius: '15px',
	width: '650px',
  height: '200px',
}));

function GameCard({
	id,
	mainImage,
	name,
	purchasedDate,
	price,
    gameId,
  }) {

  const navigate = useNavigate();

  useEffect(() => {
    // console.log(id)
  }, [id])

  const redirectToLevelPage = async (event, gameId = 1, levelId = 1) =>  { 
    navigate(`/game/${gameId}/level/${levelId}`);
    event.stopPropagation();
  }

  const truncateName = (name, maxLength = 16) => {
    return name.length > maxLength ? name.slice(0, maxLength - 3) + "..." : name;
  }

  return (
    <StyledCard onClick={(e) => redirectToLevelPage(e, gameId, id)} sx={{ marginBottom: '20px' }}>
      <CardContent sx={{ display: 'flex', flexDirection: 'row' }}>
        <CardMedia component="img" sx={{ borderRadius: '20px', width: '35%' }} height="170" image={mainImage} alt={name} />
        <CardContent sx={{ display: 'flex', width: '65%', justifyContent: 'space-between', alignItems: 'center' }}>
          <CardContent>
            <Typography variant="h5" color="white" sx={{ textAlign: 'center'}}>{name ? truncateName(name) : null}</Typography>
          </CardContent>
          <CardContent sx={{ display: 'flex', flexDirection: 'column', height: '105%', justifyContent: 'space-between', p: 2 }}>
            <Typography variant="h5" color="white">Ціна {price}$</Typography>
            <Button onClick={(e) => e.stopPropagation()} variant="contained" color="inherit">В корзину</Button>
          </CardContent>
        </CardContent>
      </CardContent>
    </StyledCard>
  );
}

export default GameCard;