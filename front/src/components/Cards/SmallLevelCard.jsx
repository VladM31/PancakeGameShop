import React from 'react';
import {Card, CardContent, CardMedia, Typography} from '@mui/material';
import {styled} from '@mui/system';
import {Link} from 'react-router-dom';

function SmallLevelCard({
                            gamesId,
                            levelsId,
                            mainImage,
                            name,
                            buyDate,
                        }) {

    const StyledCard = styled(Card)(() =>
        ({
            backgroundColor: '#B55D9C',
            borderRadius: '15px',
            width: '950px',
            height: '200px',
            scale: '0.8',
        }));

    return (
        <StyledCard
            sx={{marginBottom: '20px'}}>
            <CardContent sx={{display: 'flex', flexDirection: 'row'}}>
                <CardMedia component="img" sx={{borderRadius: '20px', width: '35%'}} height="170" image={mainImage}
                           alt={name}/>
                <CardContent
                    sx={{display: 'flex', width: '65%', justifyContent: 'space-between', alignItems: 'center'}}>
                    <CardContent sx={{
                        display: 'flex',
                        height: '150px',
                        flexDirection: 'column',
                        justifyContent: 'space-between'
                    }}>
                        <Typography variant={'h5'} color="white"
                                    sx={{mt: 1}}>{name}</Typography>
                        {buyDate &&
                            <Typography variant="h6" color="white"
                                        sx={{mt: 1}}>Придбано: {buyDate.slice(0, 10)}</Typography>}
                    </CardContent>
                    <CardContent sx={{
                        display: 'flex',
                        flexDirection: 'column',
                        height: '105%',
                        justifyContent: 'space-between',
                        p: 2
                    }}>
                        {
                            <Link style={{color: '#fff', marginTop: '15px'}}
                                  to={`/game/${gamesId}/level/${levelsId}`}>
                                Переглянути
                            </Link>
                        }
                    </CardContent>
                </CardContent>
            </CardContent>
        </StyledCard>
    );
}

export default SmallLevelCard;