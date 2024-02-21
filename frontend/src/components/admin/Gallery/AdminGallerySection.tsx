import { FC } from "react";
import { Box, Button, Typography } from "@mui/material";
import { Link } from "react-router-dom";
import ArrowBackIosIcon from "@mui/icons-material/ArrowBackIos";
import EditIcon from "@mui/icons-material/Edit";
import SaveIcon from "@mui/icons-material/Save";

interface Props extends DefaultProps {
    galleryName?: string;
    onSaveChanges: () => void;
    changed: boolean;
}

const AdminGallerySection: FC<Props> = (props) => {
    return (
        <Box id={props.id} className={props.className}>
            <Typography component="h1" fontSize={50}>
                Gallery {props.galleryName}
            </Typography>
            <Box display="flex" justifyContent="space-between">
                <Button
                    size="medium"
                    color="primary"
                    component={Link}
                    to=".."
                    startIcon={<ArrowBackIosIcon />}
                >
                    Go Back
                </Button>
                <Box>
                    <Button
                        size="medium"
                        variant="contained"
                        sx={{
                            marginRight: 1,
                        }}
                        color="success"
                        startIcon={<SaveIcon />}
                        onClick={props.onSaveChanges}
                        disabled={!props.changed}
                    >
                        Save changes
                    </Button>
                    <Button
                        size="medium"
                        variant="contained"
                        component={Link}
                        to="./edit"
                        color="warning"
                        startIcon={<EditIcon />}
                    >
                        Edit gallery
                    </Button>
                </Box>
            </Box>
            <>{props.children}</>
        </Box>
    );
};

export default AdminGallerySection;
