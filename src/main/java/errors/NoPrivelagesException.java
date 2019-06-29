package errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class NoPrivelagesException extends RuntimeException {

	public NoPrivelagesException(String message) {
		super(message);
		
	}

}
