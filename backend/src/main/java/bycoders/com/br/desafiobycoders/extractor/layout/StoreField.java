package bycoders.com.br.desafiobycoders.extractor.layout;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StoreField implements Field {

	NAME(63, 80, 19), 
	OWNER_NAME(49, 62, 14);

	private final Integer startPosition;
	private final Integer endPosition;
	private final Integer size;

}
