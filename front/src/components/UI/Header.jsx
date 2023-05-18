import * as React from 'react';
import {Link, Outlet, useNavigate} from 'react-router-dom';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import CssBaseline from '@mui/material/CssBaseline';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import {useAuth} from "../../reducers/user/useAuth";
import {useEffect} from "react";
import {initToken, logout} from "../../reducers/user/userStore";
import Cookies from 'js-cookie';
import {useDispatch} from "react-redux";
import logo from '../../assets/logo.svg';
import {initCart} from "../../reducers/cart/cartStore";
import {useLocation} from "react-router";

function Header(props) {

    const { token } = useAuth();
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const location = useLocation();

    useEffect(() => {
        const cookieToken = Cookies.get('token');
        if (cookieToken) {
            dispatch(initToken(JSON.parse(cookieToken)));
        }
        dispatch(initCart());
    }, [navigate, dispatch])

    const handleLogout = () => {
        dispatch(logout());
        if(location.pathname === '/') {
            window.location.reload();
        }
        navigate('/');
    };

    const navItemsAuthUser = [{id: 1, name: 'Бібліотека', path: '/library'}, {
        id: 2,
        name: 'Вийти',
        func: () => handleLogout()
    }];

    const navItemsNoAuthUser = [{id: 2, name: 'Увійти', path: '/auth/login'}];

    return (
        <Box sx={{display: 'flex'}}>
            <CssBaseline/>
            <AppBar component="nav">
                <Toolbar style={{backgroundColor: '#141414'}}>
                    <Box
                        variant="h6"
                        component="div"
                        style={{color: '#570861'}}
                        sx={{flexGrow: 1, display: {xs: 'none', sm: 'block'}}}
                    >
                        <Link to='/'><img width='65' src={logo} alt='logo'></img></Link>
                    </Box>
                    <Box sx={{ display: { xs: 'none', sm: 'block' } }}>
                        {token.value === ''
                            ? navItemsNoAuthUser.map((item) => (
                                <Link key={item.id} style={{ textDecoration: 'none' }} to={item.path}>
                                    <Button
                                        sx={{
                                            color: '#fff',
                                            fontWeight: '700',
                                            fontSize: '16px',
                                            lineHeight: '39px',
                                        }}
                                    >
                                        {item.name}
                                    </Button>
                                </Link>
                            ))
                            : navItemsAuthUser.map((item) =>
                                item.func ? (
                                    <Button
                                        key={item.id}
                                        onClick={item.func}
                                        sx={{
                                            color: '#fff',
                                            fontWeight: '700',
                                            fontSize: '16px',
                                            lineHeight: '39px',
                                        }}
                                    >
                                        {item.name}
                                    </Button>
                                ) : (
                                    <Link key={item.id} style={{ textDecoration: 'none' }} to={item.path}>
                                        <Button
                                            sx={{
                                                color: '#fff',
                                                fontWeight: '700',
                                                fontSize: '16px',
                                                lineHeight: '39px',
                                            }}
                                        >
                                            {item.name}
                                        </Button>
                                    </Link>
                                ),
                            )}
                    </Box>
                </Toolbar>
            </AppBar>
            <Box component={props.name}
                 sx={{p: 10, width: '200vh', display: 'flex', justifyContent: 'center', alignItems: 'center'}}>
                <Outlet/>
            </Box>
        </Box>
    );
}

export default Header;