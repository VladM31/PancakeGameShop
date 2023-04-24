import React, {useState} from 'react';
import {
    Box,
    Button,
    Container,
    TextField,
    Typography,
    MenuItem,
    Grid,
} from '@mui/material';
import {useDispatch} from "react-redux";
import {registerUser} from "../../../reducers/user/userStore";
import {redirect} from "react-router-dom";

const currencies = [
    {label: 'USD', value: 'USD'},
    {label: 'EUR', value: 'EUR'},
    {label: 'GBP', value: 'GBP'},
    // ... add more currencies here
];

const RegistrationForm = ({isActiveFunc}) => {
    const [form, setForm] = useState({
        telephone: '',
        password: '',
        name: '',
        surname: '',
        nickname: '',
        email: '',
        birthday: '',
        currency: '',
    });
    const [error, setError] = useState('');

    const dispatch = useDispatch();

    const handleChange = (event) => {
        const {name, value} = event.target;
        setForm((prevForm) => ({...prevForm, [name]: value}));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        const { payload } = await dispatch(registerUser({
            phoneNumber: form.telephone,
            password: form.password,
            nickname: form.nickname,
            firstName: form.name,
            lastName: form.surname,
            email: form.email,
            birthDate: form.birthday,
            currency: form.currency,
        }));

        if (!payload.success) {
            setError(payload.message);
            redirect('/auth/login');
            return;
        }

        isActiveFunc(true);

        console.log('Form data:', form);
        // Handle form submission here (e.g., send data to the server)
    };

    return (
        <Container sx={{display: 'flex', alignItems: 'center', height: '82vh'}} component="main" maxWidth="sm">
            <Box
                sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                    mt: 8,
                    background: '#B55D9CD9',
                    borderRadius: 5,
                    padding: 5
                }}
            >
                <Typography component="h1" variant="h5">
                    Registration
                </Typography>
                <Box component="form" onSubmit={handleSubmit} sx={{mt: 1}}>
                    <Grid container spacing={2}>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                variant="outlined"
                                margin="normal"
                                required
                                fullWidth
                                id="telephone"
                                label="Telephone"
                                name="telephone"
                                type={'tel'}
                                autoComplete="tel"
                                autoFocus
                                value={form.telephone}
                                onChange={handleChange}
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                variant="outlined"
                                margin="normal"
                                required
                                fullWidth
                                name="password"
                                label="Password"
                                type="password"
                                id="password"
                                autoComplete="new-password"
                                value={form.password}
                                onChange={handleChange}
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                variant="outlined"
                                margin="normal"
                                fullWidth
                                id="name"
                                label="Name"
                                name="name"
                                autoComplete="given-name"
                                value={form.name}
                                onChange={handleChange}
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                variant="outlined"
                                margin="normal"
                                fullWidth
                                id="surname"
                                label="Surname"
                                name="surname"
                                autoComplete="family-name"
                                value={form.surname}
                                onChange={handleChange}
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                variant="outlined"
                                margin="normal"
                                fullWidth
                                id="nickname"
                                label="Nickname"
                                name="nickname"
                                autoComplete="nickname"
                                value={form.nickname}
                                onChange={handleChange}
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                variant="outlined"
                                margin="normal"
                                fullWidth
                                id="email"
                                label="Email Address"
                                name="email"
                                autoComplete="email"
                                value={form.email}
                                onChange={handleChange}
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                variant="outlined"
                                margin="normal"
                                fullWidth
                                id="birthday"
                                label="Birthday"
                                name="birthday"
                                type="date"
                                InputLabelProps={{
                                    shrink: true,
                                }}
                                value={form.birthday}
                                onChange={handleChange}
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                variant="outlined"
                                margin="normal"
                                fullWidth
                                id="currency"
                                label="Currency"
                                name="currency"
                                select
                                value={form.currency}
                                onChange={handleChange}
                            >
                                {currencies.map((option) => (
                                    <MenuItem key={option.value} value={option.value}>
                                        {option.label}
                                    </MenuItem>
                                ))}
                            </TextField>
                        </Grid>
                        {error && <Grid item xs={12} sm={12}><Typography variant={'body1'} color={'red'}>{error}</Typography></Grid>}
                    </Grid>
                    <Button
                        type="submit"
                        variant="contained"
                        color={'inherit'}
                        sx={{mt: 3, mb: 2, width: '40%'}}
                    >
                        Register
                    </Button>
                </Box>
            </Box>
        </Container>
    );
};

export default RegistrationForm;