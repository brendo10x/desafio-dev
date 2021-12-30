package bycoders.com.br.desafiobycoders.services;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import bycoders.com.br.desafiobycoders.expections.InvalidCNABFileException;
import bycoders.com.br.desafiobycoders.utils.FileParser;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { StoreService.class })
class StoreServiceTest {

	@Autowired
	private StoreService storeService;

	@MockBean
	private FileParser fileParser;

	@Test
	void shouldImportFileCNABWithSuccessfully() throws IOException {

		MultipartFile mockFileCNABValid = mock(MultipartFile.class);
		
		when(fileParser.parse(mockFileCNABValid)).thenReturn(new ArrayList<String>());
		 
		storeService.batchInsertFromFile(mockFileCNABValid);
	}

	@Test
	void shouldThrowInvalidCNABFileExceptionForAInvalidFile() throws IOException {
		// Arrange
		MultipartFile mockFileCNABInvalid = mock(MultipartFile.class);
		when(mockFileCNABInvalid.getName()).thenReturn("CNAB.txt");
		when(fileParser.parse(mockFileCNABInvalid)).thenThrow(new IOException());

		// Act
		Throwable thrown = catchThrowable(() -> storeService.batchInsertFromFile(mockFileCNABInvalid));
		
		// Assert
		then(thrown).isInstanceOf(InvalidCNABFileException.class)
					.hasMessageContaining("File CNAB.txt is invalid");
	}

}
