import React, {useState} from 'react';
import {Box, Typography} from "@mui/material";
import SmallGameCard from "../Cards/SmallGameCard";
import SmallLevelCard from "../Cards/SmallLevelCard";

const CardsBox = ({game}) => {
    const [isShown, setIsShown] = useState(false);

    return (
        <Box>
            <SmallGameCard {...game} isShown={isShown} showHandler={() => setIsShown((prevState) => !prevState)}/>
            {isShown ? game.levels.map((level) => (
                <SmallLevelCard gamesId={game.gamesId} key={level.levelsId} {...level}/>
            )) : (
                <Box sx={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '200px'}}>
                    <Typography variant={'h2'} color={'white'}>Немає куплених рівнів</Typography>
                </Box>
            )}
        </Box>
    );
};

export default CardsBox;