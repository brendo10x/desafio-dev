package bycoders.com.br.desafiobycoders.build_test_data;

import static bycoders.com.br.desafiobycoders.build_test_data.StoreBuilder.aStore;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.github.javafaker.Faker;

import bycoders.com.br.desafiobycoders.entities.Store;
import bycoders.com.br.desafiobycoders.entities.Transaction;

public class TransactionBuilder {

	private Transaction transaction;
	
	private TransactionBuilder() {}
	
	public static TransactionBuilder aTrasanctionWithBoleto() {
		
		TransactionBuilder transactionBuilder = new TransactionBuilder();
		
		transactionBuilder.transaction = new Transaction();
		transactionBuilder.transaction.setId(null);
		transactionBuilder.transaction.setAmount(BigDecimal.valueOf(new Faker().number().numberBetween(10L, 10000L)));
		transactionBuilder.transaction.setBeneficiarysCpf("04974613316");
		transactionBuilder.transaction.setCardNumber(new Faker().finance().creditCard());
		transactionBuilder.transaction.setStore(aStore().now());
		transactionBuilder.transaction.setTransactionAt(LocalDateTime.now());
		transactionBuilder.transaction.setTransactionCategory(TransactionCategoryBuilder.aBoleto().now());
		
		return transactionBuilder;
	}
	
	public static TransactionBuilder aTrasanctionWithDebito() {
		
		TransactionBuilder transactionBuilder = new TransactionBuilder();
		
		transactionBuilder.transaction = new Transaction();
		transactionBuilder.transaction.setId(null);
		transactionBuilder.transaction.setAmount(BigDecimal.valueOf(new Faker().number().numberBetween(10L, 10000L)));
		transactionBuilder.transaction.setBeneficiarysCpf("04974613316");
		transactionBuilder.transaction.setCardNumber(new Faker().finance().creditCard());
		transactionBuilder.transaction.setStore(aStore().now());
		transactionBuilder.transaction.setTransactionAt(LocalDateTime.now());
		transactionBuilder.transaction.setTransactionCategory(TransactionCategoryBuilder.aDebito().now());
		
		return transactionBuilder;
	}
	
	public TransactionBuilder withStore(Store store) {
		transaction.setStore(store);
		return this;
	}
	
	public TransactionBuilder withAmount(BigDecimal amount) {
		transaction.setAmount(amount);
		return this;
	}
	
	
	
	public TransactionBuilder withBeneficiarysCpf(String beneficiarysCpf) {
		transaction.setBeneficiarysCpf(beneficiarysCpf);
		return this;
	}
	
	public Transaction now() {
		return transaction;
	}
	
}
