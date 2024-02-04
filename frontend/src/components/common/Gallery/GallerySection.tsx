import { FC } from "react";
import { Box, Button, Grid, Typography } from "@mui/material";
import { Link } from "react-router-dom";
import ArrowBackIosIcon from "@mui/icons-material/ArrowBackIos";

interface Props extends DefaultProps {
    galleryName?: string;
}

const GallerySection: FC<Props> = (props) => {
    return (
        <Box id={props.id} className={props.className}>
            <Typography component="h1" fontSize={50}>
                Gallery {props.galleryName}
            </Typography>
            <Button
                size="medium"
                color="primary"
                component={Link}
                to=".."
                startIcon={<ArrowBackIosIcon />}
            >
                Go Back
            </Button>
            <Grid
                container
                spacing={2}
                justifyContent="space-around"
                alignItems="stretch"
                columns={{ xl: 4, lg: 3, md: 2, sm: 1, xs: 1 }}
                sx={{ paddingTop: 2, paddingBottom: 2 }}
            >
                {props.children}
            </Grid>
        </Box>
    );
};

export default GallerySection;
