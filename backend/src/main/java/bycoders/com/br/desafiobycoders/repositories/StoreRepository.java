package bycoders.com.br.desafiobycoders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import bycoders.com.br.desafiobycoders.entities.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

}
