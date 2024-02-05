/// <reference types="vite/client" />
/// <reference types="vite-plugin-svgr/client" />

import { ReactNode } from "react";

declare global {
    interface Gallery {
        id: number;
        name: string;
        description: string;
        created: Date;
        edited: Date;
        thumbnail?: number | null;
    }

    interface Picture {
        id: number;
        description: string;
        uploaded: Date;
        edited: Date;
    }

    interface DefaultProps {
        id?: string;
        className?: string;
        children?: ReactNode;
    }
}
