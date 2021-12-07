import {TransactionCategory} from "./transaction-category.model";

export class Transaction {
    id: number;
    name?: string;
    amount?: number;
    cardNumber?:string;
    beneficiarysCpf?: string;
    transactionAt?:string;
    transactionCategory?: TransactionCategory;
}
