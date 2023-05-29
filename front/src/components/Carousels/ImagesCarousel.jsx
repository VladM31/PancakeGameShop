import React, { useState } from 'react';
import { Box, IconButton, CardMedia } from '@mui/material';
import { ArrowBackIos, ArrowForwardIos, YouTube } from '@mui/icons-material';
import {useDispatch} from "react-redux";
import {openVideoModal, setVideoModalUrl} from "../../reducers/modal/modalStore";

const ImageCarousel = ({ images, height = 300, url }) => {
    const [mainImageIndex, setMainImageIndex] = useState(0);
    const dispatch = useDispatch();

    const handleOpenModal = () => {
        dispatch(setVideoModalUrl(url))
        dispatch(openVideoModal());
    };

    const handleImageClick = (index) => {
        setMainImageIndex(index);
    };

    const handlePrevClick = () => {
        setMainImageIndex((prevIndex) => (prevIndex === 0 ? images.length - 1 : prevIndex - 1));
    };

    const handleNextClick = () => {
        setMainImageIndex((prevIndex) => (prevIndex === images.length - 1 ? 0 : prevIndex + 1));
    };

    return (
        <Box>
            <CardMedia
                component="img"
                height={height}
                image={images[mainImageIndex]}
                alt="Main Image"
            />
            {/* Добавьте здесь YouTube иконку */}
            <Box display="flex" justifyContent="center" alignItems="flex-start" mt={2}>
                <IconButton onClick={handlePrevClick}>
                    <ArrowBackIos />
                </IconButton>
                {images.map((img, index) => (
                    <Box display="flex" justifyContent="center" alignItems="center" flexDirection="column" key={index} mx={1}>
                        <CardMedia
                            component="img"
                            height="50"
                            image={img}
                            alt={`Thumbnail ${index}`}
                            onClick={() => handleImageClick(index)}
                            style={{ cursor: 'pointer', opacity: mainImageIndex === index ? 1 : 0.6 }}
                        />
                        {index === 0 && (
                            <IconButton onClick={handleOpenModal}>
                                <YouTube />
                            </IconButton>
                        )}
                    </Box>
                ))}
                <IconButton onClick={handleNextClick}>
                    <ArrowForwardIos />
                </IconButton>
            </Box>
        </Box>
    );
};

export default ImageCarousel;