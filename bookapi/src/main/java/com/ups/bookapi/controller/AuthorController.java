package com.ups.bookapi.controller;

import com.ups.bookapi.entity.Author;
import com.ups.bookapi.entity.Book;
import com.ups.bookapi.entity.request.AuthorDTO;
import com.ups.bookapi.entity.request.BookDTO;
import com.ups.bookapi.service.AuthorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @GetMapping("/author")
    public List<AuthorDTO> getAllAuthor() {
        List<AuthorDTO> authors= new ArrayList<>();
        for(Author author:authorService.getAllAuthors()){
            authors.add(convertAuthorToAuthorDTO(author));
        }
        return authors;
    }
    @GetMapping("/author/{name}")
    public List<AuthorDTO> getAuthorsByName(@PathVariable String name) {
        List<AuthorDTO> authors= new ArrayList<>();
        for(Author author:authorService.getAuthorsByName(name)){
            authors.add(convertAuthorToAuthorDTO(author));
        }
        return authors;
    }
    @PostMapping("/author")
    public AuthorDTO saveAuthor(@RequestBody AuthorDTO authorDTO) {
        Author author = new Author(authorDTO.getId(),authorDTO.getName(),authorDTO.getLastName(),authorDTO.getBirthday(),authorDTO.getAge());
        AuthorDTO convertedAuthor;
        try {
            convertedAuthor = convertAuthorToAuthorDTO(authorService.authorSave(author, authorDTO.getBookId()));
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author could not be saved", exception);
        }
        return convertedAuthor;
    }
    @DeleteMapping("/author/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id){
        if(!authorService.deleteAuthor(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author could not be deleted");
        }
        return ResponseEntity.ok().build();
    }
    private AuthorDTO convertAuthorToAuthorDTO(Author author){
        AuthorDTO authorDTO = new AuthorDTO();
        BeanUtils.copyProperties(author, authorDTO);
        if(author.getBooks()!=null){
            for(Book book:author.getBooks()){
                authorDTO.addBook(book.getId());
            }

        }

        return authorDTO;
    }

}
