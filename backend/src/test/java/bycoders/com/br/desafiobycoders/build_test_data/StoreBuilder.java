package bycoders.com.br.desafiobycoders.build_test_data;

import com.github.javafaker.Faker;

import bycoders.com.br.desafiobycoders.entities.Store;

public class StoreBuilder {

	private Store store;

	private StoreBuilder() {
	}

	public static StoreBuilder aStore() {
		StoreBuilder builder = new StoreBuilder();
		
		builder.store= new Store();
		builder.store.setId(null);
		builder.store.setName(new Faker().job().title());
		builder.store.setOwnerName(new Faker().name().fullName());
		
		return builder;
	}
	
	public StoreBuilder withId(Long id) {
		store.setId(id);
		return this;
	}

	public Store now() {
		return store;
	}
 
}
