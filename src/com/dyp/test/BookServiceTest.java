package com.dyp.test;

import com.dyp.service.BookService;
import com.dyp.service.impl.BookServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookServiceTest {
    BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
    }

    @Test
    public void deleteBookById() {
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void queryBookById() {
    }

    @Test
    public void queryBooks() {
    }

    @Test
    public void page() {
        System.out.println(bookService.page(1,4));
    }
}