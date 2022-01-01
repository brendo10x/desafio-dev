package bycoders.com.br.desafiobycoders.repositories;

import static bycoders.com.br.desafiobycoders.build_test_data.StoreBuilder.aStore;
import static bycoders.com.br.desafiobycoders.build_test_data.TransactionBuilder.aTrasanctionWithBoleto;
import static bycoders.com.br.desafiobycoders.build_test_data.TransactionBuilder.aTrasanctionWithDebito;
import static org.assertj.core.api.BDDAssertions.then;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import bycoders.com.br.desafiobycoders.entities.Store;
import bycoders.com.br.desafiobycoders.entities.Transaction;

@ActiveProfiles("test")
@DataJpaTest
class TransactionRepositoryTest {

	@Autowired
	TestEntityManager testEntityManager;

	@Autowired
	TransactionRepository transactionRepository;
	
	@Test
	void shouldReturnAllTransactionsByStore() {
		// Arrange
		Store store = aStore().now();
		Store savedStore = testEntityManager.persist(store);
		
		Transaction firstTransaction = aTrasanctionWithBoleto().withStore(savedStore).now();
		Transaction secondTransaction = aTrasanctionWithDebito().withStore(savedStore).now();
		testEntityManager.persist(firstTransaction);
		testEntityManager.persist(secondTransaction);
		
		// Act
		List<Transaction> filteredTransactions = transactionRepository.findAllTransactionsByStore(savedStore);
		
		// Assert
		then(savedStore.getId()).isNotNull()
								.isGreaterThan(0);
		then(savedStore.getName()).isEqualTo(store.getName());
		then(filteredTransactions).isNotEmpty()
								  .hasSize(2)
								  .allMatch(transaction -> transaction.getStore() == savedStore);
	}

}
