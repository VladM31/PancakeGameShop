import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import { Fade, IconButton } from '@mui/material';
import ShoppingBasketIcon from '@mui/icons-material/ShoppingBasket';

const style = {
  position: 'fixed',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 1000,
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
				{/* ... */}
			</Box>
			</Box>
		</Fade>
      </Modal>
    </>
  );
}