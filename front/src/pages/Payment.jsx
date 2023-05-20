import React, {useEffect, useState} from 'react';
import {
    Alert,
    Box, Button,
    Card,
    CardContent,
    Container,
    Grid,
    List,
    ListItem,
    ListItemText, Snackbar,
    TextField,
    Typography
} from "@mui/material";
import {makeStyles, withStyles} from "@mui/styles";
import {useLocation} from "react-router";
import {getPromoCodeDiscount, purchase} from "../api/payment/api";
import {clearCart} from "../reducers/cart/cartStore";
import {useDispatch} from "react-redux";
import {useNavigate} from "react-router-dom";
import Product from "../components/Etc/Product";

const useStyles = makeStyles((theme) => ({
    root: {
        width: '100%',
        backgroundColor: "inherit",
    },
    listItem: {
        color: 'white',
        padding: 0,
        whiteSpace: 'nowrap',
        overflow: 'hidden',
        textOverflow: 'ellipsis',
    },
}));

const CssTextField = withStyles({
    root: {
        '& input' : {
          color: 'white'
        },
        '& label': {
            color: 'white',
        },
        '&:hover label': {
            color: '#00000044',
        },
        '& label.Mui-focused': {
            color: '#000000',
        },
        '& .MuiInput-underline:after': {
            borderBottomColor: '#00000044',
        },
        '& .MuiOutlinedInput-root': {
            '& fieldset': {
                borderColor: 'white',
            },
            '&:hover fieldset': {
                borderColor: '#00000044',
            },
            '&.Mui-focused fieldset': {
                borderColor: '#00000044',
            },
        },
    },
})(TextField);

function Payment() {

    const maxVisibleItems = 3;
    const location = useLocation();
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const {products, totalPrice} = location.state;
    const [promoCode, setPromoCode] = useState('');
    const [discount, setDiscount] = useState(0);
    const [errorMsg, setErrorMsg] = useState('');

    const [error, setError] = useState('');
    const [inputData, setInputData] = useState({
        cardNumber: '',
        cardDate: '',
        cardCVV: '',
        nameOnCard: '',
        email: '',
        phoneNumber: ''
    });

    const classes = useStyles();

    const handleChange = (event) => {
        const {name, value} = event.target;
        setInputData((prevState) => ({
            ...prevState,
            [name]: value
        }));
    };

    const purchaseHandler = async () => {
        const allFieldsEmpty = Object.values(inputData).every(value => value === '');

        if (allFieldsEmpty) {
            return;
        }

        const gameIds = products.reduce((accumulator, product) => {
            if(product.levelId === undefined) {
                if (!accumulator.includes(product.gameId)) {
                    accumulator.push(product.gameId);
                }
            }
            return accumulator;
        }, []);
        const levelIds = products.reduce((accumulator, product) => {
            if (product.levelId !== undefined && !accumulator.includes(product.levelId)) {
                accumulator.push(product.levelId);
            }
            return accumulator;
        }, []);

        const res = await purchase(gameIds, levelIds, inputData.email, inputData.phoneNumber, {
            cardNumber: inputData.cardNumber,
            expiryDate: inputData.cardDate,
            cvv2: inputData.cardCVV,
            cardName: inputData.nameOnCard,
            discount
        });

        if (res.success) {
            navigate('/');
            dispatch(clearCart());
        } else {
            setError(res.error);
        }
    }

    // Функция обработки изменений промокода
    const handlePromoCodeChange = (event) => {
        setPromoCode(event.target.value);
    };

    // Хук useEffect для обработки изменений промокода с задержкой
    useEffect(() => {
        if(promoCode.trim().length === 0) {
            setDiscount(0);
            return;
        }
        const timeoutId = setTimeout(async () => {
            const {success, data} = await getPromoCodeDiscount(promoCode);
            if (success) {
                if (data === 0) {
                    setDiscount(0);
                    setErrorMsg('Промокод не действителен или срок его действия истек.');
                } else {
                    setDiscount(data);
                    setErrorMsg('');
                }
            } else {
                setDiscount(0);
                setErrorMsg('Произошла ошибка при проверке промокода.');
            }
        }, 1000);
        return () => clearTimeout(timeoutId);
    }, [promoCode]);

    return (
        <Container maxWidth="sm">
            <Box sx={{marginTop: 8, background: 'rgba(181, 93, 156, 0.85)', borderRadius: 10, padding: 5}}>
                <Grid container spacing={2}>
                    <Grid item xs={12}>
                        <Typography color={'white'} align={'center'} variant="h5">PanCake GamesShop</Typography>
                    </Grid>
                    <Grid item xs={12}>
                        <Typography color={'white'} variant="body1">Название покупаемых продуктов:</Typography>
                        <Box className={classes.root}>
                            <List aria-label="ellipsis list">
                                {products.slice(0, maxVisibleItems).map((product) => (
                                    <ListItem key={product.levelId || product.gameId} className={classes.listItem}>
                                        <ListItemText primary={product.name}/>
                                    </ListItem>
                                ))}
                                {products.length > maxVisibleItems && (
                                    <ListItem className={classes.listItem}>
                                        <ListItemText primary="..."/>
                                    </ListItem>
                                )}
                            </List>
                        </Box>
                    </Grid>
                    <Grid item xs={12}>
                        <Product variant={'body1'} priceInUSD={totalPrice} discount={discount}/>
                    </Grid>
                    <Grid item xs={12}>
                        <Card sx={{background: 'rgba(181, 93, 156, 0.85)', borderRadius: 5, padding: 2}}>
                            <CardContent>
                                <Grid container spacing={2}>
                                    <Grid item xs={12} sm={7}>
                                        <CssTextField
                                            fullWidth
                                            name="cardNumber"
                                            label="Номер карты"
                                            variant="outlined"
                                            value={inputData.cardNumber}
                                            onChange={handleChange}
                                        />
                                    </Grid>
                                    <Grid item xs={12} sm={5}>
                                        <CssTextField
                                            fullWidth
                                            name="cardDate"
                                            label="Срок действия"
                                            variant="outlined"
                                            value={inputData.cardDate}
                                            onChange={handleChange}
                                        />
                                    </Grid>
                                    <Grid item xs={12} sm={7}>
                                        <CssTextField
                                            fullWidth
                                            name="nameOnCard"
                                            label="Имя на карте"
                                            variant="outlined"
                                            value={inputData.nameOnCard}
                                            onChange={handleChange}
                                        />
                                    </Grid>
                                    <Grid item xs={12} sm={5}>
                                        <CssTextField
                                            fullWidth
                                            name="cardCVV"
                                            label="CVV2 код"
                                            variant="outlined"
                                            value={inputData.cardCVV}
                                            onChange={handleChange}
                                        />
                                    </Grid>
                                </Grid>
                            </CardContent>
                        </Card>
                    </Grid>
                    <Grid item xs={12}>
                        <CssTextField
                            fullWidth
                            name="email"
                            label="Email"
                            variant="outlined"
                            value={inputData.email}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <CssTextField
                            fullWidth
                            name="phoneNumber"
                            label="Телефонный номер"
                            variant="outlined"
                            value={inputData.phoneNumber}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <CssTextField
                            fullWidth
                            name="promoCode"
                            label="Промокод"
                            variant="outlined"
                            value={promoCode}
                            onChange={handlePromoCodeChange}
                        />
                    </Grid>
                    {
                        error ?
                            <Typography sx={{color: 'red'}} variant={'body1'}>
                                {error ? error.split('\n').join(', ') : null}
                            </Typography>
                         : null
                    }
                </Grid>
                <Box sx={{display: 'flex', justifyContent: 'center', alignItems: 'center', mt: '10px'}}>
                    <Button sx={{width: '200px'}} color={'inherit'} variant={'contained'}
                            onClick={() => purchaseHandler()}>Сплатити</Button>
                </Box>
            </Box>
            <Snackbar open={!!errorMsg} autoHideDuration={6000} onClose={() => setErrorMsg('')}>
                <Alert onClose={() => setErrorMsg('')} severity="error">
                    {errorMsg}
                </Alert>
            </Snackbar>
        </Container>
    );
}

export default Payment;