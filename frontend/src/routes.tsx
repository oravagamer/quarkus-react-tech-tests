import Layout from "./components/Layout.tsx";
import ErrorPage from "./pages/ErrorPage.tsx";
import RouteObject from 'react-router-dom';

const routes: RouteObject[] = [
    {
        path: "/",
        element: <Layout/>,
        errorElement: <ErrorPage />,
        children: [
            {
                path: "",
                children: [
                    {
                        path: "",
                        async lazy() {
                            let { Home } = await import("./pages/common/Home.tsx");
                            return { element: <Home /> };
                        }
                    },
                    {
                        path: "gallery",
                        async lazy() {
                            let { Gallery } = await import("./pages/common/Gallery.tsx");
                            return { element: <Gallery /> };
                        }
                    }
                ]
            },
            {
                path: "admin",
                children: [
                    {
                        path: "",
                        async lazy() {
                            let { AdminHome } = await import("./pages/admin/AdminHome.tsx");
                            return { element: <AdminHome /> };
                        }
                    }
                ]
            }
        ]
    }
];

export default routes;