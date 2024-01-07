import { Box, Container, Grid, Link, Typography } from "@mui/material";
import "./Footer.scss";

const Footer = () => {
    return (
        <Box component="footer">
            <Container maxWidth={false}>
                <Grid container spacing={2} justify-content="space-between">
                    <Grid item xs={12} sm={6} md={3}>
                        <Typography variant="h2" gutterBottom>
                            Oravix
                        </Typography>
                        Logo
                    </Grid>
                    <Grid item xs={6} sm={3} md={2}>
                        <Typography
                            variant="subtitle1"
                            color="text.primary"
                            gutterBottom
                        >
                            PRODUCT
                        </Typography>
                        <Link href="#" color="inherit" display="block">
                            Features
                        </Link>
                        <Link href="#" color="inherit" display="block">
                            Integrations
                        </Link>
                        <Link href="#" color="inherit" display="block">
                            Pricing
                        </Link>
                        <Link href="#" color="inherit" display="block">
                            FAQ
                        </Link>
                    </Grid>
                    <Typography
                        variant="body2"
                        color="text.secondary"
                        align="center"
                        sx={{ pt: 4 }}
                    >
                        © 2024 Company Co. All rights reserved.
                    </Typography>
                </Grid>
            </Container>
        </Box>
    );
};

export default Footer;
