package bycoders.com.br.desafiobycoders.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {

	private Long id;

	private String name;

	private String ownerName;

	private BigDecimal balance;

}
