import {FC, ReactNode} from "react";
import {AxiosError} from "axios";

interface Props {
    children: ReactNode
    loading: boolean,
    error: AxiosError<any, any> | null,
    id: string
}

const RequestLayout: FC<Props> = (props) => {
    if (props.loading) return <section id={props.id}>
        <div>Loading...</div>
    </section>

    if (props.error) {
        return (
            <section id={props.id}>
                <h1>{props.error.response?.status}</h1>
                <h3>{props.error.message}</h3>
                <h5>{props.error.code}</h5>
            </section>
        )
    }

    return <section id={props.id}>{
        props.children
    }</section>;
}

export default RequestLayout;