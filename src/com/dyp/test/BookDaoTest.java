package com.dyp.test;

import com.dyp.dao.BookDao;
import com.dyp.dao.impl.BookDaoImpl;
import com.dyp.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookDaoTest {
    BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"hhhh","howard",new BigDecimal(19.8),15,12,""));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(20);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21,"yoyo","howard",new BigDecimal(1921842.8),15,12,""));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(19));
    }

    @Test
    public void queryBooks() {
        System.out.println(bookDao.queryBooks());
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        System.out.println(bookDao.queryForPageItems(0,4));
    }
}