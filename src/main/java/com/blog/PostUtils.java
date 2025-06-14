package com.blog;

public class PostUtils {
    public static String formatTitle(String title) {
        return title == null ? "" : title.trim().toUpperCase();
    }
}
