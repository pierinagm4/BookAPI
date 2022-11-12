package com.ups.bookapi.service;


import com.ups.bookapi.entity.Author;
import com.ups.bookapi.entity.Book;
import com.ups.bookapi.repository.AuthorRepository;
import com.ups.bookapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Override
    public Book saveBook(Book book, List<Long> authorsId) {
        List<Author> authors = new ArrayList<>();
        if(authorsId != null && !authorsId.isEmpty()){
            authors = authorsId.stream().map((detailId) -> authorRepository.getOne(detailId)).collect(Collectors.toList());

        }
        book.setAuthor(authors);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

}
