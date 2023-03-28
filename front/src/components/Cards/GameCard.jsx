import React from 'react';
import { Card, CardContent, CardMedia, Typography, ImageList, ImageListItem, Button } from '@mui/material';
import { styled } from '@mui/system';

const StyledCard = styled(Card)(({ theme }) => ({
  backgroundColor: '#B55D9C',
  borderRadius: '15px',
  width: '1450px',
  height: '600px',
}));

function GameCard(props) {
  const { mainImage, name, images, releaseDate, price } = props;

  const imageList = (
    <ImageList cols={2}>
      {images.map((image, index) => (
        <ImageListItem key={image}>
            <img style={{ borderRadius: 5 }} src={`${image}`} alt={`game img #${index}`} />
        </ImageListItem>
      ))}
    </ImageList>
  );

  function isPastDate(date) {
    const inputDate = new Date(date);
    if (isNaN(inputDate)) {
      throw new Error("Invalid date format");
    }
  
    const currentDate = new Date();
    currentDate.setHours(0, 0, 0, 0);
  
    return inputDate <= currentDate;
  }

  return (
    <StyledCard style={{ marginTop: '20px' }}>
      <CardContent sx={{ display: 'flex', flexDirection: 'row' }}>
        <CardMedia component="img" sx={{ borderRadius: '20px' }} height="550" image={mainImage} alt={name} />
        <CardContent>
          <Typography variant="h4" color="white" sx={{ textAlign: 'center', mt: 1 }}>{name}</Typography>
          <CardContent>{imageList}</CardContent>
          <CardContent sx={{ p: 1 }}>
            <Typography variant="h4" color="white">{isPastDate(releaseDate) ? 'Гра вже вийшла' : 'Гра ще в розробці'}</Typography>
            {isPastDate(releaseDate) && (
              <CardContent sx={{ display: 'flex', flexDirection: 'column', gap: 2, mt: 2 }}>
                <Typography variant="h5" color="white">
                  Ціна {price}$
                </Typography>
                <Button variant="contained" color="success">
                  Додати у корзину
                </Button>
              </CardContent>
            )}
          </CardContent>
        </CardContent>
      </CardContent>
    </StyledCard>
  );
}

export default GameCard;