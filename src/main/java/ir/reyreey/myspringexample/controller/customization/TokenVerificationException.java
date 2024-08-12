package ir.reyreey.myspringexample.controller.customization;

import org.springframework.security.core.AuthenticationException;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 8/10/2024, Saturday
 **/
public class TokenVerificationException extends AuthenticationException {

    public TokenVerificationException(String msg) {
        super(msg);
    }

}
