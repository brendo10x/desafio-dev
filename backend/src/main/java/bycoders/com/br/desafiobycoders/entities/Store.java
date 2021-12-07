package bycoders.com.br.desafiobycoders.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import bycoders.com.br.desafiobycoders.enums.TransactionCategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "stores")
public class Store {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String ownerName;

	@OneToMany(mappedBy = "store")
	private List<Transaction> transactions;

	@Transient
	private BigDecimal balance;

	public BigDecimal getBalance() {

		BigDecimal income = getSumByTransaction(TransactionCategoryType.INCOME);
		BigDecimal outcome = getSumByTransaction(TransactionCategoryType.OUTCOME);

		return income.subtract(outcome);
	}

	public BigDecimal getSumByTransaction(TransactionCategoryType type) {
		return transactions.stream()
				.filter(t -> t.getTransactionCategory().getTransactionCategoryType() == type)
				.map(t -> t.getAmount())
				.reduce(BigDecimal.ONE, BigDecimal::add);
	}
}
