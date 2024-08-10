package ir.reyreey.myspringexample.controller;

import ir.reyreey.myspringexample.repository.entities.Book;
import ir.reyreey.myspringexample.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 7/26/2024, Friday
 **/
@RestController
@RequestMapping(path = "/books",produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {
    @Autowired
    private BookService bookService;

//    @GetMapping(path = "/")
//    public String hello(){
//        return "Hello Reyreey!";
//    }

    @PreAuthorize("hasAuthority('SELECT')")
    @GetMapping(path = "/all")
    public List<Book> getAllBooks(){
        return bookService.findAll();
    }

    @PreAuthorize("hasAuthority('SELECT')")
    @GetMapping(path = "/{id}")
    public Book getBooksById(@PathVariable long id){
        return bookService.find(id);
    }

    @PreAuthorize("hasAuthority('INSERT')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(@Valid @RequestBody Book book){
        bookService.insert(book);
    }

    @PreAuthorize("hasAuthority('CHANGE')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@Valid @RequestBody Book book){
        bookService.change(book);
    }

    @PreAuthorize("hasAuthority('REMOVE')")
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable long id){
        bookService.remove(id);
    }
}
