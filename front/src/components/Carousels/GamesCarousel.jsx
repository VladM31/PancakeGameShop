import * as React from 'react';
import { Box, Button, MobileStepper, useTheme } from '@mui/material';
import KeyboardArrowLeft from '@mui/icons-material/KeyboardArrowLeft';
import KeyboardArrowRight from '@mui/icons-material/KeyboardArrowRight';
import GameCard from '../Cards/GameCard';
import SwipeableViews from 'react-swipeable-views';
import { autoPlay } from 'react-swipeable-views-utils';

const AutoPlaySwipeableViews = autoPlay(SwipeableViews, {interval: 1000});

function GamesCarousel(props) {
  const { games } = props;

  const theme = useTheme();

  const [activeStep, setActiveStep] = React.useState(0);
  const maxSteps = games.length; 

  const handleNext = () => {
    setActiveStep((prevActiveStep) => prevActiveStep + 1);
  };

  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  const handleStepChange = (step) => {
    setActiveStep(step);
  };

  return (
	<Box>
		<AutoPlaySwipeableViews
			axis={theme.direction === 'rtl' ? 'x-reverse' : 'x'}
			index={activeStep}
			onChangeIndex={handleStepChange}
			enableMouseEvents
			springConfig={{ duration: '0.5s', delay: '0.1ms', easeFunction: 'ease-in' }}
		>
			{
				games.map((game, index) => (
					<Box>
						{ activeStep === index ? (
						<GameCard  mainImage={game.mainImage} name={game.name} images={game.images} releaseDate={game.releaseDate} price={game.price} />
						) : null }
					</Box>
				))
			}
		</AutoPlaySwipeableViews>
		<MobileStepper
		sx={{ background: 'inherit' }}
		steps={maxSteps}
		position="static"
		activeStep={activeStep}
		nextButton={
		<Button
			size="small"
			onClick={handleNext}
			disabled={activeStep === maxSteps - 1}
			sx={{ color:'#B55D9C' }}
		>
			Вперед
			{theme.direction === 'rtl' ? (
			<KeyboardArrowLeft />
			) : (
			<KeyboardArrowRight />
			)}
		</Button>
		}
		backButton={
		<Button size="small" onClick={handleBack} disabled={activeStep === 0} sx={{ color:'#B55D9C' }}>
			{theme.direction === 'rtl' ? (
			<KeyboardArrowRight />
			) : (
			<KeyboardArrowLeft />
			)}
			Назад
		</Button>
		}
	/>
  </Box>
  );
}

export default GamesCarousel;