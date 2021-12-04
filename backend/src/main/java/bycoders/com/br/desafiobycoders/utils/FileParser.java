package bycoders.com.br.desafiobycoders.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileParser {
	
	   public List<String> parse(MultipartFile file) throws IOException {
	        InputStream inputStream = file.getInputStream();
	        return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
	                .lines().collect(Collectors.toList());
	    }
}
