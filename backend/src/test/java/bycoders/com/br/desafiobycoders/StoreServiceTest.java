package bycoders.com.br.desafiobycoders;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import bycoders.com.br.desafiobycoders.entities.Store;
import bycoders.com.br.desafiobycoders.repositories.StoreRepository;
import bycoders.com.br.desafiobycoders.services.StoreService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY)
class StoreServiceTest {

	@Autowired
	private StoreService storeService;

	@Autowired
	private StoreRepository storeRepository;
 
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

		assertNotNull(store);
		assertThat(store.getTransactions(), hasSize(3));
	}

	@Test
	@DisplayName("Deve retornar um saldo positivo de R$489.20 da loja do MERCADO DA AVENIDA quando o arquivo CNAB for importado")
	 void itMustReturnAPositiveBalanceOf489_20ReaisFromTheMercadoDaAvenida_WhenToImportFileCNAB() {

		Store store = storeRepository.findByName("MERCADO DA AVENIDA").get();

		assertThat(store.getBalance(), is(new BigDecimal("489.20")));
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
