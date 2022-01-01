package bycoders.com.br.desafiobycoders.resources;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import bycoders.com.br.desafiobycoders.TestCaseConfiguration;
import bycoders.com.br.desafiobycoders.build_test_data.TransactionBuilder;
import bycoders.com.br.desafiobycoders.core.mapper.ConverterMapper;
import bycoders.com.br.desafiobycoders.dtos.TransactionDTO;
import bycoders.com.br.desafiobycoders.entities.Transaction;
import bycoders.com.br.desafiobycoders.services.TransactionService;

@WebMvcTest(controllers = TransactionResource.class)
@Import(TestCaseConfiguration.class)
class TransactionResourceTest {

	private static final String TRANSACTIONS_API = "/api/v1/transactions/";
	
	@MockBean
	private TransactionService mockTransactionService;
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ConverterMapper converterMapper;
	
	@Test
	final void shouldReturnAllTransactionsDTO() throws Exception {
		// Arrange
		Transaction transaction = TransactionBuilder.aTrasanctionWithBoleto().withId(1L).now();
		TransactionDTO transactionsDTO = converterMapper.convertTo(transaction, TransactionDTO.class);
		Page<TransactionDTO> pagedTransaction = new PageImpl<>(List.of(transactionsDTO));

		when(mockTransactionService.findAllTransactionsDTO(ArgumentMatchers.<Specification<Transaction>>any(), any(Pageable.class))).thenReturn(pagedTransaction);
	
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.get(TRANSACTIONS_API)
				.accept("application/json;charset=UTF-8");
		
		// Act & Assert
		mockMvc.perform(mockRequest)
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
		
	}

}
