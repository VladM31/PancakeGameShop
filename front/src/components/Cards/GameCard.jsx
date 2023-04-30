import React from 'react';
import { Card, CardContent, CardMedia, Typography, ImageList, ImageListItem, Button } from '@mui/material';
import { styled } from '@mui/system';
import { useNavigate } from 'react-router';
import { useEffect } from 'react';
import {getGameWithLevels} from "../../api/games/api";

const StyledCard = styled(Card)(({ theme }) => ({
  cursor: 'pointer',
  backgroundColor: '#B55D9C',
  borderRadius: '15px',
  width: '1450px',
  height: '625px',
}));

function GameCard({ id, mainImage, name, images, releaseDate, price }) {

  const navigate = useNavigate();

  const imageList = (
      <ImageList>
        {images.slice(0, 4).map((image, index) => (
            <ImageListItem key={image}>
              <img style={{ borderRadius: 5, maxWidth: '300px', maxHeight: '200px', objectFit: 'cover' }} src={`${image}`} alt={`game img #${index}`} />
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

  const redirectToGamePage = async (event, id) =>  {
    navigate(`/game/${id}`);
    event.preventDefault();
  }

  return (
      <StyledCard onClick={(e) => redirectToGamePage(e, id) } style={{ marginTop: '20px' }}>
        <CardContent sx={{ display: 'flex', flexDirection: 'row' }}>
          <CardMedia component="img" sx={{ borderRadius: '20px', maxWidth: '900px', maxHeight: '550px', objectFit: 'cover' }} height="550" image={mainImage} alt={name} />
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
                    <Button onClick={(e) => {e.stopPropagation();}} variant="contained" color="inherit">
                      В корзину
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