package ir.reyreey.myspringexample.controller.customization;

import com.auth0.jwt.exceptions.JWTVerificationException;
import ir.reyreey.myspringexample.service.exceptions.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 7/26/2024, Friday
 **/
@RestControllerAdvice
public class MyExceptionHandler {

     @ExceptionHandler(DataNotFoundException.class)
     public ResponseEntity<Map<String,String>> handleDataNotFoundException(final DataNotFoundException e) {
        return new ResponseEntity<>(
                generate(e),
                HttpStatus.NOT_FOUND
        );
     }

     @ExceptionHandler(AuthenticationException.class)
     public ResponseEntity<Map<String,String>> handleAuthenticationException(final AuthenticationException e) {
         return new ResponseEntity<>(
                 generate(e),
                 HttpStatus.UNAUTHORIZED
         );
     }

     //this exception doesn't occur in controller and is not seen by ExceptionTranslationFilter
//     @ExceptionHandler(JWTVerificationException.class)
//     public ResponseEntity<Map<String,String>> handleJWTVerificationException(final JWTVerificationException e) {
//         return new ResponseEntity<>(
//                 generate(e),
//                 HttpStatus.UNAUTHORIZED
//         );
//     }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String,String>> handleAccessDeniedException(final AccessDeniedException e) {
        return new ResponseEntity<>(
                generate(e),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Map<String,String>> handleException(final Throwable t) {
        return new ResponseEntity<>(
                generate(t),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

     private Map<String,String> generate(final Throwable t) {
        var response = new HashMap<String, String>();
        response.put("message", t.getMessage());
        response.put("time",new Date().toString());
        return response;
     }
}
