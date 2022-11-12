package com.ups.bookapi.service;

import com.ups.bookapi.entity.Author;
import com.ups.bookapi.entity.Book;
import com.ups.bookapi.repository.AuthorRepository;
import com.ups.bookapi.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
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

    @Override
    public Boolean deleteAuthor(Long AuthorId) {
        boolean deleted=false;
        try{
            authorRepository.deleteById(AuthorId);
            deleted=true;
        }catch(Exception e){
            log.error("Error on delete author: {}",e.getMessage());
        }
        return deleted;
    }

    @Override
    public List<Author> getAuthorsByName(String name) {
        List<Author> authorsFiltered = new ArrayList<>(getAllAuthors().stream().filter(author -> name.equals(author.getName())).toList());
        return authorsFiltered;
    }
}
