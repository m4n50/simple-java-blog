package com.blog;

import java.time.LocalDate;

public class BlogPost {
    private final int ID;
    private String title;
    private String content;
    private final LocalDate DATE;

    public BlogPost(int id, String title, String content, LocalDate date){
        this.ID = id;
        this.title = title;
        this.content = content;
        this.DATE = date;
    }

    public String getSummary(){
        return DATE + " (" + DATE + ")";
    }

    public String toString(){
        return "Post ID: " + ID + "\nTitle: " + title + "\nDate: " + DATE + "\nContent: \n" + content;
    }

    public int getID(){
        return ID;
    }

}
