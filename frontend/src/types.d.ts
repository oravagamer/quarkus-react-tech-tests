import { ReactNode } from "react";

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
