package bycoders.com.br.desafiobycoders.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

	private Long id;

	private LocalDateTime transactionAt;

	private BigDecimal amount;

	private String beneficiarysCpf;

	private String cardNumber;

	private TransactionCategoryDTO transactionCategory;

	private Long storeId;

}
