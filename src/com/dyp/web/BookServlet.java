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
import java.util.List;

/**
 * @author howard
 * @version 1.0
 */
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookService.addBook(WebUtils.copyParamToBean(req.getParameterMap(), new Book()));
        // 增添一条图书后自动跳转到末页
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=0");
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        bookService.deleteBookById(Integer.parseInt(req.getParameter("id")));
        // 删除一条图书后返回原页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        bookService.updateBook(book);
        // 更新一条图书后返回原页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = bookService.queryBookById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("book", book);
        req.setAttribute("pageNo",Integer.parseInt(req.getParameter("pageNo")));
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> list = bookService.queryBooks();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = req.getParameter("pageNo") == null ? 1 : Integer.parseInt(req.getParameter("pageNo"));
        int pageSize = req.getParameter("pageSize") == null ? 4 : Integer.parseInt(req.getParameter("pageSize"));

        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("manager/bookServlet?action=page");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
