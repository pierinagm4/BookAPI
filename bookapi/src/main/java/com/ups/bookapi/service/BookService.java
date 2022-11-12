package com.ups.bookapi.service;


import com.ups.bookapi.entity.Book;

import java.util.List;

public interface BookService {
    Book saveBook(Book book, List<Long> authorsId);

    List<Book> getAllBooks();
}
