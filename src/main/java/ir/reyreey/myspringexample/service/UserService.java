package ir.reyreey.myspringexample.service;

import ir.reyreey.myspringexample.repository.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 7/28/2024, Sunday
 **/
public interface UserService extends UserDetailsService {
    public void insertUser(User user);
}
