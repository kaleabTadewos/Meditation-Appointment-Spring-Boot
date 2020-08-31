package app_checking.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import app_checking.util.CustomError;

import java.util.Date;

@ControllerAdvice
//methods inside a class annotated with @ControllerAdvice can be applied to all controllers globally.
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
	//by extending ResponseEntityExceptionHandler I got default implemented exceptions.
	//default implementations can be overriden so whenever an error match there type comes it will be evaluated with them.
	
	//For additional Custom implementation , use @ExceptionHandler annotation and specify which exception should be handled on methods.
	
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),"From Methodnotvalid exception ",ex.getMessage());
        return new ResponseEntity<>(customErrorDetails,HttpStatus.BAD_REQUEST);

    }

    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),"From HttpRequestnotsupported exception ing geh",ex.getMessage());
        return new ResponseEntity<>(customErrorDetails,HttpStatus.METHOD_NOT_ALLOWED);
    }
    
    //if any exception comes with of type UserNameNotFoundException then it will be handled here.
    //It is also possible to add other types of exceptions on this method then it will call appropriate method based on type of exception. look at the picture 
    //  -- inside documents on the way.
    
    //Prepare different type of exception and throw them when you suspect they happen, then they will be handled like the following.

    @ExceptionHandler(UserNameNotFoundException.class)
    public final ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),"From HttpRequestnotsupported exception ing geh",ex.getMessage());
        return new ResponseEntity<>(customErrorDetails,HttpStatus.NOT_FOUND);
    }
    
    //if any of the above method don't handle the exception then it will be handled here.
    
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);
        //
        final CustomErrorDetails apiError = new CustomErrorDetails(new Date(), "error occurred" , ex.getLocalizedMessage());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
