package com.bsong.controller.admin.cat;

import com.bsong.dao.impl.CategoryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/cats")
public class IndexCatController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");

        req.setAttribute("categories",new CategoryDao().findAll());
        req.getRequestDispatcher("/admin/cat/index.jsp").forward(req,resp);
    }
}
