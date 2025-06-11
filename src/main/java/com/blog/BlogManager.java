package com.blog;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class BlogManager {
    private List<BlogPost> posts = new ArrayList<>();

    public void addPost(BlogPost post){
        posts.add(post);
    }

    public List<BlogPost> getAllPosts(){
        return posts;
    }

    public void saveToFile(String filename){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            for(BlogPost post : posts){
                writer.write(post.getID() + ";" + post.getSummary() + ";" + post.toString().replace("\n", "\\n"));
                writer.newLine();
            }

        } catch (IOException e){
            System.out.println("Error saving Blog posts: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename){
        posts.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            int id = 1;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(";", 4);
                if(parts.length == 4){
                    String title = parts[1];
                    String content = parts[3].replace("\\n", "\n");
                    posts.add(new BlogPost(id++, title, content, LocalDate.now()));
                }
            }
        } catch (IOException e){
            System.out.println("No saved posts found. Starting fresh");
        }
    }
}
