import React from 'react';
import { Navigate } from 'react-router-dom';
import Cookies from 'js-cookie';

const withAuthProtection = (WrappedComponent) => {
    return (props) => {
        const isAuthenticated = Cookies.get('token');

        if (isAuthenticated) {
            return <WrappedComponent {...props} />;
        } else {
            return <Navigate to="/" replace />;
        }
    };
};

export default withAuthProtection;