package com.ups.bookapi.entity.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuthorDTO {

    private Long id;
    private String Name;
    private String LastName;
    private Date birthday;
    private Integer age;
    private List<Long> bookId= new ArrayList<>();;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Long> getBookId() {
        return bookId;
    }

    public void setBookId(List<Long> bookId) {
        this.bookId = bookId;
    }
    public void addBook(Long bookId){
        this.bookId.add(bookId);
    }
    @Override
    public String toString() {
        return "AuthorDTO{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", LastName='" + LastName + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                ", bookId=" + bookId +
                '}';
    }
}
