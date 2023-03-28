import { Card, CardContent, CardMedia, Typography } from "@mui/material";
import { styled } from '@mui/system';

const DescriptionCard = (props) => {

	const {imgSide, imgUrl, title, description} = props;

	const StyledCard = styled(Card)(({ theme }) => ({
		backgroundColor: '#B55D9C',
		borderRadius: '15px',
		width: '1450px',
		height: '600px',
		marginTop: '100px',
	  }));

  return (
	<>
		<StyledCard>
			<CardContent sx={{display: 'flex'}}>
				{
					imgSide === 'left' ? (
						<>
							<CardMedia component="img" sx={{ borderRadius: '20px' }} height="550" image={imgUrl} alt={`${imgUrl}`} />
							<CardContent> 
								<Typography align="center" color='white' variant="h2">{title}</Typography>
								<Typography sx={{fontWeight: 400, fontSize: '32px', lineHeight: '39px', marginTop: '10px'}} color='white' variant="h5">{description}</Typography>
							</CardContent>
						</>
					) : (
						<>
							<CardContent> 
								<Typography align="center" color='white' variant="h2">{title}</Typography>
								<Typography sx={{fontWeight: 400, fontSize: '32px', lineHeight: '39px', marginTop: '10px'}} color='white' variant="h5">{description}</Typography>
							</CardContent>
							<CardMedia component="img" sx={{ borderRadius: '20px' }} height="550" image={imgUrl} alt={`${imgUrl}`} />
						</>
					)
				}
			</CardContent>
		</StyledCard>
	</>
  )

}

export default DescriptionCard;