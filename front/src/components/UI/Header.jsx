import * as React from 'react';
import { Link, Outlet } from 'react-router-dom';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import CssBaseline from '@mui/material/CssBaseline';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';


// const navItemsAuthUser = ['Бібліотека', 'Корзина', 'Увійти / Вийти'];

const navItemsNoAuthUser = ['Увійти / Вийти'];

function Header(props) {

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
            {navItemsNoAuthUser.map((item) => (
              <Button key={item} sx={{ color: '#fff', fontWeight: '700', fontSize: '16px', lineHeight: '39px' }}>
                {item}
              </Button>
            ))}
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