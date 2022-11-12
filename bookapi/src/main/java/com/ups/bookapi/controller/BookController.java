package com.ups.bookapi.controller;

import com.ups.bookapi.entity.Author;
import com.ups.bookapi.entity.Book;
import com.ups.bookapi.entity.request.BookDTO;
import com.ups.bookapi.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class BookController {

    @Autowired
    BookService bookService;
    @GetMapping("/book")
    public List<BookDTO> getAllBooks() {
        List<BookDTO> books= new ArrayList<>();
        for(Book book:bookService.getAllBooks()){
            books.add(convertBookToBookDTO(book));
        }
        return books;
    }

    private BookDTO convertBookToBookDTO(Book book){
        BookDTO bookDTO = new BookDTO();
        List<Long> detailsIds = book.getAuthor().stream().map(Author::getId).collect(Collectors.toList());
        BeanUtils.copyProperties(book, bookDTO);
        bookDTO.setAuthorId(detailsIds);
        return bookDTO;
    }


    @PostMapping("/book")
    public BookDTO saveBook(@RequestBody BookDTO bookDTO){
        Book book = new Book(bookDTO.getId(), bookDTO.getTitle(), bookDTO.getYear(),bookDTO.getPublisher());
        List<Long> detailsAuthorId = bookDTO.getAuthorId();

        BookDTO convertedBook;
        try {
            convertedBook = convertBookToBookDTO(bookService.saveBook(book, detailsAuthorId));
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book could not be saved", exception);
        }
        return convertedBook;
    }
}
