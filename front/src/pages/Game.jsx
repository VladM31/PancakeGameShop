// Game.jsx
import React, {useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';
import ImageCarousel from '../components/Carousels/ImagesCarousel';
import {Box, Button, CircularProgress, Menu, MenuItem, Typography} from '@mui/material';
import LevelCard from '../components/Cards/LevelCard';
import Filter from '../components/UI/Filter';
import {getLevelsByGameIdAndPage} from "../api/levels/api";
import {downloadGame, getBoughtContent, getGameById} from "../api/games/api";
import CustomPagination from "../components/UI/Pagination";
import {useDispatch} from "react-redux";
import {addToCart} from "../reducers/cart/cartStore";
import {isPastDate} from "../helpers/Date";
import Product from "../components/Etc/Product";

function Game() {
    const {id} = useParams();

    const [game, setGame] = useState({});
    const [levels, setLevels] = useState([]);
    const [isBought, setIsBought] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [boughtLevels, setBoughtLevels] = useState([]);
    const [platform, setPlatform] = useState('');
    const [anchorEl, setAnchorEl] = React.useState(null);

    const dispatch = useDispatch();

    const removeImages = (obj) => {
        const {levels, ...rest} = obj;
        return rest;
    };

    const getCurrentPlatform = () => {
        const platform = navigator.platform;
        switch (platform) {
            case 'Win32':
                return 'Windows';
            case 'Win64':
                return 'Windows';
            case 'MacIntel':
                return 'MacOS';
            case 'Linux x86_64':
                return 'Linux';
            default:
                return 'Windows';
        }
    }

    const downloadFile = async () => {
        await downloadGame(id, game.name, platform);
    }

    const buyHandler = (e, item) => {
        e.stopPropagation();
        dispatch(addToCart(item))
    };

    const handleChange = async (event, value) => {
        if (value === 1 || value < 1) return;
        setCurrentPage(value);
        const levels = await getLevelsByGameIdAndPage(id, currentPage - 1);
        setLevels(levels);
    };

    async function getGame(gameId) {
        const {content} = await getGameById(gameId)
        const {content: boughtContent} = await getBoughtContent();
        const game = removeImages(content[0])
        const levels = await getLevelsByGameIdAndPage(gameId, currentPage - 1);
        if (boughtContent && boughtContent.length > 0) {
            boughtContent.map(item => setBoughtLevels([...boughtLevels, ...item.levels]))
            const isBought = boughtContent.some(item => {
                return item.gamesId === +gameId
            });
            setIsBought(isBought);
        }
        setGame({...game});
        setLevels(levels);
    }

    const handleClickMenu = (event) => {
        setAnchorEl(event.currentTarget);
    }

    const handleCloseMenu = () => {
        setAnchorEl(null);
    };

    const platformHandler = (platformName) => {
        setPlatform(platformName);
        handleCloseMenu();
    };

    useEffect(() => {
        getGame(id);
    }, [id])

    useEffect(() => {
        if (!game?.platforms) {
            setPlatform('Windows')
            return;
        }
        if (game.platforms.includes(getCurrentPlatform())) {
            setPlatform(getCurrentPlatform());
        } else {
            setPlatform(game.platforms[0]);
        }
    }, [game])


    return (
        <>
            {
                game && game.id ? (
                        <Box>
                            <Box sx={{
                                display: 'flex',
                                width: 1000,
                                backgroundColor: '#B55D9C',
                                padding: '40px',
                                borderRadius: '15px',
                            }}>
                                <ImageCarousel images={game.images ? game.images : []}
                                               url={game.videoUrl ? game.videoUrl : 'https://www.youtube.com/watch?v=5QZcOugHQ6s&ab_channel=kriper2004'}/>
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
                                        <Typography color={'white'} variant='h5'>Дата
                                            виходу: {game.releaseDate ? game.releaseDate.slice(0, 10) : null}</Typography>
                                        <Product priceInUSD={game.price}/>
                                        <Typography color={'white'}
                                                    variant='h5'>Жанри: {game.genres ? game.genres.join(', ') : null}</Typography>
                                        <Typography color={'white'}
                                                    variant='h5'>Платформи: {game.platforms ? game.platforms.join(', ') : null}</Typography>
                                    </Box>
                                    <Box alignSelf={'end'}>
                                        {
                                            isPastDate(game.releaseDate) ? (
                                                !isBought ? (
                                                    <Button onClick={(e) => buyHandler(e, {
                                                        gameId: game.id,
                                                        mainImage: game.mainImage,
                                                        name: game.name,
                                                        price: game.price
                                                    })} variant="contained" color="inherit">
                                                        В корзину
                                                    </Button>) : (
                                                    <>
                                                        <Button aria-controls="simple-menu" sx={{fontSize: '14px', fontWeight: 'bold', marginRight: '20px', width: '100px', color: 'white'}} color={'inherit'} aria-haspopup="true"
                                                                onClick={handleClickMenu}>
                                                            {platform}
                                                        </Button>
                                                        <Menu
                                                            id="simple-menu"
                                                            anchorEl={anchorEl}
                                                            keepMounted
                                                            open={Boolean(anchorEl)}
                                                            onClose={handleCloseMenu}
                                                        >
                                                            {game.platforms ? game.platforms.map(item => <MenuItem
                                                                onClick={() => platformHandler(item)}>{item}</MenuItem>) : null}
                                                        </Menu>
                                                        <Button variant="contained" color="inherit"
                                                                onClick={() => downloadFile()}>Завантажити</Button>
                                                    </>

                                                )
                                            ) : (
                                                <Typography variant='h5' color={'white'}>Гра ще в розробці</Typography>
                                            )
                                        }

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
                                    {levels.content && levels.content.length > 0 ? levels.content.map((level) => (
                                        <LevelCard key={level.id} id={level.id} gameId={level.gameId}
                                                   mainImage={level.mainImage}
                                                   isBought={boughtLevels ? boughtLevels.some(item => item.levelsId === level.id) : false}
                                                   name={level.name} price={level.price}
                                                   isReady={isPastDate(game.releaseDate)}/>
                                    )) : (
                                        <Box sx={{
                                            display: 'flex',
                                            justifyContent: 'center',
                                            alignItems: 'center',
                                            width: '650px',
                                            height: '640px'
                                        }}>
                                            <Typography variant={'h5'} color={'white'}>Рівнів не знайдено...</Typography>
                                        </Box>
                                    )}
                                    <Box mt={4} display="flex" justifyContent="center">
                                        <CustomPagination
                                            count={levels.totalPages || 1}
                                            page={currentPage}
                                            onChange={handleChange}
                                            variant="outlined"
                                            shape="rounded"
                                            color={'secondary'}
                                        />
                                    </Box>
                                </Box>
                                <Box>
                                    <Filter gameId={id} currentPage={currentPage} onFilter={setLevels}/>
                                </Box>
                            </Box>
                        </Box>
                    ) :
                    (<Box sx={{
                        display: 'flex',
                        justifyContent: 'center',
                        alignItems: 'center',
                        width: '1400px',
                    }}>
                        <CircularProgress color="secondary"/>
                    </Box>)
            }
        </>
    )
        ;
}

export default Game;