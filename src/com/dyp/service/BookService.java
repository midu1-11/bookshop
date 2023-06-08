package com.dyp.service;

import com.dyp.pojo.Book;
import com.dyp.pojo.Page;

import java.util.List;

public interface BookService {
    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public Page<Book> page(int pageNo, int pageSize);

    public Page<Book> pageByPrice(int pageNo, int pageSize, double max, double min);
}
