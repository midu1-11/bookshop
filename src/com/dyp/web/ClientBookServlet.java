package com.dyp.web;

import com.dyp.pojo.Book;
import com.dyp.pojo.Page;
import com.dyp.service.BookService;
import com.dyp.service.impl.BookServiceImpl;
import com.dyp.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author howard
 * @version 1.0
 */
public class ClientBookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = req.getParameter("pageNo") == null ? 1 : Integer.parseInt(req.getParameter("pageNo"));
        int pageSize = req.getParameter("pageSize") == null ? 4 : Integer.parseInt(req.getParameter("pageSize"));

        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/bookServlet?action=page");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = req.getParameter("pageNo") == null ? 1 : Integer.parseInt(req.getParameter("pageNo"));
        int pageSize = req.getParameter("pageSize") == null ? 4 : Integer.parseInt(req.getParameter("pageSize"));
        int min = WebUtils.parseInt(req.getParameter("min"),0);
        int max = WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);

        Page page = bookService.pageByPrice(pageNo, pageSize, max, min);
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        if(req.getParameter("min")!=null) {
            sb.append("&min=").append(req.getParameter("min"));
        }
        if(req.getParameter("max")!=null) {
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
