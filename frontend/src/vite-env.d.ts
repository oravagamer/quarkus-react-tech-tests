interface Gallery {
    id: number,
    name: string,
    description: string,
    created: Date,
    edited: Date,
    pid?: number
}

interface Picture {
    id: number,
    description: string,
    uploaded: Date,
    edited: Date
}
