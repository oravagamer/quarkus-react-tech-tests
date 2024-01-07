import useAxios from "axios-hooks";
import {backendUrl} from "../../data/settings.ts";
import {Link} from "react-router-dom";
import RequestLayout from "../../components/RequestLayout.tsx";
import {Button, Card, CardActions, CardContent, CardMedia, Grid, Typography} from "@mui/material";

const Galleries = () => {
    const [{data, loading, error}] = useAxios<Gallery[]>(`${backendUrl}/galleries`);

    return (
        <RequestLayout
            loading={loading}
            error={error}
            id="galleries">
            <Grid
                container
                spacing={2}
                justifyContent="space-around"
                alignItems="stretch"
                sx={{padding: 2}}>
                {
                    data?.map(gallery =>
                        <Grid
                            item
                            xs={12}
                            sm={6}
                            md={4}
                            className="gallery"
                            justifyContent="center"
                            display="flex"
                            key={gallery.id}>
                            <Card sx={{maxWidth: 300, height: "100%"}}>
                                <CardMedia component="img" image={`${backendUrl}/picture/${gallery?.thumbnail}`}
                                           alt={`${gallery.name} thumbnail`}/>
                                <CardContent>
                                    <Typography variant="h5" component="div">{gallery?.name}</Typography>
                                    <Typography variant="body2" component="div">{gallery?.description}</Typography>
                                </CardContent>
                                <CardActions>
                                    <Button size="small" color="primary" component={Link}
                                            to={gallery?.id.toString()}>More</Button>
                                </CardActions>
                            </Card>
                        </Grid>)
                }
            </Grid>
        </RequestLayout>
    );
}

export default Galleries;