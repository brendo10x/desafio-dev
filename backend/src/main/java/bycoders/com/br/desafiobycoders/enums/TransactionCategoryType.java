package bycoders.com.br.desafiobycoders.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TransactionCategoryType {

	INCOME("Entrada"), OUTCOME("Saída");

	@JsonValue
	private String type;

}
