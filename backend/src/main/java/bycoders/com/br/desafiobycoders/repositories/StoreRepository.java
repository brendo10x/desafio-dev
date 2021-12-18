package bycoders.com.br.desafiobycoders.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bycoders.com.br.desafiobycoders.entities.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

	Optional<Store> findByName(String name);

}
