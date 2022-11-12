package com.ups.bookapi.service;

import com.ups.bookapi.entity.Author;
import com.ups.bookapi.entity.Book;
import com.ups.bookapi.repository.AuthorRepository;
import com.ups.bookapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public Author authorSave(Author author, List<Long> bookId) {
        List<Book> bookList = bookRepository.findAllById(bookId);

        if(!bookList.isEmpty() && bookList!=null) {
            for(Book book:bookList){
                book.addAuthor(author);
            }
            author.setBooks(bookList);
        }

        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}
