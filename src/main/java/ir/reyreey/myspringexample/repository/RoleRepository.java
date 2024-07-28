package ir.reyreey.myspringexample.repository;

import ir.reyreey.myspringexample.repository.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 7/28/2024, Sunday
 **/
public interface RoleRepository extends JpaRepository<Role, Long> {
}
