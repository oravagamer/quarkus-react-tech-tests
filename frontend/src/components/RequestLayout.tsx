import { FC } from "react";
import { AxiosError } from "axios";
import { CircularProgress, Container } from "@mui/material";
import Section from "./Section.tsx";

interface Props extends DefaultProps {
    loading: boolean;
    error: AxiosError<any, any> | null;
}

const RequestLayout: FC<Props> = (props) => {
    if (props.loading)
        return (
            <Section id={props.id} className={props.className}>
                <Container maxWidth={false}>
                    <CircularProgress sx={{ scale: "-1 1" }} />
                </Container>
            </Section>
        );

    if (props.error) {
        return (
            <Section id={props.id} className={props.className}>
                <Container maxWidth={false}>
                    <h1>{props.error.response?.status}</h1>
                    <h3>{props.error.message}</h3>
                    <h5>{props.error.code}</h5>
                </Container>
            </Section>
        );
    }

    return (
        <Section>
            <Container maxWidth={false}>{props.children}</Container>
        </Section>
    );
};

export default RequestLayout;
