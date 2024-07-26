package ir.reyreey.myspringexample.service;

import ir.reyreey.myspringexample.repository.BookRepository;
import ir.reyreey.myspringexample.repository.entities.Book;
import ir.reyreey.myspringexample.service.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 7/26/2024, Friday
 **/

@Service
public class InternalBookService implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book find(Long id) {
        var book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new DataNotFoundException("book with id (%d) not found".formatted(id));
        }
        return book.get();
    }

    @Override
    public Boolean isExists(String title) {
        return bookRepository.existsByTitle(title);
    }

    @Override
    public void insert(Book book) {
            bookRepository.save(book);
    }

    @Override
    public void change(Book book) {
        if (bookRepository.existsById(book.getId())) {
            bookRepository.save(book);
        }
        else throw new DataNotFoundException("book with id (%d) not found".formatted(book.getId()));
    }

    @Override
    public void remove(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        }
    }
}
