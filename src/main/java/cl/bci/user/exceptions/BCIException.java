package cl.bci.user.exception;

public class BCIException extends Exception {

    public BCIException(String message, Throwable cause) {
		super(message, cause);	
	}

	public BCIException(String message) {
		super(message);		
	}

	public BCIException(Throwable cause) {
		super(cause);	
	}	
    
}