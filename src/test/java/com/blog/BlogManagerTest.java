package com.blog;

import org.junit.jupiter.api.*;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BlogManagerTest {

    private static final String TEST_FILE = "test-blogposts.json";
    private BlogManager manager;

    @BeforeEach
    public void setUp(){
        manager = new BlogManager();
        File file = new File(TEST_FILE);
        if(file.exists()){
            file.delete();
        }
    }

    @Test
    public void testSaveAndLoadPost(){
        BlogPost post1 = new BlogPost(1, "Test title 1", "Test content 1",LocalDate.now());
        BlogPost post2 = new BlogPost(2, "Test title 2", "Test content 2", LocalDate.now());

        manager.addPost(post1);
        manager.addPost(post2);
        manager.saveToFile(TEST_FILE);

        BlogManager loadedManager = new BlogManager();
        loadedManager.loadFromFile(TEST_FILE);
        List<BlogPost> loadPosts = loadedManager.getAllPosts();

        assertEquals(2, loadPosts.size());
        assertEquals("Test title 1", loadPosts.get(0).getTitle());
        assertEquals("Test content 2", loadPosts.get(1).getContent());
        assertNotNull(loadPosts.get(0).getDate());
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE);
        if(file.exists()){
            file.delete();
        }
    }
}
