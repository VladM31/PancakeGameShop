import React, { useState } from 'react';
import {
    Box,
    Button,
    Container,
    TextField,
    Typography,
    MenuItem,
    Grid,
} from '@mui/material';

const currencies = [
    { label: 'USD', value: 'USD' },
    { label: 'EUR', value: 'EUR' },
    { label: 'GBP', value: 'GBP' },
    // ... add more currencies here
];

const RegistrationForm = () => {
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

    const handleChange = (event) => {
        const {name, value} = event.target;
        setForm((prevForm) => ({...prevForm, [name]: value}));
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log('Form data:', form);
        // Handle form submission here (e.g., send data to the server)
    };

    return (
        <Container sx={{display: 'flex', alignItems: 'center', height: '100vh'}} component="main" maxWidth="sm">
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
                <Box component="form" onSubmit={handleSubmit} sx={{ mt: 1 }}>
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
                    </Grid>
                    <Button
                        type="submit"
                        variant="contained"
                        color={'inherit'}
                        sx={{ mt: 3, mb: 2, width: '40%' }}
                    >
                        Register
                    </Button>
                </Box>
            </Box>
        </Container>
    );
};

export default RegistrationForm;