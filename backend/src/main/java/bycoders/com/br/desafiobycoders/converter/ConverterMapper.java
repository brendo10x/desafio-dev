package bycoders.com.br.desafiobycoders.converter;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

@Component
public class ConverterMapper {
	
	private ModelMapper mapper = new ModelMapper();

	public <D> D convertTo(Object source, Class<D> destinationType) {
		return mapper.map(source, destinationType);
	}

	public <D> List<D> convertToList(List<?> source, Class<D> destinationType) {
		return mapper.map(source, new TypeToken<List<D>>() {
		}.getType());
	}
}
