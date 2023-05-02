import {
    Box,
    Button,
    TextField,
    Typography,
    Link,
    Alert,
    Container,
} from '@mui/material';
import { styled } from '@mui/system';
import {Link as RouterLink, useNavigate} from 'react-router-dom';
import { useState } from 'react';
import {useDispatch} from "react-redux";
import {initToken, initUser, loginUser} from "../reducers/user/userStore";
import {withStyles} from "@mui/styles";

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

const CssTextField = withStyles({
    root: {
        '& label' : {
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

const btnSize = {
    width: '150px',
    height: '40px',
    color: 'black'
};

const FullScreenContainer = styled(Container)({
    height: '82vh',
    margin: 0,
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
});

const Login = () => {
    const [password, setPassword] = useState('');
    const [phoneNumber, setPhoneNumber] = useState(0);
    const [isOpenErrorMes, setOpenErrorMes] = useState(false);
    const [errorMes, setErrorMes] = useState('');

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        if(!password || !phoneNumber) {
            setOpenErrorMes(true);
            setErrorMes('Please, fill in all fields');
            return;
        }

        const { payload } = await dispatch(loginUser({ phoneNumber, password }));

        if(payload.success) {
            dispatch(initUser(payload.user));
            dispatch(initToken(payload.token));
            navigate('/')
        } else {
            setErrorMes(payload.message);
            setOpenErrorMes(true);
        }
    };

    return (
        <>
            <FullScreenContainer maxWidth={false} disableGutters>
                <LoginBox component="form" onSubmit={handleSubmit}>
                    <Typography color={'white'} variant="h4">Login</Typography>
                    <CssTextField
                        label="Phone number"
                        type="text"
                        name="phoneNumber"
                        required
                        onChange={(e) => setPhoneNumber(e.target.value)}
                        inputProps={{ pattern: '\\d{10,15}' }}
                    />
                    <CssTextField
                        label="Password"
                        type="password"
                        name="password"
                        onChange={(e) => setPassword(e.target.value)}
                        required
                        inputProps={{ minLength: 8, maxLength: 60 }}
                    />

                    <Link style={{ textDecoration: 'none' }} component={RouterLink} to="/auth/signUp" underline="hover">
                        <em style={{ color: 'white' }}>Sign up</em>
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
                            {errorMes.split('\n').join(', ')}
                        </Alert>
                    )}
                </LoginBox>
            </FullScreenContainer>
        </>
    );
};

export default Login;