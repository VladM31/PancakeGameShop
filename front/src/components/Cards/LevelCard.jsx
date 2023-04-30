import React from 'react';
import {Card, CardContent, CardMedia, Typography, Button, Box} from '@mui/material';
import {styled} from '@mui/system';
import {useNavigate} from 'react-router';
import {useEffect} from 'react';
import {addToCart} from "../../reducers/cart/cartStore";
import {useDispatch} from "react-redux";

const StyledCard = styled(Card)(({theme}) => ({
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
                      isReady,
                  }) {

    const navigate = useNavigate();
    const dispatch = useDispatch();

    const redirectToLevelPage = async (event, gameId = 1, levelId = 1) => {
        navigate(`/game/${gameId}/level/${levelId}`);
        event.stopPropagation();
    }

    const truncateName = (name, maxLength = 16) => {
        return name.length > maxLength ? name.slice(0, maxLength - 3) + "..." : name;
    }

    const buyHandler = (e, item) => {
        e.stopPropagation();
        dispatch(addToCart(item))
    };

    return (
        <StyledCard onClick={(e) => redirectToLevelPage(e, gameId, id)} sx={{marginBottom: '20px'}}>
            <CardContent sx={{display: 'flex', flexDirection: 'row'}}>
                <CardMedia component="img" sx={{borderRadius: '20px', width: '35%'}} height="170" image={mainImage}
                           alt={name}/>
                <CardContent
                    sx={{display: 'flex', width: '65%', justifyContent: 'space-between', alignItems: 'center'}}>
                    <CardContent>
                        <Typography variant="h5" color="white"
                                    sx={{textAlign: 'center'}}>{name ? truncateName(name) : null}</Typography>
                    </CardContent>
                    <CardContent>
                        {isReady ? (
                            <Box sx={{
                                display: 'flex',
                                flexDirection: 'column',
                                height: '105px',
                                justifyContent: 'space-between',
                            }}>
                                <Typography variant="h5" color="white">Ціна {price}$</Typography>
                                <Button onClick={(e) => buyHandler(e, {
                                    gameId: id,
                                    mainImage: mainImage,
                                    name: name,
                                    price: price
                                })} variant="contained" color="inherit">В
                                    корзину</Button>
                            </Box>
                        ) : (
                            <Typography sx={{width: '100px', mt: 2}} variant="h5" color="white">Гра ще в
                                розробці</Typography>
                        )}
                    </CardContent>
                </CardContent>
            </CardContent>
        </StyledCard>
    );
}

export default GameCard;