package com.dyp.dao.impl;


import com.dyp.dao.BookDao;
import com.dyp.pojo.Book;

import java.util.List;

/**
 * @author howard
 * @version 1.0
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImg_path());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id=?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id=?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImg_path(), book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select * from t_book where id = ?";
        return queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select * from t_book";
        return queryForList(Book.class, sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        long count = (Long) queryForSingleValue(sql);
        return (int) count;
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select * from t_book limit ?,?";
        return queryForList(Book.class, sql, begin, pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(double max, double min) {
        String sql = "select count(*) from t_book where price between ? and ?";
        long count = (Long) queryForSingleValue(sql, min, max);
        return (int) count;
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, double max, double min) {
        String sql = "select * from t_book where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class, sql, min, max, begin, pageSize);
    }
}
