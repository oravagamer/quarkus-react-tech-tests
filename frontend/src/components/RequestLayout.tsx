import { FC, ReactNode } from "react";
import { AxiosError } from "axios";
import { Box, CircularProgress, Container } from "@mui/material";

interface Props {
    children: ReactNode;
    loading: boolean;
    error: AxiosError<any, any> | null;
    id?: string;
    className?: string;
}

const RequestLayout: FC<Props> = (props) => {
    if (props.loading)
        return (
            <Box component="section" id={props.id} className={props.className}>
                <Container maxWidth={false}>
                    <CircularProgress sx={{ scale: "-1 1" }} />
                </Container>
            </Box>
        );

    if (props.error) {
        return (
            <Box component="section" id={props.id} className={props.className}>
                <Container maxWidth={false}>
                    <h1>{props.error.response?.status}</h1>
                    <h3>{props.error.message}</h3>
                    <h5>{props.error.code}</h5>
                </Container>
            </Box>
        );
    }

    return (
        <Box
            component="section"
            id={props.id}
            className={props.className}
            sx={{ maxWidth: "unset" }}
        >
            <Container maxWidth={false}>{props.children}</Container>
        </Box>
    );
};

export default RequestLayout;
