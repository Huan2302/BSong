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

@WebServlet("/admin/cats")
public class IndexCatController extends HttpServlet {
    private CategoryDao categoryDao = new CategoryDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!AuthUtil.checkLogin(req,resp)){
            resp.sendRedirect(req.getContextPath()+"/login");
            return;
        }
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");

        CategoryModel cat = new CategoryModel();

        String pageStr = req.getParameter("page");
        String maxPageitemStr =req.getParameter("maxPageItem");
        if(pageStr != null){
            cat.setPage(Integer.parseInt(pageStr));
        }else {
            cat.setPage(1);
        }
        if (maxPageitemStr != null){
            cat.setMaxPageItem(Integer.parseInt(maxPageitemStr));
        }else {
            cat.setMaxPageItem(4);
        }

        Integer offset = (cat.getPage() - 1)*cat.getMaxPageItem();
        cat.setListResult(categoryDao.findAll(offset,cat.getMaxPageItem()));

        cat.setTotalItem(categoryDao.getTotalItem());
        cat.setTotalPage((int )Math.ceil((double) cat.getTotalItem()/cat.getMaxPageItem()));
        req.setAttribute("categories",cat);
        req.getRequestDispatcher("/admin/cat/index.jsp").forward(req,resp);
    }
}
