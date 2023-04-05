import * as React from 'react';
import { Link, Outlet } from 'react-router-dom';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import CssBaseline from '@mui/material/CssBaseline';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import {initUser} from '../../reducers/user/userStore';


function Header(props) {
  const { user, token } = useSelector(state => state.userStore);
  const dispatch = useDispatch();

  useEffect(() => {
      initUser()
  }, [user, token, dispatch]);

  const navItemsAuthUser = [{ id: 1, name: 'Бібліотека', path: '/library' }, { id: 2, name: 'Вийти', path: '/auth/login' }];

  const navItemsNoAuthUser = [{ id: 2, name: 'Увійти', path: '/auth/login' }];

  return (
    <Box sx={{ display: 'flex' }}>
      <CssBaseline />
      <AppBar component="nav">
        <Toolbar style={{ backgroundColor: '#141414' }}>
          <Box
            variant="h6"
            component="div"
			      style={{ color: '#570861' }}
            sx={{ flexGrow: 1, display: { xs: 'none', sm: 'block' } }}
          >
            <Link to='/'><img width='175' src={require('../../assets/logo.png')} alt='logo'></img></Link>
          </Box>
            <Box sx={{ display: { xs: 'none', sm: 'block' } }}>
            {
              token.value === '' ? navItemsNoAuthUser.map((item) => (
                  <Link key={item.id} style={{textDecoration: 'none'}} to={item.path}>
                    <Button sx={{ color: '#fff', fontWeight: '700', fontSize: '16px', lineHeight: '39px',}}>
                      {item.name}
                    </Button>
                  </Link>
              )) : navItemsAuthUser.map((item) => (
                  <Link key={item.id} style={{textDecoration: 'none'}} to={item.path}>
                    <Button sx={{ color: '#fff', fontWeight: '700', fontSize: '16px', lineHeight: '39px',}}>
                      {item.name}
                    </Button>
                  </Link>
              ))
            }
          </Box>
        </Toolbar>
      </AppBar>
      <Box component={props.name} sx={{ p: 10, width: '200vh' , display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
        <Outlet />
      </Box>
    </Box>
  );
}

export default Header;