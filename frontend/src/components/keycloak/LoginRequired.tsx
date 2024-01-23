import { FC, ReactNode } from "react";

interface Props {
    children?: ReactNode;
}

const LoginRequired: FC<Props> = (props) => {
    if (true) {
        return props.children;
    } else {
        return <div>Login required</div>;
    }
};

export default LoginRequired;