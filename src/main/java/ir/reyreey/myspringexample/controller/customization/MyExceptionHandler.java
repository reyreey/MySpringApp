package ir.reyreey.myspringexample.controller.customization;

import ir.reyreey.myspringexample.service.exceptions.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Map<String,String>> handleException(final Throwable t) {
         return new ResponseEntity<>(
                 generate(t),
                 HttpStatus.BAD_REQUEST
         );
     }

     @ExceptionHandler(DataNotFoundException.class)
     public ResponseEntity<Map<String,String>> handleDataNotFoundException(final DataNotFoundException e) {
        return new ResponseEntity<>(
                generate(e),
                HttpStatus.NOT_FOUND
        );
     }

     private Map<String,String> generate(final Throwable t) {
        var response = new HashMap<String, String>();
        response.put("message", t.getMessage());
        response.put("time",new Date().toString());
        return response;
     }
}
