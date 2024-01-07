interface Gallery {
    id: number;
    name: string;
    description: string;
    created: Date;
    edited: Date;
    thumbnail?: number;
}

interface Picture {
    id: number;
    description: string;
    uploaded: Date;
    edited: Date;
}