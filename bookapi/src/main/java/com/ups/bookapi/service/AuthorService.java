package com.ups.bookapi.service;


import com.ups.bookapi.entity.Author;

import java.util.List;

public interface AuthorService {
    Author authorSave(Author author, List<Long> bookId);
    List<Author> getAllAuthors();
    Boolean deleteAuthor(Long AuthorId);
    List<Author> getAuthorsByName(String name);
}
