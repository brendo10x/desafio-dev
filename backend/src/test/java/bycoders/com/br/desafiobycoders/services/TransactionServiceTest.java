package bycoders.com.br.desafiobycoders.services;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import bycoders.com.br.desafiobycoders.TestCaseConfiguration;
import bycoders.com.br.desafiobycoders.build_test_data.TransactionBuilder;
import bycoders.com.br.desafiobycoders.core.mapper.ConverterMapper;
import bycoders.com.br.desafiobycoders.dtos.TransactionDTO;
import bycoders.com.br.desafiobycoders.entities.Transaction;
import bycoders.com.br.desafiobycoders.repositories.TransactionRepository;
import bycoders.com.br.desafiobycoders.repositories.filter.TransactionFilter;
import bycoders.com.br.desafiobycoders.repositories.spec.TransactionSpecs;

@ExtendWith(SpringExtension.class)
@Import(TestCaseConfiguration.class)
class TransactionServiceTest {

	@Mock
	private TransactionRepository mockTransactionRepository;  

	@Mock
	private ConverterMapper mockConverterMapper;
	
	@InjectMocks
	private TransactionService transactionService;
	
	@Autowired
	private ConverterMapper converterMapper;
	
	@Test
	final void findAllTransactionsDTO_shouldReturnAll() {
		// Arrange
		Transaction transaction = TransactionBuilder.aTrasanctionWithBoleto().withId(1L).now();
		TransactionDTO transactionsDTO = converterMapper.convertTo(transaction, TransactionDTO.class);
		Page<Transaction> pagedTransaction = new PageImpl<>(List.of(transaction));

		when(mockTransactionRepository.findAll(ArgumentMatchers.<Specification<Transaction>>any(), any(Pageable.class))).thenReturn(pagedTransaction);
		when(mockConverterMapper.convertToList(any(), ArgumentMatchers.<Class<TransactionDTO>>any())).thenReturn(List.of(transactionsDTO));
		
		// Act
		Page<TransactionDTO> filteredTransactions = transactionService
															.findAllTransactionsDTO(TransactionSpecs
															.usingFilter(new TransactionFilter()), pagedTransaction.getPageable());
		// Assert
		then(filteredTransactions.getContent()).isNotNull()
											   .hasSize(1)
											   .contains(transactionsDTO);
	}

}
