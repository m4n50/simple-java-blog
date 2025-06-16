package com.blog;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

public class BlogTest {

    @Test
    public void testAddPost() {
        BlogManager blog = new BlogManager();
        BlogPost post = new BlogPost(1, "Hello!","First post", LocalDate.now());
        blog.addPost(post);

        List<BlogPost> posts = blog.getAllPosts();

        assertEquals(1, posts.size());
        assertEquals("Hello!", posts.get(0).getTitle());
        assertEquals("First post", posts.get(0).getContent());
        assertNotNull(posts.get(0).getSummary());//get date
    }

    @Test
    public void testPostToString() {
        BlogPost post = new BlogPost(1, "Title", "Content", LocalDate.now());
        String text = post.toString();

        assertTrue(text.contains("Title"));
        assertTrue(text.contains("Content"));
    }
}
