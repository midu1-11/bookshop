package com.dyp.dao;

import com.dyp.pojo.Book;

import java.util.List;

public interface BookDao {
    /**
     * 添加一条图书记录
     * @param book
     * @return 添加成功返回1，添加失败返回-1
     */
    public int addBook(Book book);

    /**
     * 删除指定id的图书记录
     * @param id
     * @return 删除成功返回1，删除失败返回-1
     */
    public int deleteBookById(Integer id);

    /**
     * 更新一条图书记录
     * @param book
     * @return 更新成功返回1，失败返回-1
     */
    public int updateBook(Book book);

    /**
     * 根据id查询一条图书记录
     * @param id
     * @return 成功返回Book，失败返回null
     */
    public Book queryBookById(Integer id);

    /**
     * 查询所有图书记录
     * @return 成功返回List<Book>，失败返回null
     */
    public List<Book> queryBooks();

    /**
     * 查询图书记录个数
     * @return 返回数据库中图书记录个数
     */
    public Integer queryForPageTotalCount();

    /**
     * 查询某一页的所有Book对象
     * @param begin
     * @param pageSize
     * @return 成功返回List<Book>，失败返回null
     */
    public List<Book> queryForPageItems(int begin, int pageSize);

    /**
     * 查询(min,max)区间内的图书记录个数
     * @param max
     * @param min
     * @return (min,max)区间内的图书记录个数
     */
    public Integer queryForPageTotalCountByPrice(double max,double min);


    /**
     * 查询(min,max)区间内，查询某一页的所有Book对象
     * @param begin
     * @param pageSize
     * @param max
     * @param min
     * @return 成功返回List<Book>，失败返回null
     */
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, double max, double min);
}
