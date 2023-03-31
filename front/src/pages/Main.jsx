import React, { useEffect } from 'react';
import { Box } from '@mui/material';
import DescriptionCard from '../components/Cards/DescriptionCard';
import brand from '../assets/shopCompany/brand.jpg';
import games from '../assets/shopCompany/games.png';
import people from '../assets/shopCompany/people.png';
import company from '../assets/shopCompany/company.jpg';
import GamesCarousel from '../components/Carousels/GamesCarousel';

function Main() {
  const [allGames, setGames] = React.useState([]);

  async function getAllGames() {
    const allGames = [
      { 
        id: 1,
        mainImage: 'https://m.media-amazon.com/images/M/MV5BMzgyZWEzMDgtMzI0YS00ZDMwLTllNjQtZjE3ZmVkNWM3YzliXkEyXkFqcGdeQXVyMTYxNzI4OTYx._V1_FMjpg_UX1000_.jpg',
        name: 'Minecraft',
        images: ['https://www.ionos.at/digitalguide/fileadmin/_processed_/1/c/csm_linux-minecraft-server-t_dc835841c1.jpg', 'https://assets2.rockpapershotgun.com/minecraft-house-ideas-hobbit-hole.jpg/BROK/resize/1920x1920%3E/format/jpg/quality/80/minecraft-house-ideas-hobbit-hole.jpg', 'https://www.popsci.com/uploads/2022/02/03/Minecraft-Tips-Parents.jpeg', 'https://ftw.usatoday.com/wp-content/uploads/sites/90/2022/05/Minecraft-attacking-a-SkeletoN.jpg'],
        releaseDate: '2023-03-26',
        price: 50, 
      },
      {
        id: 2,
        mainImage: 'https://cdn.akamai.steamstatic.com/steam/apps/374320/capsule_616x353.jpg?t=1644436409',
        name: 'Dark Souls 3',
        images: ['https://www.ionos.at/digitalguide/fileadmin/_processed_/1/c/csm_linux-minecraft-server-t_dc835841c1.jpg', 'https://assets2.rockpapershotgun.com/minecraft-house-ideas-hobbit-hole.jpg/BROK/resize/1920x1920%3E/format/jpg/quality/80/minecraft-house-ideas-hobbit-hole.jpg', 'https://www.popsci.com/uploads/2022/02/03/Minecraft-Tips-Parents.jpeg', 'https://ftw.usatoday.com/wp-content/uploads/sites/90/2022/05/Minecraft-attacking-a-SkeletoN.jpg'],
        releaseDate: '2023-03-26',
        price: 50, 
      },
      {
        id: 3,
        mainImage: 'https://cdn1.epicgames.com/offer/602a0ef0aceb46cca62445439661d712/EGS_STALKER2HeartofChornobyl_GSCGameWorld_S1_2560x1440-7cc8db55646ee7b969c48defed6963f4',
        name: 'S.T.A.L.K.E.R. 2',
        images: ['https://www.ionos.at/digitalguide/fileadmin/_processed_/1/c/csm_linux-minecraft-server-t_dc835841c1.jpg', 'https://assets2.rockpapershotgun.com/minecraft-house-ideas-hobbit-hole.jpg/BROK/resize/1920x1920%3E/format/jpg/quality/80/minecraft-house-ideas-hobbit-hole.jpg', 'https://www.popsci.com/uploads/2022/02/03/Minecraft-Tips-Parents.jpeg', 'https://ftw.usatoday.com/wp-content/uploads/sites/90/2022/05/Minecraft-attacking-a-SkeletoN.jpg'],
        releaseDate: '2024-03-26',
        price: 50, 
      }
    ]
    setGames(allGames)
  }

  useEffect(() => {
    getAllGames();
  }, [])

  return (
    <Box>
      <GamesCarousel games={allGames} />
      <DescriptionCard imgSide='left' imgUrl={company} title='Компанія' description='Наша компанія заснована в 2023. Ми продукти, щоб дивувати гравців по всьому світі.' />
      <DescriptionCard imgSide='right' imgUrl={brand} title='Наші бренди' description='Наша компанія створює світи для всіх. Ми прагнемо збагатити життя наших гравців, розробляючи високоякісні ігри, які резонують з усіма типами особистостей, об’єднують людей і дозволяють кожному навчатися та розвиватися, розважаючись.' />
      <DescriptionCard imgSide='left' imgUrl={games} title='Наші ігри' description='Наші ігри наповнені вихором приголомшливості, що викликає посмішку, і відшліфовані до блиску, завдяки якому світ повертається знову і знову. Ви знайдете наші ігри на мобільних пристроях, консолях, ПК та багатьох інших пристроях — можливо, ви навіть зможете грати в них на Місяці!' />
      <DescriptionCard imgSide='right' imgUrl={people} title='Наші люди' description='Співробітники нашої компанії розробляють ігри, щоб розповідати цікаві історії та демонструвати те, чого ви раніше не бачили. Співробітники працюють відповідально, атмосфера в компанії доброзичлива.' />
    </Box>
  );
}

export default Main;