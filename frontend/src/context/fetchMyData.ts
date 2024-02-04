import { AxiosRequestConfig } from "axios";
import useAxios from "axios-hooks";
import { useEffect, useState } from "react";

const useFetchMyData = <TBody>(config: AxiosRequestConfig<TBody> | string) => {
    const [{ data, loading, error }] = useAxios<TBody>(config);
    const [myData, setMyData] = useState(data);

    useEffect(() => {
        setMyData(data);
    }, [data]);

    return { myData, loading, error, setMyData };
};
export default useFetchMyData;
