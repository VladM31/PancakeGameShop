import React, {useEffect} from 'react';
import {Box, Typography} from '@mui/material';
import withAuthProtection from "../hoc/withAuthProtection";
import {getBoughtContent} from "../api/games/api";
import CardsBox from "../components/Box/CardsBox";

function Library() {
    const [boughtGames, setBoughtGame] = React.useState([]);

    useEffect(() => {
        getBoughtGamesAndLevels();
    }, [])

    async function getBoughtGamesAndLevels() {
        const {content} = await getBoughtContent();
        setBoughtGame(content)
    }

    return (
        <Box>
            <Typography variant={'h3'} color={'white'}>Бібліотека ігор</Typography>
            <Box sx={{display: 'flex', flexDirection: 'column', flexWrap: 'wrap', justifyContent: 'space-between'}}>
                {
                    boughtGames.length > 0 ? boughtGames.map((game) => (
                        <CardsBox key={game.id} game={game}/>
                    )) : <Box
                        sx={{display: 'flex', alignItems: 'center', height: 500, fontSize: '24px', color: 'white'}}>Ви
                        ще не купили жодної гри!</Box>
                }
            </Box>
        </Box>
    )
        ;
}

export default withAuthProtection(Library);