package com.bsong.controller.admin.cat;

import com.bsong.dao.impl.CategoryDao;
import com.bsong.model.CategoryModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/cat/add")
public class AddCatController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/admin/cat/add.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");

        CategoryModel cat = new CategoryModel();
        cat.setName(req.getParameter("name"));
        CategoryDao categoryDao = new CategoryDao();
        if (categoryDao.addCat(cat)>0){
            resp.sendRedirect(req.getContextPath()+"/admin/cats?msg=1");
        }
        else {
            resp.sendRedirect(req.getContextPath()+"/admin/cat/add?err=1");
        }
    }
}
