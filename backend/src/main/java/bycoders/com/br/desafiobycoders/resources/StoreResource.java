package bycoders.com.br.desafiobycoders.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bycoders.com.br.desafiobycoders.dtos.StoreDTO;
import bycoders.com.br.desafiobycoders.expections.InvalidCNABFileException;
import bycoders.com.br.desafiobycoders.services.StoreService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/v1/stores")
@Api(tags = "Store Resource")
public class StoreResource {

	@Autowired
	private StoreService storeService;

	@PostMapping("/upload-cnab")
	public ResponseEntity<String> uploadFileCNAB(@RequestPart MultipartFile file) {

		try {
			storeService.batchInsertFromFile(file);
			log.info("Cnab file imported into database successfully!");
			
		} catch (InvalidCNABFileException ex) {
			log.error("cnab file import failed");
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();

	}

	@GetMapping
	public List<StoreDTO> findAllStores() {
		return storeService.findAllStores();
	}

}
