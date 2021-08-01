package com.bsong.controller.admin.user;

import com.bsong.dao.impl.UserDao;
import com.bsong.model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/user/add")
public class AddUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/admin/user/add.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");

        UserModel user = new UserModel();
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
            if (userDao.addUser(user)>0){
                resp.sendRedirect(req.getContextPath()+"/admin/users?msg=1");
            }
        }catch (Exception e){
            resp.sendRedirect(req.getContextPath()+"/admin/user/add?err=1");
        }
//        if (userDao.addUser(user)>0){
//            resp.sendRedirect(req.getContextPath()+"/admin/users?msg=1");
//        }
//        else {
//
//        }
    }
}
