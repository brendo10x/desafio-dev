package bycoders.com.br.desafiobycoders.dtos;

import bycoders.com.br.desafiobycoders.enums.TransactionCategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCategoryDTO {

	private String description;

	private TransactionCategoryType TransactionCategoryType;
}
