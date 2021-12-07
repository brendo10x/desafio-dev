package bycoders.com.br.desafiobycoders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bycoders.com.br.desafiobycoders.entities.TransactionCategory;

@Repository
public interface TransactionCategoryRepository  extends JpaRepository<TransactionCategory, Long>{

}
