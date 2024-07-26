package ir.reyreey.myspringexample.service;

import ir.reyreey.myspringexample.repository.entities.Book;

import java.util.List;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 7/26/2024, Friday
 **/
public interface BookService {
    List<Book> findAll();
    Book find(Long id);
    Boolean isExists(String name);
    void insert(Book book);
    void change(Book book);
    void remove(Long id);
}
