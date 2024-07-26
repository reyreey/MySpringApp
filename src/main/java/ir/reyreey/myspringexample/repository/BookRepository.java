package ir.reyreey.myspringexample.repository;

import ir.reyreey.myspringexample.repository.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 7/26/2024, Friday
 **/
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitle(String title);
}
