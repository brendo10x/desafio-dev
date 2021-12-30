package bycoders.com.br.desafiobycoders.repositories;

import static bycoders.com.br.desafiobycoders.build_test_data.StoreBuilder.aStore;
import static org.assertj.core.api.BDDAssertions.then;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import bycoders.com.br.desafiobycoders.entities.Store;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
class StoreRepositoryTest {
 
	@Autowired
	TestEntityManager testEntityManager;

	@Autowired
	StoreRepository storeRepository;
	
	@Test
	void shouldFindStoreByName() {
		// Arrange	
		Store store = aStore().now();
		
		Store savedStore = testEntityManager.persist(store);
		
		// Act 
		Optional<Store> foundStore = storeRepository.findByName(savedStore.getName());
		
		// Assert
		then(foundStore).isPresent();
		then(foundStore.get().getId()).isGreaterThan(0);
		then(foundStore.get().getName()).isEqualTo(store.getName());
		then(foundStore.get().getOwnerName()).isEqualTo(store.getOwnerName());
		
	} 
}
