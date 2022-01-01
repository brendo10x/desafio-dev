package bycoders.com.br.desafiobycoders;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import bycoders.com.br.desafiobycoders.core.mapper.ConverterMapper;

@TestConfiguration
public class TestCaseConfiguration {

	 @Bean
	 ConverterMapper converterMapper() {
		 return new ConverterMapper();
	 }
}
