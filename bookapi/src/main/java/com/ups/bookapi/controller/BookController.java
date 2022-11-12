package com.ups.bookapi.controller;

import com.ups.bookapi.entity.Author;
import com.ups.bookapi.entity.Book;
import com.ups.bookapi.entity.request.BookDTO;
import com.ups.bookapi.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/book")
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    private BookDTO convertUserToUserDTO(Book book){
        BookDTO bookDTO = new BookDTO();
        List<Long> detailsIds = book.getAuthor().stream().map(Author::getId).collect(Collectors.toList());
        BeanUtils.copyProperties(book, bookDTO);
        bookDTO.setAuthorId(detailsIds);
        return bookDTO;
    }
}
