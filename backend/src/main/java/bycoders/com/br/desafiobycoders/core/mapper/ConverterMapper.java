package bycoders.com.br.desafiobycoders.core.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ConverterMapper {

	private ModelMapper mapper = new ModelMapper();

	public <D> D convertTo(Object source, Class<D> destinationType) {
		return mapper.map(source, destinationType);
	}

	public <S, T> List<T> convertToList(List<S> source, Class<T> targetClass) {
		return source
				.stream()
				.map(element -> mapper.map(element, targetClass))
				.collect(Collectors.toList());
	}

}
