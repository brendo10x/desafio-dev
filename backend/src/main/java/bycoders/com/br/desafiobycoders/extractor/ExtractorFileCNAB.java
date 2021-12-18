package bycoders.com.br.desafiobycoders.extractor;

import static bycoders.com.br.desafiobycoders.extractor.layout.StoreField.NAME;
import static bycoders.com.br.desafiobycoders.extractor.layout.StoreField.OWNER_NAME;
import static bycoders.com.br.desafiobycoders.extractor.layout.TransactionField.TYPE;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import bycoders.com.br.desafiobycoders.entities.Store;
import bycoders.com.br.desafiobycoders.entities.Transaction;
import bycoders.com.br.desafiobycoders.entities.TransactionCategory;
import bycoders.com.br.desafiobycoders.extractor.layout.Field;
import bycoders.com.br.desafiobycoders.extractor.layout.TransactionField;

@Component
public class ExtractorFileCNAB {

	private ExtractorFileCNAB() {}
	
	public static Store extractStore(String line) {
		return Store.builder()
				.name(getField(line, NAME))
				.ownerName(getField(line, OWNER_NAME))
				.build();
	}

	public static Transaction extractTransaction(String line) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

		return Transaction.builder()
				.transactionCategory(TransactionCategory.builder().id(Long.parseLong(getField(line, TYPE))).build())
				.transactionAt(LocalDateTime.parse(getField(line, TransactionField.DATE_OF_OCCURRENCE)
						+ getField(line, TransactionField.TIME_OF_OCCURRENCE), formatter))
				.amount(new BigDecimal(getField(line, TransactionField.AMOUNT)).divide(BigDecimal.valueOf(100.00)))
				.beneficiarysCpf(getField(line, TransactionField.BENEFICIARYS_CPF))
				.cardNumber(getField(line, TransactionField.CARD_NUMBER)).build();
	}

	private static String getField(String line, Field field) {
		return line.substring(field.getStartPosition() - 1, field.getEndPosition()).trim();
	}

}
