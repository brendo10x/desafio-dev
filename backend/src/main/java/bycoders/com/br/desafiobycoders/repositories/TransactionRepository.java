package bycoders.com.br.desafiobycoders.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import bycoders.com.br.desafiobycoders.entities.Store;
import bycoders.com.br.desafiobycoders.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

	List<Transaction> findAllTransactionsByStore(Store store);
}
