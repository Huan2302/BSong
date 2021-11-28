package com.bsong.controller.admin.cat;

import com.bsong.dao.impl.CategoryDao;
import com.bsong.model.CategoryModel;
import com.bsong.padding.PageRequest;
import com.bsong.sort.Sorter;
import com.bsong.util.AuthUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/cats/search")
public class SearchCatController extends HttpServlet {
    private CategoryDao categoryDao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!AuthUtil.checkLogin(req,resp)){
            resp.sendRedirect(req.getContextPath()+"/login");
            return;
        }
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        CategoryModel cat = new CategoryModel();

        String search = req.getParameter("search");
        String pageStr = req.getParameter("page");
        String maxPageitemStr =req.getParameter("maxPageItem");
        String sortBy = req.getParameter("sortBy");
        String sortName = req.getParameter("sortName");

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

        if (sortBy != null){
            cat.setSortBy(sortBy);
        }else {
            cat.setSortBy("DESC");
        }

        if (sortName != null){
            cat.setSortName(sortName);
        }else {
            cat.setSortName("id");
        }

        PageRequest pageRequest = new PageRequest(cat.getPage(),cat.getMaxPageItem(),
                new Sorter(cat.getSortBy(),cat.getSortName()));
        cat.setListResult(categoryDao.findAll(search,pageRequest));
        cat.setTotalItem(categoryDao.getTotalItem(search));
        cat.setTotalPage((int )Math.ceil((double) cat.getTotalItem()/cat.getMaxPageItem()));
        req.setAttribute("categories",cat);
        req.getRequestDispatcher("/admin/cat/search.jsp").forward(req,resp);
    }
}
