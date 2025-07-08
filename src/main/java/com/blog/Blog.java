package com.blog;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Blog {
    private static final String SAVE_FILE = "blogposts.txt";

    public static void main(String[] args){
        BlogManager manager = new BlogManager();
        manager.loadFromFile(SAVE_FILE);

        Scanner scanner = new Scanner(System.in);
        int nextId = manager.getAllPosts().size() + 1;

        while(true){
            System.out.println("\n === Real Estate Blog ===");
            System.out.println("1. Add post");
            System.out.println("2. View posts");
            System.out.println("3. Save and Exit");
            System.out.println("4. Export posts as Markdown");
            System.out.println("5. Search posts by category");
            System.out.println("6. Search posts by author");
            System.out.println("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newLine

            switch (choice){
                case 1 -> {
                    System.out.println("Title: ");
                    String title = scanner.nextLine();

                    System.out.println("Content: ");
                    String content = scanner.nextLine();

                    System.out.println("Categories (comma separated): ");
                    String categoriesLine = scanner.nextLine();
                    List<String> categories = Arrays.asList(categoriesLine.split("\\s*,\\s*"));

                    System.out.println("Author name: ");
                    String authorName = scanner.nextLine();

                    System.out.println("Author email: ");
                    String authorEmail = scanner.nextLine();

                    Author author = new Author(authorName, authorEmail);

                    manager.addPost(new BlogPost(nextId++, title, content, LocalDate.now(),author, categories));
                    System.out.println("Post added.");
                }
                case 2 -> {
                    System.out.println("\nAll Posts:");
                    for (BlogPost post : manager.getAllPosts())
                        System.out.println(post + "\n ---- ");
                }
                case 3 ->{
                    manager.saveToFile(SAVE_FILE);
                    System.out.println("Post saved. Goodbye!");
                    scanner.close();
                    return;
                }
                case 4 ->{
                    for(BlogPost post : manager.getAllPosts()){
                        String filename = "post-" + post.getId() + ".md";
                        manager.exportPostAsMarkdown(post, filename);
                    }
                    System.out.println("All posts exported as Markdown files.");
                }
                case 5 ->{
                    System.out.println("Enter the category to search: ");
                    String category = scanner.nextLine();
                    List<BlogPost> filtered = manager.getPostsByCategory(category);
                    if(filtered.isEmpty())
                        System.out.println("No posts found for category: " + category);
                    for(BlogPost post : filtered)
                        System.out.println(post + "\n -----");
                }
                case 6 ->{
                    System.out.println("Enter author name to search: ");
                    String authorName = scanner.nextLine();
                    List<BlogPost> filtered = manager.getPostsByAuthor(authorName);
                    if(filtered.isEmpty())
                        System.out.println("No posts found for author: " + authorName);
                    for(BlogPost post : filtered)
                        System.out.println(post + "\n -----");
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
