package bycoders.com.br.desafiobycoders.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import bycoders.com.br.desafiobycoders.dtos.StoreDTO;
import bycoders.com.br.desafiobycoders.entities.Store;
import bycoders.com.br.desafiobycoders.entities.Transaction;
import bycoders.com.br.desafiobycoders.expections.InvalidCNABFileException;
import bycoders.com.br.desafiobycoders.extractor.ExtractorFileCNAB;
import bycoders.com.br.desafiobycoders.repositories.StoreRepository;
import bycoders.com.br.desafiobycoders.repositories.TransactionRepository;
import bycoders.com.br.desafiobycoders.utils.FileParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StoreService {

	@Autowired
	private FileParser fileParser;

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	ModelMapper modelMapper;

	public void batchInsertFromFile(MultipartFile file) throws InvalidCNABFileException {
		try {
			List<String> lines = fileParser.parse(file);

				lines.forEach(line -> {
	
					Store store = ExtractorFileCNAB.extractStore(line);
					Optional<Store> storeOptional = storeRepository.findByName(store.getName());
	
					if (!storeOptional.isPresent()) {
						store = storeRepository.save(store);
					} else {
						store = storeOptional.get();
					}
	
					Transaction transaction = ExtractorFileCNAB.extractTransaction(line);
					transaction.setStore(store);
					transactionRepository.save(transaction);

				log.info("cnab file line imported successfully: " + line);
			});

		} catch (IOException e) {
			String errorMessage = "File " + file.getName() + " is invalid";
			log.error(errorMessage);
			throw new InvalidCNABFileException(errorMessage);
		}
	}

	 

	public List<StoreDTO> findAllStores() {

		List<Store> stores = storeRepository.findAll();
		return stores.stream().map(store -> modelMapper.map(store, StoreDTO.class)).collect(Collectors.toList());
	}
 
}
