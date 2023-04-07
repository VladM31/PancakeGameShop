import {
    Box,
    Button,
    TextField,
    Typography,
    Link,
    Alert,
    Container,
    CssBaseline,
} from '@mui/material';
import { styled } from '@mui/system';
import { Link as RouterLink } from 'react-router-dom';
import { useState } from 'react';

const options = {
    shouldForwardProp: (prop) => prop !== 'fontColor',
};

const LoginBox = styled(Box)({
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    gap: 10,
    padding: '40px',
    borderRadius: '10px',
    color: 'white',
    background: '#B55D9CD9',
});

const StyledTextField = styled(
    TextField,
    options,
)(({ fontColor }) => ({
    input: {
        color: fontColor,
    },
}));

const btnSize = {
    width: '150px',
    height: '40px',
    color: 'black'
};

const FullScreenContainer = styled(Container)({
    height: '100vh',
    margin: 0,
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
});

const Login = () => {
    const [isOpenErrorMes, setOpenErrorMes] = useState(false);
    const [errorMes, setErrorMes] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
    };

    return (
        <>
            <FullScreenContainer maxWidth={false} disableGutters>
                <LoginBox component="form" onSubmit={handleSubmit}>
                    <Typography color={'black'} variant="h4">Login</Typography>
                    <StyledTextField
                        label="Phone number"
                        type="text"
                        name="phoneNumber"
                        required
                        inputProps={{ pattern: '\\d{10,15}' }}
                    />
                    <StyledTextField
                        label="Password"
                        type="password"
                        name="password"
                        required
                        inputProps={{ minLength: 8, maxLength: 60 }}
                    />

                    <Link style={{ textDecoration: 'none' }} component={RouterLink} to="/auth/signUp" underline="hover">
                        <em style={{ color: 'black' }}>Sign up</em>
                    </Link>

                    <Box>
                        <Button
                            variant="contained"
                            type="submit"
                            color={'inherit'}
                            sx={{ ...btnSize }}
                        >
                            Submit
                        </Button>
                    </Box>
                    {isOpenErrorMes && (
                        <Alert severity="error" onClose={() => setOpenErrorMes(false)}>
                            {errorMes}
                        </Alert>
                    )}
                </LoginBox>
            </FullScreenContainer>
        </>
    );
};

export default Login;