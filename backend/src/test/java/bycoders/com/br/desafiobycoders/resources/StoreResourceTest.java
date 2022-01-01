package bycoders.com.br.desafiobycoders.resources;

import static bycoders.com.br.desafiobycoders.build_test_data.StoreBuilder.aStore;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import bycoders.com.br.desafiobycoders.converter.ConverterMapper;
import bycoders.com.br.desafiobycoders.dtos.StoreDTO;
import bycoders.com.br.desafiobycoders.entities.Store;
import bycoders.com.br.desafiobycoders.expections.InvalidCNABFileException;
import bycoders.com.br.desafiobycoders.services.StoreService;

@WebMvcTest(controllers = StoreResource.class)
class StoreResourceTest {

	private static final String STORES_API = "/api/v1/stores/";
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StoreService mockStoreService;
	
	private ConverterMapper converterMapper = new ConverterMapper();
	
	@Test
	void shouldUploadFileCNAB() throws Exception {
		// Arrange
		MockMultipartFile mockFileCNABValid = new MockMultipartFile("file", 
																    "CNAB.txt",
																    MediaType.TEXT_PLAIN_VALUE, new FileInputStream(new File("src/test/resources/cnab_files/CNAB.txt")));
		
		doNothing().when(mockStoreService).batchInsertFromFile(mockFileCNABValid);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
													.multipart(STORES_API + "upload-cnab")
											        .file("file", mockFileCNABValid.getBytes());								       
		// Act & Assert
		mockMvc.perform(mockRequest)
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
	}

	@Test
	void shouldThrowInvalidCNABFileExceptionForAInvalidFile() throws Exception {
		// Arrange
		
		MockMultipartFile mockFileCNABInvalid = new MockMultipartFile("file","CNAB.txt",
				MediaType.TEXT_PLAIN_VALUE, new FileInputStream(new File("src/test/resources/cnab_files/CNAB.txt")));
		 
		doThrow(new InvalidCNABFileException()).when(mockStoreService).batchInsertFromFile(mockFileCNABInvalid);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.multipart(STORES_API + "upload-cnab")
				.file(mockFileCNABInvalid);
		
		// Act & Assert
		mockMvc.perform(mockRequest)
				.andExpect(status().isBadRequest())
				.andDo(print());
	}
	
	@Test
	void shouldFindAllStores() throws Exception{
		// Arrange
		List<Store> stores = List.of(aStore().withId(1L).now(), aStore().withId(2L).now()); 
		List<StoreDTO> storesDTO = converterMapper.convertToList(stores, StoreDTO.class);
		
		when(mockStoreService.findAllStores()).thenReturn(storesDTO);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.get(STORES_API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		//Act & Assert
		mockMvc.perform(mockRequest)
				.andExpect(status().isOk())
				.andDo(print());
	}

}
