package ir.reyreey.myspringexample.repository;

import ir.reyreey.myspringexample.repository.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 7/28/2024, Sunday
 **/
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
