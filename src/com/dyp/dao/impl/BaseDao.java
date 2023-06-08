package com.dyp.dao.impl;

import com.dyp.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.util.List;

/**
 * @author howard
 * @version 1.0
 */
public class BaseDao {
    private QueryRunner qr = new QueryRunner();

    /**
     * 用于执行增删改操作
     *
     * @param sql
     * @param args
     * @return 成功则返回影响行数，失败则返回-1
     */
    public int update(String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return qr.update(conn, sql, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    /**
     * 单条查询
     *
     * @param type
     * @param sql
     * @param args
     * @param <T>
     * @return 成功则返回javabean对象，失败则返回null
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return qr.query(conn, sql, new BeanHandler<T>(type), args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }


    /**
     * 多条查询
     *
     * @param type
     * @param sql
     * @param args
     * @param <T>
     * @return 成功则返回javabean的list，失败则返回null
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return qr.query(conn, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    /**
     * 查询单个值
     *
     * @param sql
     * @param args
     * @return 成功则返回单个值，失败则返回null
     */
    public Object queryForSingleValue(String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return qr.query(conn, sql, new ScalarHandler(), args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }
}
