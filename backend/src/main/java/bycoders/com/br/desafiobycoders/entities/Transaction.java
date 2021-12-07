package bycoders.com.br.desafiobycoders.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "transactions")
public class Transaction {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private LocalDateTime transactionAt;
	
	@Column(nullable = false)
	private BigDecimal amount;
	
	@Column(nullable = false)
	private String beneficiarysCpf;
	
	@Column(nullable = false)
	private String cardNumber;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private TransactionCategory transactionCategory;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Store store;
	
}
