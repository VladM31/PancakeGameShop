import {useState} from 'react';
import {Box} from "@mui/material";
import SmallGameCard from "../Cards/SmallGameCard";
import SmallLevelCard from "../Cards/SmallLevelCard";

const CardsBox = ({game}) => {
    const [isShown, setIsShown] = useState(false);

    return (
        <Box>
            <SmallGameCard {...game} isShown={isShown} showHandler={() => setIsShown((prevState) => !prevState)}/>
            {isShown ? game.levels.map((level) => (
                <SmallLevelCard gamesId={game.gamesId} key={level.levelsId} {...level}/>
            )) : null}
        </Box>
    );
};

export default CardsBox;