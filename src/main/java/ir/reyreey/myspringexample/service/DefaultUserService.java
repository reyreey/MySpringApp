package ir.reyreey.myspringexample.service;

import ir.reyreey.myspringexample.repository.UserRepository;
import ir.reyreey.myspringexample.repository.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 7/28/2024, Sunday
 **/
@Service
public class DefaultUserService implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void insertUser(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user == null) {throw new UsernameNotFoundException("user with username (%s) not found".formatted(username));}
        return user.get();
    }
}
