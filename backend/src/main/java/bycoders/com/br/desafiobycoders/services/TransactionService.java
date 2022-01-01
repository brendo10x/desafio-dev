package bycoders.com.br.desafiobycoders.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import bycoders.com.br.desafiobycoders.converter.ConverterMapper;
import bycoders.com.br.desafiobycoders.dtos.TransactionDTO;
import bycoders.com.br.desafiobycoders.entities.Transaction;
import bycoders.com.br.desafiobycoders.repositories.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private ConverterMapper mapperModel;

	public Page<TransactionDTO> findAllTransactionsDTO(Specification<Transaction> transactionFilter,
			Pageable pageable) {
		 
		Page<Transaction> transactionsPage = transactionRepository.findAll(transactionFilter, pageable);
		List<TransactionDTO> transactionsDTO = mapperModel.convertToList(transactionsPage.getContent(), TransactionDTO.class);
		return new PageImpl<>(transactionsDTO, transactionsPage.getPageable(), transactionsPage.getTotalElements());
	}

}
