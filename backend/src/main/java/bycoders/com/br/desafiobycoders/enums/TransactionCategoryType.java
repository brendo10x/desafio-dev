package bycoders.com.br.desafiobycoders.enums;

import lombok.Getter;

@Getter
public enum TransactionCategoryType {

	INCOME("income"), OUTCOME("outcome");

	private String type;

	TransactionCategoryType(String type) {
		this.type = type;
	}

}
