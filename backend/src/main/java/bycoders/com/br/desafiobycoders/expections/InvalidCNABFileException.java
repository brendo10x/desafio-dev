package bycoders.com.br.desafiobycoders.expections;

public class InvalidCNABFileException extends RuntimeException {
 
	private static final long serialVersionUID = 1L;

	public InvalidCNABFileException(String mensagem) {
		super(mensagem);
	}
}
