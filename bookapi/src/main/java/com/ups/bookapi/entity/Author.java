package com.ups.bookapi.entity;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "author")
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Nationalized
    private String Name;
    @Nationalized
    private String LastName;
    private Date birthday;
    private Integer age;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public Author(String name, String lastName, Date birthday, Integer age) {
        Name = name;
        LastName = lastName;
        this.birthday = birthday;
        this.age = age;
    }

    public Author() {
    }

    public Author(Long id, String name, String lastName, Date birthday, Integer age) {
        this.id = id;
        Name = name;
        LastName = lastName;
        this.birthday = birthday;
        this.age = age;
    }

    public Author(String name, String lastName, Date birthday, Integer age, List<Book> books) {
        Name = name;
        LastName = lastName;
        this.birthday = birthday;
        this.age = age;
        this.books = books;
    }

    public Author(Long id, String name, String lastName, Date birthday, Integer age, List<Book> books) {
        this.id = id;
        Name = name;
        LastName = lastName;
        this.birthday = birthday;
        this.age = age;
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", LastName='" + LastName + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                ", books=" + books +
                '}';
    }
}
