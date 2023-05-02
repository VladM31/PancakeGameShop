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
            <Typography>Бібліотека ігор</Typography>
            <Box sx={{display: 'flex', flexDirection: 'column', flexWrap: 'wrap', justifyContent: 'space-between'}}>
                {boughtGames.map((game) => (
                    <CardsBox key={game.id} game={game} />
                ))}
            </Box>
        </Box>
    )
        ;
}

export default withAuthProtection(Library);