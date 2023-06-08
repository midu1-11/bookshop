package com.dyp.filter;

import com.dyp.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author howard
 * @version 1.0
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            e.printStackTrace();
            JdbcUtils.rollbackAndClose();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
