package com.bsong.controller.admin.cat;

import com.bsong.dao.impl.CategoryDao;
import com.bsong.model.CategoryModel;
import com.bsong.util.AuthUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/cat/edit")
public class EditCatController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!AuthUtil.checkLogin(req,resp)){
            resp.sendRedirect(req.getContextPath()+"/login");
            return;
        }
        int id = 0;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        }catch (NumberFormatException e){
            resp.sendRedirect(req.getContextPath()+"/admin/cats");
        }

        CategoryModel cat = new CategoryDao().findId(id);

        if (cat != null){
            req.setAttribute("category",cat);
            req.getRequestDispatcher("/admin/cat/edit.jsp").forward(req,resp);
        }else{
            resp.sendRedirect(req.getContextPath()+"/admin/cats?err=1");
            return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!AuthUtil.checkLogin(req,resp)){
            resp.sendRedirect(req.getContextPath()+"/login");
            return;
        }
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");

        CategoryModel cat = new CategoryModel();
        cat.setName(req.getParameter("name"));
        cat.setId(Integer.parseInt(req.getParameter("id")));

        CategoryDao categoryDao = new CategoryDao();
        categoryDao.updateCat(cat);
        resp.sendRedirect(req.getContextPath()+"/admin/cats?msg=2");
//        if (categoryDao.updateCat(cat)){
//            req.getRequestDispatcher(req.getContextPath()+"/admin/cats?msg=2").forward(req,resp);
//        }
//        else {
//            resp.sendRedirect(req.getContextPath()+"/admin/cat/edit?id="+cat.getId()+"&err=1");
//        }
    }
}
