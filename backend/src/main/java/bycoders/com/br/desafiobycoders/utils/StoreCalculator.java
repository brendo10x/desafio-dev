package bycoders.com.br.desafiobycoders.utils;

import java.math.BigDecimal;
import java.util.List;

import bycoders.com.br.desafiobycoders.entities.Transaction;
import bycoders.com.br.desafiobycoders.enums.TransactionCategoryType;

public class StoreCalculator {

	private StoreCalculator() {}
	
	public static BigDecimal calculateBalanceFromStoreByTransactions(List<Transaction> transactions) {
		
		if (transactions == null) return new BigDecimal(0);
		
		BigDecimal income = calculateSumByTransactionCategoryType(TransactionCategoryType.INCOME, transactions);
		BigDecimal outcome = calculateSumByTransactionCategoryType(TransactionCategoryType.OUTCOME, transactions);

		return income.subtract(outcome);
	}

	public static BigDecimal calculateSumByTransactionCategoryType(TransactionCategoryType type, List<Transaction> transactions) {
		return transactions.stream()
				.filter(t -> t.getTransactionCategory().getTransactionCategoryType() == type)
				.map(Transaction::getAmount)
				.reduce(BigDecimal.ONE, BigDecimal::add);
	}
}
