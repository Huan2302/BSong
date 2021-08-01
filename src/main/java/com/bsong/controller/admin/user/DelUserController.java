package com.bsong.controller.admin.user;

import com.bsong.dao.impl.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/user/del")
public class DelUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        }catch (NumberFormatException e){
            resp.sendRedirect(req.getContextPath()+"/admin/users?err=1");
        }

        new UserDao().del(id);
        resp.sendRedirect(req.getContextPath()+"/admin/users?dell=1");
    }
}
