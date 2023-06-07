package Assignment.Assignment.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class HandleAllError {

    @ExceptionHandler(AllExceptions.class)
    public ResponseEntity<AllExceptionErrorResponse> exceptionFoundError(AllExceptions ex){

        AllExceptionErrorResponse error = new AllExceptionErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND);
        error.setMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {

        List<String> errors = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            errors.add(error.getDefaultMessage());
//            errors.add(error.getObjectName());
        }

        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
          Map<String, List<String>> errorResponse = new HashMap<>();
          errorResponse.put("errors", errors);
          return errorResponse;
    }


}
