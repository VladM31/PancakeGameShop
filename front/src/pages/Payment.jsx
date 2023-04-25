import React from 'react';
import {
    Box, Button,
    Card,
    CardContent,
    Container,
    Grid,
    List,
    ListItem,
    ListItemText,
    TextField,
    Typography
} from "@mui/material";
import {makeStyles, withStyles} from "@mui/styles";
import {useLocation} from "react-router";

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

function Payment() {

    const maxVisibleItems = 3;
    const location = useLocation();
    const {products, totalPrice} = location.state;

    const classes = useStyles();
    const CssTextField = withStyles({
        root: {
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
                                    <ListItem key={product.gameId || product.levelId} className={classes.listItem}>
                                        <ListItemText primary={product.name} />
                                    </ListItem>
                                ))}
                                {products.length > maxVisibleItems && (
                                    <ListItem className={classes.listItem}>
                                        <ListItemText primary="..." />
                                    </ListItem>
                                )}
                            </List>
                        </Box>
                    </Grid>
                    <Grid item xs={12}>
                        <Typography color={'white'} variant="body1">Сумма: {totalPrice}$</Typography>
                    </Grid>
                    <Grid item xs={12}>
                        <Card sx={{background: 'rgba(181, 93, 156, 0.85)', borderRadius: 5, padding: 2}}>
                            <CardContent>
                                <Grid container spacing={2}>
                                    <Grid item xs={12} sm={7}>
                                        <CssTextField
                                            fullWidth label="Номер карты" variant="outlined"/>
                                    </Grid>
                                    <Grid item xs={12} sm={5}>
                                        <CssTextField fullWidth label="Срок действия" variant="outlined"/>
                                    </Grid>
                                    <Grid item xs={12} sm={7}>
                                        <CssTextField fullWidth label="Имя на карте" variant="outlined"/>
                                    </Grid>
                                    <Grid item xs={12} sm={5}>
                                        <CssTextField fullWidth label="CVV2 код" variant="outlined"/>
                                    </Grid>
                                </Grid>
                            </CardContent>
                        </Card>
                    </Grid>
                    <Grid item xs={12}>
                        <CssTextField fullWidth label="Email" variant="outlined"/>
                    </Grid>
                    <Grid item xs={12}>
                        <CssTextField fullWidth label="Телефонный номер" variant="outlined"/>
                    </Grid>
                </Grid>
                <Box sx={{display: 'flex', justifyContent: 'center', alignItems: 'center', mt: '10px'}}>
                    <Button sx={{width: '200px'}} color={'inherit'} variant={'contained'}>Сплатити</Button>
                </Box>
            </Box>
        </Container>
    );
}

export default Payment;