package bycoders.com.br.desafiobycoders.mapper;

import static bycoders.com.br.desafiobycoders.build_test_data.StoreBuilder.aStore;
import static org.assertj.core.api.BDDAssertions.then;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import bycoders.com.br.desafiobycoders.build_test_data.StoreBuilder;
import bycoders.com.br.desafiobycoders.converter.ConverterMapper;
import bycoders.com.br.desafiobycoders.dtos.StoreDTO;
import bycoders.com.br.desafiobycoders.entities.Store;

@SpringBootTest
class TestConvertModelMapper {

	@Autowired
	ConverterMapper mapper;

	@Test
	void testConvertModelToDTO() {
		// Arrange
		Store store = aStore().now();
		
		// Act
		StoreDTO storeDTO = mapper.convertTo(store, StoreDTO.class);

		// Assert 
		then(storeDTO.getName()).isEqualTo(store.getName());
	}

	@Test
	void testConvertToList() {
		// Arrange
		Store firstStore = StoreBuilder.aStore().withId(1L).now();
		Store secondStore = StoreBuilder.aStore().withId(1L).now();
		List<Store> stores = List.of(firstStore, secondStore);
		
		// Act
		List<StoreDTO> storesDTO = mapper.convertToList(stores, StoreDTO.class);

		// Assert
		then(storesDTO).hasSize(2);
	}
}
