package ir.reyreey.myspringexample.controller;

import ir.reyreey.myspringexample.repository.entities.Book;
import ir.reyreey.myspringexample.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @GetMapping(path = "/all")
    public List<Book> getAllBooks(){
        return bookService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Book getBooksById(@PathVariable long id){
        return bookService.find(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(@RequestBody Book book){
        bookService.insert(book);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@RequestBody Book book){
        bookService.change(book);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable long id){
        bookService.remove(id);
    }
}
