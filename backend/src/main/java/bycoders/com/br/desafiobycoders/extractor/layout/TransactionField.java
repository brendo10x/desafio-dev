package bycoders.com.br.desafiobycoders.extractor.layout;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionField implements Field {

	TYPE(1, 1, 1), 
	DATE_OF_OCCURRENCE(2, 9, 8), 
	AMOUNT(10, 19, 10), 
	BENEFICIARYS_CPF(20, 30, 11),
	CARD_NUMBER(31, 42, 12), 
	TIME_OF_OCCURRENCE(43, 48, 6);

	private final Integer startPosition;
	private final Integer endPosition;
	private final Integer size;

}
