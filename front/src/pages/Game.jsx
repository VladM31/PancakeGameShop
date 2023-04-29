// Game.jsx
import {useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';
import ImageCarousel from '../components/Carousels/ImagesCarousel';
import {Box, Button, Typography} from '@mui/material';
import LevelCard from '../components/Cards/LevelCard';
import Filter from '../components/UI/Filter';
import {getGameWithLevels} from "../api/games/api";

function Game() {
    const {id} = useParams();

    const [game, setGame] = useState({});

    async function getGame(gameId) {
        const {content} = await getGameWithLevels(gameId)
        setGame(content[0]);
    }

    useEffect(() => {
        getGame(id);
    }, [id])

    return (
        <>
            <Box>
                <Box sx={{
                    display: 'flex',
                    width: 1000,
                    backgroundColor: '#B55D9C',
                    padding: '40px',
                    borderRadius: '15px',
                }}>
                    <ImageCarousel images={game.images ? game.images : []}/>
                    <Box sx={{
                        display: 'flex',
                        width: '50%',
                        justifyContent: 'space-between',
                        flexDirection: 'column',
                        marginLeft: '20px'
                    }}>
                        <Box sx={{display: 'flex', gap: '10px', flexDirection: 'column'}}>
                            <Typography color={'white'} variant='h4' align='center'>{game.name}</Typography>
                            <Typography color={'white'} variant='h5'>Рейтинг: {game.ageRating}+</Typography>
                            <Typography color={'white'} variant='h5'>Дата виходу: {game.releaseDate}</Typography>
                            <Typography color={'white'} variant='h5'>Ціна: {game.price}$</Typography>
                            <Typography color={'white'}
                                        variant='h5'>Жанри: {game.genres ? game.genres.join(', ') : null}</Typography>
                        </Box>
                        <Box alignSelf={'end'}>
                            <Button onClick={(e) => {
                                e.stopPropagation();
                            }} variant="contained" color="inherit">
                                В корзину
                            </Button>
                        </Box>
                    </Box>
                </Box>
                <Typography sx={{marginTop: '20px'}} align='center' variant='h3' color={'white'}>
                    Опис
                </Typography>
                <Box sx={{width: 1000, backgroundColor: '#B55D9C', padding: '40px', borderRadius: '15px',}}>
                    <Typography variant='h6' color={'white'}>
                        {game.description}
                    </Typography>
                </Box>
                <Typography sx={{marginTop: '20px'}} align='center' variant='h3' color={'white'}>
                    Рівні гри
                </Typography>
                <Box sx={{display: 'flex'}}>
                    <Box>
                        {game.levels ? game.levels.map((level) => (
                            <LevelCard key={level.id} id={level.id} gameId={level.gameId} mainImage={level.mainImage} name={level.name} price={level.price} />
                        )) : null}
                    </Box>
                    <Box>
                        <Filter/>
                    </Box>
                </Box>
            </Box>
        </>
    );
}

export default Game;