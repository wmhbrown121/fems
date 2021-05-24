package dev.brown.fems.clients;

import dev.brown.fems.models.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="book-crud", url="http://localhost:8080")
public interface BookClient {

    @GetMapping("/books/{id}")
    Book getBookById(@PathVariable int id);

    @GetMapping("/books")
    List<Book> getBooks(@RequestParam(name="author",required=false) String author,
                        @RequestParam(name="title",required = false) String title,
                        @RequestParam(name="condition",required = false) int condition);

}
