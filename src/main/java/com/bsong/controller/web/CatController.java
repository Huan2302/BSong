package com.bsong.controller.web;

import com.bsong.dao.impl.CategoryDao;
import com.bsong.dao.impl.SongDao;
import com.bsong.model.CategoryModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/cat")
public class CatController extends HttpServlet {
    private SongDao songDao = new SongDao();
    private CategoryDao categoryDao = new CategoryDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");

        int id = 0;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        }catch (NumberFormatException e){
            resp.sendRedirect(req.getContextPath()+"/cat?err=1");
            return;
        }

        CategoryModel cat = categoryDao.findId(id);
        if (cat == null){
            resp.sendRedirect(req.getContextPath()+"/cat?err=1");
            return;
        }

        req.setAttribute("catName",cat);
        req.setAttribute("catId",songDao.findCatId(id));
        req.getRequestDispatcher("/public/cat.jsp").forward(req,resp);
    }
}
