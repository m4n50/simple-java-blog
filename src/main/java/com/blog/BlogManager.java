package com.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.util.*;

public class BlogManager {
    private List<BlogPost> posts = new ArrayList<>();
    private final ObjectMapper mapper;

    public BlogManager(){
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public void addPost(BlogPost post){
        posts.add(post);
    }

    public List<BlogPost> getAllPosts(){
        return posts;
    }

    public void saveToFile(String filename){
        try {
            mapper.writeValue(new File(filename), posts);
        } catch (IOException e){
            System.out.println("Error saving Blog posts: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename){
        File file = new File(filename);
        if(!file.exists()){
            System.out.println("No saved posts found. Starting fresh");
            return;
        }

        try {
            BlogPost[] loadedPosts = mapper.readValue(file, BlogPost[].class);
            posts = new ArrayList<>(Arrays.asList(loadedPosts));
        }catch (IOException e){
            System.out.println("Error loading blog posts: " + e.getMessage());
        }
    }

    public void exportPostAsMarkdown(BlogPost post, String filename){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            writer.write("# " + post.getTitle() + "\n\n");
            writer.write("**Date:** " + post.getDate() + "\n\n");
            writer.write("**Content:** " +post.getContent() + "\n\n");

            if(post.getAuthor() != null)
                writer.write("**Author:** " + post.getAuthor().getName() + "\n (" + post.getAuthor().getEmail() + ")\n\n");

            List<String> categories = post.getCategories();
            if(categories != null && !categories.isEmpty())
                writer.write("**Categories:** " + String.join(", ", categories) + "\n\n");

            System.out.println("Post exported as markdown to " + filename);
        } catch(IOException e){
            System.out.println("Error exporting post as markdown " + e.getMessage());
        }
    }

    public List<BlogPost> getPostsByCategory(String category){
        List<BlogPost> result = new ArrayList<>();
        for(BlogPost post : posts){
            List<String> cats = post.getCategories();// null category "cats"
            if (cats != null && cats.contains(category))
                result.add(post);
        }
        return result;
    }

    public List<BlogPost> getPostsByAuthor(String authorName){
        List<BlogPost> result = new ArrayList<>();
        for(BlogPost post : posts)
            if(post.getAuthor() != null && post.getAuthor().getName().equalsIgnoreCase(authorName))
                result.add(post);
        return result;
    }
}
