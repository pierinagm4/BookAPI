package com.ups.bookapi.entity.request;

import java.util.ArrayList;
import java.util.List;


public class BookDTO {
    private Long id;
    private String title;
    private List<Long> authorId = new ArrayList<>();;
    private Integer year;
    private String publisher;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Long> getAuthorId() {
        return authorId;
    }

    public void setAuthorId(List<Long> authorId) {
        this.authorId = authorId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorId=" + authorId +
                ", year=" + year +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
