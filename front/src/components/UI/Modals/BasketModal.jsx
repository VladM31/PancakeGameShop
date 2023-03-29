import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import { Button, Fade, IconButton } from '@mui/material';
import ShoppingBasketIcon from '@mui/icons-material/ShoppingBasket';
import SmallGameOrLevelCard from '../../Cards/SmallGameOrLevelCard';

const style = {
  position: 'fixed',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  maxWidth: 1000,
  maxHeight: 535,
  overflow: 'scroll',
  bgcolor: '#141414',
  border: '2px solid #000',
  borderRadius: '10px',
  boxShadow: 24,
  p: 4,
};

export default function BasicModal() {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const basketGames = [
    { 
      id: 1,
      mainImage: 'https://m.media-amazon.com/images/M/MV5BMzgyZWEzMDgtMzI0YS00ZDMwLTllNjQtZjE3ZmVkNWM3YzliXkEyXkFqcGdeQXVyMTYxNzI4OTYx._V1_FMjpg_UX1000_.jpg',
      name: 'Minecraft',
      releaseDate: '2023-03-26',
      price: 50, 
    },
    {
      id: 2,
      mainImage: 'https://cdn.akamai.steamstatic.com/steam/apps/374320/capsule_616x353.jpg?t=1644436409',
      name: 'Dark Souls 3',
      releaseDate: '2023-03-26',
      price: 50, 
    },
    {
      id: 3,
      mainImage: 'https://cdn1.epicgames.com/offer/602a0ef0aceb46cca62445439661d712/EGS_STALKER2HeartofChornobyl_GSCGameWorld_S1_2560x1440-7cc8db55646ee7b969c48defed6963f4',
      name: 'S.T.A.L.K.E.R. 2',
      releaseDate: '2024-03-26',
      price: 50, 
    },
    {
      id: 4,
      mainImage: 'https://cdn1.epicgames.com/offer/602a0ef0aceb46cca62445439661d712/EGS_STALKER2HeartofChornobyl_GSCGameWorld_S1_2560x1440-7cc8db55646ee7b969c48defed6963f4',
      name: 'S.T.A.L.K.E.R. 2',
      releaseDate: '2024-03-26',
      price: 50, 
    },
    {
      id: 5,
      mainImage: 'https://cdn1.epicgames.com/offer/602a0ef0aceb46cca62445439661d712/EGS_STALKER2HeartofChornobyl_GSCGameWorld_S1_2560x1440-7cc8db55646ee7b969c48defed6963f4',
      name: 'S.T.A.L.K.E.R. 2',
      releaseDate: '2024-03-26',
      price: 50, 
    }
  ]

  const basketPrice = basketGames.reduce((acc, game) => acc + game.price, 0)

  return (
    <>
      <IconButton sx={{color: 'white'}} style={{ position: 'fixed', right: '40px', top: '100px'}} onClick={handleOpen}><ShoppingBasketIcon fontSize='large' /></IconButton>
      <Modal
        open={open}
        onClose={handleClose}
		disableScrollLock
        aria-labelledby="modal-modal-title"
      >
        <Fade in={open}>
          <Box sx={style}>
            <Typography id="modal-modal-title" variant="h6" color={'white'} component="h2">
              Game Basket
            </Typography>
            <Box>
              {
                basketGames.map((game) => (
                  <SmallGameOrLevelCard key={game.id} id={game.id} mainImage={game.mainImage} name={game.name} price={game.price}  />
                ))
              }
              <Box sx={{
                display: 'flex',
                justifyContent: 'space-between',
                alignItems: 'center',
                backgroundColor: '#B55D9C',
                borderRadius: '15px',
                width: '950px',
                height: '100px',
                marginTop: '20px',
              }}>
                <Button variant='contained' sx={{ marginLeft: '20px', bgcolor: '#C0369A', color: 'white' }} >Купити</Button><Typography variant='h4' color={'white'} sx={{ marginRight: '20px' }}>Сумма замовлень: {basketPrice}$</Typography>
              </Box>
            </Box>
          </Box>
        </Fade>
      </Modal>
    </>
  );
}