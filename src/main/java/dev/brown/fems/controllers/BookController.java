package dev.brown.fems.controllers;

import dev.brown.fems.clients.BookClient;
import dev.brown.fems.models.Book;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookClient bookClient;

    @GetMapping("/books/{id}")
    ResponseEntity<Book> getBookById(@PathVariable int id){
        try{
            Book book = bookClient.getBookById(id);
            return ResponseEntity.status(200).body(book);
        }catch(FeignException fei){
            throw new ResponseStatusException(HttpStatus.valueOf(fei.status()),fei.getMessage());
        }
    }

    @GetMapping("/books")
    ResponseEntity<List<Book>> getBooks(@RequestParam(name="author",required=false) String author,
                                        @RequestParam(name="title",required = false) String title,
                                        @RequestParam(name="condition",required = false) int condition) {
        try {
            List<Book> books = bookClient.getBooks(author, title, condition);
            return ResponseEntity.status(HttpStatus.OK).body(books);
        } catch (FeignException fei) {
            throw new ResponseStatusException(HttpStatus.valueOf(fei.status()), fei.getMessage());
        }
    }
}
