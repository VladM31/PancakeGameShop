import React from 'react';
import {Card, CardContent, CardMedia, Typography, Button} from '@mui/material';
import {styled} from '@mui/system';
import ArrowDownwardIcon from '@mui/icons-material/ArrowDownward';
import ArrowUpwardIcon from '@mui/icons-material/ArrowUpward';
import {Link} from 'react-router-dom';
import {useNavigate} from 'react-router';
import Product from "../Etc/Product";

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
                                  handleClose,
                                  extraCardContent, // Add this prop for extra content
                              }) {
    const navigate = useNavigate();
    const StyledCard = styled(Card)(({theme}) => ({
        backgroundColor: '#B55D9C',
        borderRadius: '15px',
        width: '950px',
        height: '200px',
    }));

    const isLibraryCard = cardType === 'library';
    const isGamePageCard = cardType === 'gamepage';
    const isCartCard = cardType === 'cart';

    const navigationToGameOrLevel = (e, gameId, levelId) => {
        if (levelId) {
            navigate(`/game/${gameId}/level/${levelId}`);
        } else {
            navigate(`/game/${gameId}`);
        }
        handleClose();
        e.stopPropagation();
    };

    const removeHandler = (e) => {
        e.stopPropagation();
        onButtonClick();
    };

    return (
        <StyledCard
            onClick={isCartCard ? (e) => navigationToGameOrLevel(e, gameId, levelId) : null}
            sx={isCartCard ? {cursor: 'pointer', marginBottom: '20px'} : {marginBottom: '20px'}}
        >
            <CardContent sx={{display: 'flex', flexDirection: 'row'}}>
                <CardMedia component="img" sx={{borderRadius: '20px', width: '35%'}} height="170" image={mainImage}
                           alt={name}/>
                <CardContent
                    sx={{display: 'flex', width: '65%', justifyContent: 'space-between', alignItems: 'center'}}>
                    <CardContent>
                        <Typography variant="h4" color="white" sx={{textAlign: 'center', mt: 1}}>
                            {name}
                        </Typography>
                        {purchasedDate && (
                            <Typography variant="h6" color="white" sx={{mt: 1}}>
                                Придбано: {purchasedDate.slice(0, 10)}
                            </Typography>
                        )}
                        {extraCardContent && extraCardContent}
                    </CardContent>
                    <CardContent sx={{
                        display: 'flex',
                        flexDirection: 'column',
                        height: '105%',
                        justifyContent: 'space-between',
                        p: 2
                    }}>
                        {isLibraryCard ? (
                            <Link style={{color: '#fff', marginTop: '15px'}} to={`/game/${gameId}`}>
                                Переглянути
                            </Link>
                        ) : (
                            <Product priceInUSD={price}/>
                        )}
                        {isLibraryCard ? (
                            <Button onClick={onButtonClick} color="inherit" variant="contained">
                                {showCards ? <ArrowUpwardIcon/> : <ArrowDownwardIcon/>}
                            </Button>
                        ) : !isGamePageCard ? (
                            <Button variant="contained" color="error" onClick={(e) => removeHandler(e)}>
                                Видалити
                            </Button>
                        ) : (
                            <Button variant="contained" color="inherit">В корзину</Button>
                        )}
                    </CardContent>
                </CardContent>
            </CardContent>
        </StyledCard>
    );
}

export default SmallGameOrLevelCard;