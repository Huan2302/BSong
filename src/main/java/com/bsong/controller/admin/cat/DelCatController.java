package com.bsong.controller.admin.cat;

import com.bsong.dao.impl.CategoryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/cat/del")
public class DelCatController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        }catch (NumberFormatException e){
            resp.sendRedirect(req.getContextPath()+"/admin/cats");
        }
        if (new CategoryDao().del(id)>0){
            resp.sendRedirect(req.getContextPath()+"/admin/cats?msg=3");
        }else {
            resp.sendRedirect(req.getContextPath()+"/admin/cats?err=3");
        }
    }
}
