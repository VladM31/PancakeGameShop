import React from 'react';
import Pagination from '@mui/material/Pagination';
import {withStyles} from "@mui/styles";

const CssPagination = withStyles({
    root: {
        '& button': {
            color: 'white',
            borderColor: 'white',
        },
        '& span': {
            borderColor: 'white',
        }
    },
})(Pagination);

const CustomPagination = React.forwardRef((props, ref) => {
    // Здесь вы можете добавить или изменить стили для кастомной пагинации

    return (
        <CssPagination
            ref={ref}
            {...props}
        />
    );
});

export default CustomPagination;