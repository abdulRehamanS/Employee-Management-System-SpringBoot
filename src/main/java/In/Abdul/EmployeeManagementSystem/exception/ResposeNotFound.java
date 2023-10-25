package In.Abdul.EmployeeManagementSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResponseNotFoundException extends RuntimeException{

    public ResponseNotFoundException(String message) {
        super(message);
    }
}
