package com.blog;

import java.time.LocalDate;
import java.util.*;

public class BlogPost {
    private int id;
    private String title;
    private String content;
    private LocalDate date;
    private Author author;
    private List<String> categories = new ArrayList<>();

    public BlogPost(){} // Required for Jackson

    public BlogPost(int id, String title, String content, LocalDate date, Author author, List<String> categories){
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.author = author;
        this.categories = categories;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public LocalDate getDate(){
        return date;
    }

    public Author getAuthor(){
        return author;
    }

    public List<String> getCategories(){
        return categories != null ? categories : new ArrayList<>();
    }

    public String toString(){
        return "Post ID: " + id +
                "\nTitle: " + title +
                "\nDate: " + date +
                "\nAuthor: " + author +
                "\nCategories: " + categories +
                "\nContent: \n" + content;
    }
}
