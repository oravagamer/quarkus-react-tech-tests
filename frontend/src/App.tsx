import {RouterProvider, createBrowserRouter} from "react-router-dom";

import loadable from "@loadable/component";
import Layout from "./components/Layout.tsx";
import ErrorPage from "./pages/ErrorPage.tsx";
import LoadingPage from "./pages/LoadingPage.tsx";

const App = () => {
    const Home = loadComponentAsync("./pages/common/Home.tsx");
    const Galleries = loadComponentAsync("./pages/common/Galleries.tsx");
    const Gallery = loadComponentAsync("./pages/common/Gallery.tsx");
    const AdminHome = loadComponentAsync("./pages/admin/AdminHome.tsx");

    const router = createBrowserRouter([
        {
            path: "",
            element: <Layout />,
            errorElement: <ErrorPage/>,
            children: [
                {
                    path: "",
                    children: [
                        {
                            index: true,
                            element: <Home/>
                        },
                        {
                            path: "gallery",
                            children: [
                                {
                                    index: true,
                                    element: <Galleries/>
                                },
                                {
                                    path: ":id",
                                    element: <Gallery/>
                                }
                            ]
                        }
                    ]
                },
                {
                    path: "admin",
                    children: [
                        {
                            index: true,
                            element: <AdminHome/>
                        }
                    ]
                }
            ]
        }
    ]);

    return (
        <RouterProvider router={router}/>
    );
}

function loadComponentAsync(path: string) {
    return loadable(() => import(/* @vite-ignore*/path), {
        fallback: <LoadingPage />
    });
}

export default App
