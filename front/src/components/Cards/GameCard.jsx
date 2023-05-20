import React from 'react';
import {Card, CardContent, CardMedia, Typography, ImageList, ImageListItem, Button} from '@mui/material';
import {styled} from '@mui/system';
import {useNavigate} from 'react-router';
import {addToCart} from "../../reducers/cart/cartStore";
import {useDispatch} from "react-redux";
import {isPastDate} from "../../helpers/Date";
import Product from "../Etc/Product";

const StyledCard = styled(Card)(({theme}) => ({
    cursor: 'pointer',
    backgroundColor: '#B55D9C',
    borderRadius: '15px',
    width: '1450px',
    height: '625px',
}));

function GameCard({id, mainImage, name, images, releaseDate, price, isBought}) {

    const navigate = useNavigate();
    const dispatch = useDispatch();

    const imageList = (
        <ImageList>
            {images.slice(0, 4).map((image, index) => (
                <ImageListItem key={image}>
                    <img style={{borderRadius: 5, maxWidth: '300px', maxHeight: '200px', objectFit: 'cover'}}
                         src={`${image}`} alt={`game img #${index}`}/>
                </ImageListItem>
            ))}
        </ImageList>
    );

    const redirectToGamePage = async (event, id) => {
        navigate(`/game/${id}`);
        event.preventDefault();
    }

    const buyHandler = (e, item) => {
        e.stopPropagation();
        dispatch(addToCart(item))
    };

    return (
        <StyledCard onClick={(e) => redirectToGamePage(e, id)} style={{marginTop: '20px'}}>
            <CardContent sx={{display: 'flex', flexDirection: 'row'}}>
                <CardMedia component="img"
                           sx={{borderRadius: '20px', maxWidth: '900px', maxHeight: '550px', objectFit: 'cover'}}
                           height="550" image={mainImage} alt={name}/>
                <CardContent>
                    <Typography variant="h4" color="white" sx={{textAlign: 'center', mt: 1}}>{name}</Typography>
                    <CardContent>{imageList}</CardContent>
                    <CardContent sx={{p: 1}}>
                        <Typography variant="h4"
                                    color="white">{isPastDate(releaseDate) ? 'Гра вже вийшла' : 'Гра ще в розробці'}</Typography>
                        {isPastDate(releaseDate) && (
                            <CardContent sx={isBought ? {display: 'flex', flexDirection: 'column', gap: 2, mt: 8} : {display: 'flex', flexDirection: 'column', gap: 2, mt: 2}}>
                                {isBought ? (
                                    <Typography variant="h4" color="white">Гра вже придбана</Typography>
                                ) : (
                                    <>
                                        <Product priceInUSD={price}/>
                                        <Button onClick={(e) => buyHandler(e, {
                                            gameId: id,
                                            mainImage: mainImage,
                                            name: name,
                                            price: price
                                        })} variant="contained" color="inherit">
                                            В корзину
                                        </Button>
                                    </>
                                )}
                            </CardContent>
                        )}
                    </CardContent>
                </CardContent>
            </CardContent>
        </StyledCard>
    );
}

export default GameCard;