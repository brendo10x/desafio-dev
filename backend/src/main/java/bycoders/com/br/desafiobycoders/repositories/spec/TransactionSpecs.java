package bycoders.com.br.desafiobycoders.repositories.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import bycoders.com.br.desafiobycoders.entities.Transaction;
import bycoders.com.br.desafiobycoders.repositories.filter.TransactionFilter;

public class TransactionSpecs {
	
	private TransactionSpecs() {}

	public static Specification<Transaction> usingFilter(TransactionFilter filter) {
		return (root, query, builder) -> {
			
			var predicates = new ArrayList<Predicate>();

			if (filter.getStoreId() != null) {
				predicates.add(builder.equal(root.get("store"), filter.getStoreId()));
			}

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
