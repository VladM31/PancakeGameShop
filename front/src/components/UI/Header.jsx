import * as React from 'react';
import { Link, Outlet } from 'react-router-dom';
import PropTypes from 'prop-types';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import CssBaseline from '@mui/material/CssBaseline';
import Divider from '@mui/material/Divider';
import Drawer from '@mui/material/Drawer';
import IconButton from '@mui/material/IconButton';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemText from '@mui/material/ListItemText';
import MenuIcon from '@mui/icons-material/Menu';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';

const drawerWidth = 240;

// const navItemsAuthUser = ['Бібліотека', 'Корзина', 'Увійти / Вийти'];

const navItemsNoAuthUser = ['Увійти / Вийти'];

function Header(props) {
  const { window } = props;
  const [mobileOpen, setMobileOpen] = React.useState(false);

  console.log(props.children)

  const handleDrawerToggle = () => {
    setMobileOpen((prevState) => !prevState);
  };

  const drawer = (
    <Box style={{ display: 'flex', flexDirection: 'column', height: '100vh', backgroundColor: '#141414'}} onClick={handleDrawerToggle} sx={{ textAlign: 'center' }}>
      <Typography variant="h6" style={{ color: '#B55D9C'}} sx={{ my: 2 }}>
        Pancake GameShop
      </Typography>
      <Divider />
      <List>
        {navItemsNoAuthUser.map((item) => (
          <ListItem style={{ color: '#B55D9C'}} key={item} disablePadding>
            <ListItemButton sx={{ textAlign: 'center' }}>
              <ListItemText primary={item} />
            </ListItemButton>
          </ListItem>
        ))}
      </List>
    </Box>
  );

  const container = window !== undefined ? () => window().document.body : undefined;

  return (
    <Box sx={{ display: 'flex' }}>
      <CssBaseline />
      <AppBar component="nav">
        <Toolbar style={{ backgroundColor: '#141414' }}>
          <IconButton
            color="inherit"
            aria-label="open drawer"
            edge="start"
            onClick={handleDrawerToggle}
            sx={{ mr: 2, display: { sm: 'none' } }}
          >
            <MenuIcon />
          </IconButton>
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
              <Button key={item} sx={{ color: '#B55D9C', fontWeight: '700', fontSize: '16px', lineHeight: '39px' }}>
                {item}
              </Button>
            ))}
          </Box>
        </Toolbar>
      </AppBar>
      <Box component="nav">
        <Drawer
          container={container}
          variant="temporary"
          open={mobileOpen}
          onClose={handleDrawerToggle}
          ModalProps={{
            keepMounted: true, // Better open performance on mobile.
          }}
          sx={{
            display: { xs: 'block', sm: 'none' },
            '& .MuiDrawer-paper': { boxSizing: 'border-box', width: drawerWidth },
          }}
        >
          {drawer}
        </Drawer>
      </Box>
      <Box component={props.name} sx={{ p: 10, width: '200vh' , display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
        <Outlet />
      </Box>
    </Box>
  );
}

Header.propTypes = {
  /**
   * Injected by the documentation to work in an iframe.
   * You won't need it on your project.
   */
  window: PropTypes.func,
};

export default Header;