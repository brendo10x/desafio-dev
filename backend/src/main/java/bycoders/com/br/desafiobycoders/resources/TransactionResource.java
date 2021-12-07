package bycoders.com.br.desafiobycoders.resources;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bycoders.com.br.desafiobycoders.dtos.TransactionDTO;
import bycoders.com.br.desafiobycoders.repositories.filter.TransactionFilter;
import bycoders.com.br.desafiobycoders.repositories.spec.TransactionSpecs;
import bycoders.com.br.desafiobycoders.services.TransactionService;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/transactions")
@Api(tags = "Transaction Resource")
public class TransactionResource {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	ModelMapper modelMapper;

	@GetMapping
	public Page<TransactionDTO> findAllTransactions(TransactionFilter transactionFilter, Pageable pageable) {
		return transactionService.findAllTransactionsDTO(TransactionSpecs.usingFilter(transactionFilter), pageable);
	}

}
