package bycoders.com.br.desafiobycoders;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.web.multipart.MultipartFile;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

import bycoders.com.br.desafiobycoders.entities.Store;
import bycoders.com.br.desafiobycoders.entities.Transaction;
import bycoders.com.br.desafiobycoders.repositories.StoreRepository;
import bycoders.com.br.desafiobycoders.repositories.TransactionCategoryRepository;
import bycoders.com.br.desafiobycoders.repositories.TransactionRepository;
import bycoders.com.br.desafiobycoders.services.StoreService;

@SpringBootTest
@DatabaseTearDown
@TestExecutionListeners({ TransactionalTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
@Transactional
@TestPropertySource(locations = "/application-test.properties")
class StoreServiceTest {

	@Autowired
	private StoreService storeService;

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private TransactionCategoryRepository TransactionCategoryRepository;

	@BeforeEach
	 void setUp() {

		importFileCNAB();
	}

	@Test
	@DisplayName("Deve retornar cico lojas quando o arquivo CNAB for importado")
	 void mustReturnFiveStores_WhenToImportFileCNAB() {

		List<Store> stores = storeRepository.findAll();

		assertThat(stores, hasSize(5));
	}

	@Test
	@DisplayName("Deve retornar três transações da loja do BAR DO JOÃO quando o arquivo CNAB for importado")
	void mustReturnThreeTransactionsByStoreBarDoJoao_WhenToImportFileCNAB() {

		Store store = storeRepository.findByName("BAR DO JOÃO").get();

		loadTransactionsByStore(store);

		assertNotNull(store);

		assertThat(store.getTransactions(), hasSize(3));
	}

	@Test
	@DisplayName("Deve retornar um saldo positivo de R$489.20 da loja do MERCADO DA AVENIDA quando o arquivo CNAB for importado")
	 void itMustReturnAPositiveBalanceOf489_20ReaisFromTheMercadoDaAvenida_WhenToImportFileCNAB() {

		Store store = storeRepository.findByName("MERCADO DA AVENIDA").get();

		loadTransactionsByStore(store);

		assertThat(store.getBalance(), is(new BigDecimal("489.20")));
	}

	private Store loadTransactionsByStore(Store store) {

		List<Transaction> transactions = transactionRepository.findAllTransactionsByStore(store);

		transactions.stream().forEach(t -> {

			t.setTransactionCategory(TransactionCategoryRepository.findById(t.getTransactionCategory().getId()).get());
		});

		store.setTransactions(transactions);

		return store;
	}
 
	private void importFileCNAB() {

		MultipartFile multipartFile = null;

		try {
			multipartFile = new MockMultipartFile("CNAB.txt",
					new FileInputStream(new File("src/test/resources/cnab_files/CNAB.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		storeService.batchInsertFromFile(multipartFile);

	}

}
