package com.blog;

import java.time.LocalDate;
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
            System.out.println("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newLine

            switch (choice){
                case 1 -> {
                    System.out.println("Title: ");
                    String title = scanner.nextLine();
                    System.out.println("Content: ");
                    String content = scanner.nextLine();
                    manager.addPost(new BlogPost(nextId++, title, content, LocalDate.now()));
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
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
            scanner.close();
            System.out.println("Goodbye.");
        }
    }
}
