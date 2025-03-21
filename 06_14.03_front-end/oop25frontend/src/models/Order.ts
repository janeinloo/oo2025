import { Person } from "./Person"
import { Product } from "./Products"

export type Order = {
    id: number,
    created: Date,
    person: Person,
    products: Product[],
    totalSum: number
}