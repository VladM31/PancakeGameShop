import {useEffect} from 'react';
import {useNavigate} from 'react-router-dom';
import {Box, Typography} from "@mui/material";

function AccessDenied() {
    const navigate = useNavigate();

    useEffect(() => {
        setTimeout(() => {
            navigate('/');
        }, 3000);
    }, [navigate]);

    return (
        <Box>
            <Typography variant={'h4'} color={'white'}>
                Доступ запрещен. Вы будете перенаправлены на главную страницу.
            </Typography>
        </Box>
    )
}

export default AccessDenied;