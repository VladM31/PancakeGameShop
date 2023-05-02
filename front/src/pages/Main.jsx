import {useEffect, useState} from 'react';
import {Box} from '@mui/material';
import DescriptionCard from '../components/Cards/DescriptionCard';
import brand from '../assets/shopCompany/brand.jpg';
import games from '../assets/shopCompany/games.png';
import people from '../assets/shopCompany/people.png';
import company from '../assets/shopCompany/company.jpg';
import GamesCarousel from '../components/Carousels/GamesCarousel';
import {getBoughtContent, getGames} from "../api/games/api";


function Main() {
    const [allGames, setGames] = useState([]);

    const [isBought, setIsBought] = useState(false);

    async function getAllGames() {
        const allGames = await getGames();
        await boughtCheck(allGames.content);
        setGames(allGames.content);
    }

    async function boughtCheck(allGames) {
        const { content } = await getBoughtContent();
        if (content && content.length > 0) {
            setIsBought(content.some(item1 => allGames.some(item2 => item1.gamesId === item2.id)));
        } else {
            return setIsBought(false);
        }
    }

    useEffect(() => {
        getAllGames();
    }, []);

    return (
        <Box>
            <GamesCarousel isBought={isBought} games={allGames}/>
            <DescriptionCard imgSide='left' imgUrl={company} title='Компанія'
                             description='Наша компанія заснована в 2023. Ми продукти, щоб дивувати гравців по всьому світі.'/>
            <DescriptionCard imgSide='right' imgUrl={brand} title='Наші бренди'
                             description='Наша компанія створює світи для всіх. Ми прагнемо збагатити життя наших гравців, розробляючи високоякісні ігри, які резонують з усіма типами особистостей, об’єднують людей і дозволяють кожному навчатися та розвиватися, розважаючись.'/>
            <DescriptionCard imgSide='left' imgUrl={games} title='Наші ігри'
                             description='Наші ігри наповнені вихором приголомшливості, що викликає посмішку, і відшліфовані до блиску, завдяки якому світ повертається знову і знову. Ви знайдете наші ігри на мобільних пристроях, консолях, ПК та багатьох інших пристроях — можливо, ви навіть зможете грати в них на Місяці!'/>
            <DescriptionCard imgSide='right' imgUrl={people} title='Наші люди'
                             description='Співробітники нашої компанії розробляють ігри, щоб розповідати цікаві історії та демонструвати те, чого ви раніше не бачили. Співробітники працюють відповідально, атмосфера в компанії доброзичлива.'/>
        </Box>
    );
}

export default Main;