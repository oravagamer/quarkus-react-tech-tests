import { FC } from "react";
import { Grid } from "@mui/material";

interface Props extends DefaultProps {}

const GalleriesSection: FC<Props> = (props) => {
    return (
        <Grid
            container
            id={props.id}
            className={props.className}
            spacing={2}
            justifyContent="space-around"
            alignItems="stretch"
            columns={{ xl: 4, lg: 3, md: 2, sm: 1, xs: 1 }}
            sx={{ paddingTop: 2, paddingBottom: 2 }}
        >
            {props.children}
        </Grid>
    );
};

export default GalleriesSection;
