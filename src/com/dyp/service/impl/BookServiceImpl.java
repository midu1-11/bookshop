package com.dyp.service.impl;

import com.dyp.dao.BookDao;
import com.dyp.dao.impl.BookDaoImpl;
import com.dyp.pojo.Book;
import com.dyp.pojo.Page;
import com.dyp.service.BookService;

import java.util.List;

/**
 * @author howard
 * @version 1.0
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();

        page.setPageSize(pageSize);
        page.setPageTotalCount(bookDao.queryForPageTotalCount());
        page.setPageTotal(page.getPageTotalCount() % pageSize > 0 ? page.getPageTotalCount() / pageSize + 1 : page.getPageTotalCount() / pageSize);
        if (pageNo > page.getPageTotal() || pageNo == 0) {
            pageNo = page.getPageTotal();
        } else if (pageNo < 0) {
            pageNo = 1;
        }
        page.setPageNo(pageNo);
        page.setItems(bookDao.queryForPageItems((pageNo - 1) * pageSize, pageSize));

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, double max, double min) {
        Page<Book> page = new Page<>();

        page.setPageSize(pageSize);
        page.setPageTotalCount(bookDao.queryForPageTotalCountByPrice(max, min));
        page.setPageTotal(page.getPageTotalCount() % pageSize > 0 ? page.getPageTotalCount() / pageSize + 1 : page.getPageTotalCount() / pageSize);
//        if(pageNo > page.getPageTotal()||pageNo==0) {
//            pageNo = page.getPageTotal();
//        } else if(pageNo < 0) {
//            pageNo = 1;
//        }
        page.setPageNo(pageNo);
        page.setItems(bookDao.queryForPageItemsByPrice((pageNo - 1) * pageSize, pageSize, max, min));

        return page;
    }
}
