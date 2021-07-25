package com.bsong.controller.admin.user;

import com.bsong.dao.UserDao;
import com.bsong.model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/user/edit")
public class EditUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");

        int id =0;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        }catch (NumberFormatException e){
            resp.sendRedirect(req.getContextPath()+"/admin/users?err=1");
        }

        req.setAttribute("user",new UserDao().findId(id));
        req.getRequestDispatcher("/admin/user/edit.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");

        UserModel user = new UserModel();
        user.setId(Integer.parseInt(req.getParameter("id")));
        if (!req.getParameter("name").equals("")){
            user.setName(req.getParameter("name"));
        }
        if (!req.getParameter("password").equals("")){
            user.setPass(req.getParameter("password"));
        }
        if (!req.getParameter("fullname").equals("")){
            user.setFullName(req.getParameter("fullname"));
        }
        UserDao userDao = new UserDao();
        try{
            userDao.updateUser(user);
            resp.sendRedirect(req.getContextPath()+"/admin/users?msg=2");
        }catch (Exception e){
            resp.sendRedirect(req.getContextPath()+"/admin/user/edit?err=1");
        }
    }
}
