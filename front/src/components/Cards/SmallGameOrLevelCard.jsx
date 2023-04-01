import React from 'react';
import { Card, CardContent, CardMedia, Typography, Button } from '@mui/material';
import { styled } from '@mui/system';
import ArrowDownwardIcon from '@mui/icons-material/ArrowDownward';
import { Link } from 'react-router-dom';

function SmallGameOrLevelCard({
  id,
  mainImage,
  name,
  purchasedDate,
  price,
  cardType, // Pass 'cart', 'library', 'gamepage' or any other type as a prop
}) {
  const StyledCard = styled(Card)(({ theme }) => (cardType === 'gamepage' ? {
    backgroundColor: '#B55D9C',
    borderRadius: '15px',
    width: '650px',
    height: '200px',
  }: {
    backgroundColor: '#B55D9C',
    borderRadius: '15px',
    width: '950px',
    height: '200px',
  }));

  const isLibraryCard = cardType === 'library';
  const isGamePageCard = cardType === 'gamepage';

  return (
    <StyledCard sx={{ marginBottom: '20px' }}>
      <CardContent sx={{ display: 'flex', flexDirection: 'row' }}>
        <CardMedia component="img" sx={{ borderRadius: '20px', width: '35%' }} height="170" image={mainImage} alt={name} />
        <CardContent sx={{ display: 'flex', width: '65%', justifyContent: 'space-between', alignItems: 'center' }}>
          <CardContent>
            <Typography variant="h4" color="white" sx={{ textAlign: 'center', mt: 1 }}>{name}</Typography>
            {purchasedDate && <Typography variant="h4" color="white" sx={{ textAlign: 'center', mt: 1 }}>{purchasedDate}</Typography>}
          </CardContent>
          <CardContent sx={{ display: 'flex', flexDirection: 'column', height: '105%', justifyContent: 'space-between', p: 2 }}>
            {
              isLibraryCard ? (
                <Link style={{ color: '#fff' }} to={`/game/${id}`}>
                  Переглянути
                </Link>
              ) : (
                <Typography variant="h5" color="white">Ціна {price}$</Typography>
              )
            }
            {
              isLibraryCard ? (
                <Button variant="contained">
                  <ArrowDownwardIcon />
                </Button>
              ) : (
                !isGamePageCard ? <Button variant="contained" color="error">Видалити</Button> : <Button variant="contained" color="inherit">В корзину</Button>
              )
            }
          </CardContent>
        </CardContent>
      </CardContent>
    </StyledCard>
  );
}

export default SmallGameOrLevelCard;