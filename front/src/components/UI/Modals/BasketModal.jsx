import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import {Button, Fade, IconButton} from '@mui/material';
import ShoppingBasketIcon from '@mui/icons-material/ShoppingBasket';
import SmallGameOrLevelCard from '../../Cards/SmallGameOrLevelCard';
import {useNavigate} from "react-router";
import Cookies from "js-cookie";
import {useCart} from "../../../reducers/cart/useCart";
import {removeFromCart} from "../../../reducers/cart/cartStore";
import {useDispatch} from "react-redux";

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

export default function BasicModal({func}) {
    const [open, setOpen] = React.useState(false);

    const dispatch = useDispatch();

    const isAuthenticated = Cookies.get('token');
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);
    const navigate = useNavigate();

    const removeHandler = (id) => {
        dispatch(removeFromCart(id));
    }

    const handleNavigate = () => {
        if(!items.length) return;
        if (isAuthenticated) {
            func();
            navigate('/payment', {state: {products: items, totalPrice: basketPrice}});
            handleClose();
        } else {
            navigate('/auth/login');
            handleClose();
        }
    };

    const {items} = useCart();

    const basketPrice = items.reduce((acc, game) => acc + game.price, 0)

    return (
        <>
            <IconButton sx={{color: 'white'}} style={{position: 'fixed', right: '40px', top: '100px'}}
                        onClick={handleOpen}><ShoppingBasketIcon fontSize='large'/></IconButton>
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
                                items.map((gameOrLevel) => (
                                    <SmallGameOrLevelCard key={gameOrLevel.levelId ? gameOrLevel.levelId : gameOrLevel.gameId}
                                                          onButtonClick={() => removeHandler(gameOrLevel.levelId ? gameOrLevel.levelId : gameOrLevel.gameId)}
                                                          handleClose={handleClose}
                                                          cardType='cart'
                                                          levelId={gameOrLevel.levelId}
                                                          gameId={gameOrLevel.gameId} mainImage={gameOrLevel.mainImage}
                                                          name={gameOrLevel.name} price={gameOrLevel.price}/>
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
                                <Button onClick={handleNavigate} variant='contained' color='inherit'
                                        sx={{marginLeft: '20px'}}>Купити</Button>
                                <Typography variant='h4'
                                            color={'white'}
                                            sx={{marginRight: '20px'}}>Сумма
                                    замовлень: {basketPrice}$</Typography>
                            </Box>
                        </Box>
                    </Box>
                </Fade>
            </Modal>
        </>
    );
}