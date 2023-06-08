package com.dyp.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author howard
 * @version 1.0
 */
public class JdbcUtils {

    // 数据库连接池
    private static DruidDataSource druidDataSource;

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        try {
            // 获取数据库连接池配置信息
            Properties properties = new Properties();
            InputStream ins = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(ins);

            // 初始化数据库连接池对象
            druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池中的连接
     * 如果获取成功则返回Connection对象，如果获取失败则返回null
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = threadLocal.get();
        if (conn == null) {
            try {
                conn = druidDataSource.getConnection(); // 从数据库连接池中获取连接
                threadLocal.set(conn); // 保存到threadLocal对象，供整个事务过程使用
                conn.setAutoCommit(false); // 设置为手动提交
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务，并关闭释放连接
     */
    public static void commitAndClose() {
        Connection conn = threadLocal.get();
        // 如果当前有事务，则要提交事务并关闭事务对应连接
        if (conn != null) {
            try {
                conn.commit();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        threadLocal.remove();
    }

    /**
     * 回滚事务，并关闭释放连接
     */
    public static void rollbackAndClose() {
        Connection conn = threadLocal.get();
        // 如果当前有事务，则要回滚事务并关闭事务对应连接
        if (conn != null) {
            try {
                conn.rollback();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        threadLocal.remove();
    }
}
