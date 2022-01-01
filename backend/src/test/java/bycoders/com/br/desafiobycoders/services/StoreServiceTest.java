package bycoders.com.br.desafiobycoders.services;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import bycoders.com.br.desafiobycoders.build_test_data.StoreBuilder;
import bycoders.com.br.desafiobycoders.converter.ConverterMapper;
import bycoders.com.br.desafiobycoders.dtos.StoreDTO;
import bycoders.com.br.desafiobycoders.entities.Store;
import bycoders.com.br.desafiobycoders.expections.InvalidCNABFileException;
import bycoders.com.br.desafiobycoders.repositories.StoreRepository;
import bycoders.com.br.desafiobycoders.utils.FileParser;

@ExtendWith(MockitoExtension.class)
class StoreServiceTest {

	@Mock
	private FileParser mockFileParser;	
	
	@Mock
	private ConverterMapper mockConverterMapper;

	@Mock 
	private StoreRepository mockStoreRepository;
	 
	@InjectMocks
	private StoreService storeService;
	
	private ConverterMapper converterMapper = new ConverterMapper();
	
	@Test
	void shouldImportFileCNABWithSuccessfully() throws IOException {
		// Arrange
		MultipartFile mockFileCNABValid = mock(MultipartFile.class);
		
		// Act
		when(mockFileParser.parse(mockFileCNABValid)).thenReturn(new ArrayList<String>());
		 
		// Assert
		storeService.batchInsertFromFile(mockFileCNABValid);
	}

	@Test
	void shouldThrowInvalidCNABFileExceptionForAInvalidFile() throws IOException {
		// Arrange
		MultipartFile mockFileCNABInvalid = mock(MultipartFile.class);
		
		when(mockFileCNABInvalid.getName()).thenReturn("CNAB.txt");
		when(mockFileParser.parse(mockFileCNABInvalid)).thenThrow(new IOException());

		// Act
		Throwable thrown = catchThrowable(() -> storeService.batchInsertFromFile(mockFileCNABInvalid));
		
		// Assert
		then(thrown).isInstanceOf(InvalidCNABFileException.class)
					.hasMessageContaining("File CNAB.txt is invalid");
	}

	@Test
	void shouldReturnAllStores() {
		// Arrange
		Store firstStore = StoreBuilder.aStore().withId(1L).now();
		Store secondStore = StoreBuilder.aStore().withId(1L).now();
		
		List<Store> stores = List.of(firstStore, secondStore);
		List<StoreDTO> storesDTO = converterMapper.convertToList(stores, StoreDTO.class);
		
		when(mockStoreRepository.findAll()).thenReturn(stores);
		when(mockConverterMapper.convertToList(any(), ArgumentMatchers.<Class<StoreDTO>>any())).thenReturn(storesDTO);
		
		// Act
		List<StoreDTO> foundStores = storeService.findAllStores();
		
		// Assert
		then(foundStores).isNotEmpty()
						 .hasSize(2)
						 .containsAll(storesDTO);
	}

}
