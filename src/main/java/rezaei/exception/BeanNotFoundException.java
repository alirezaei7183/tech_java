package rezaei.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class BeanNotFoundException  extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public BeanNotFoundException(String message) {
		super(message);
	}
}
