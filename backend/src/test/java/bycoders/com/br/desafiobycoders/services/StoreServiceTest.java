package bycoders.com.br.desafiobycoders.services;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import bycoders.com.br.desafiobycoders.expections.InvalidCNABFileException;
import bycoders.com.br.desafiobycoders.utils.FileParser;

@ExtendWith(MockitoExtension.class)
class StoreServiceTest {

	@InjectMocks
	private StoreService storeService;

	@Mock
	private FileParser fileParser;

	@Test
	void shouldImportFileCNABWithSuccessfully() throws IOException {
		// Arrange
		MultipartFile mockFileCNABValid = mock(MultipartFile.class);
		
		// Act
		when(fileParser.parse(mockFileCNABValid)).thenReturn(new ArrayList<String>());
		 
		// Assert
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
