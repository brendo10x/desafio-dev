package bycoders.com.br.desafiobycoders.build_test_data;

import bycoders.com.br.desafiobycoders.entities.TransactionCategory;
import bycoders.com.br.desafiobycoders.enums.TransactionCategoryType;

public class TransactionCategoryBuilder {

	private TransactionCategory transactionCategory;
	
	private TransactionCategoryBuilder() {}
	
	public static TransactionCategoryBuilder aDebito() {
		TransactionCategoryBuilder builder = new TransactionCategoryBuilder();
		
		builder.transactionCategory = TransactionCategory.builder()
				.id(1L)
				.description("Débito")
				.transactionCategoryType(TransactionCategoryType.INCOME)
				.build();
		
		return builder;
	}
	
	public static TransactionCategoryBuilder aBoleto() {
		TransactionCategoryBuilder builder = new TransactionCategoryBuilder();
		builder.transactionCategory = TransactionCategory.builder()
				.id(2L)
				.description("Boleto")
				.transactionCategoryType(TransactionCategoryType.OUTCOME)
				.build();
		
		return builder;
	}
	
	public TransactionCategory now() {
		return transactionCategory;
	}
	
}
