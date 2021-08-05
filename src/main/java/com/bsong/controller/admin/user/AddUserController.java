package com.bsong.controller.admin.user;

import com.bsong.dao.impl.UserDao;
import com.bsong.model.UserModel;
import com.bsong.util.AuthUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/admin/user/add")
public class AddUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!AuthUtil.checkLogin(req,resp)){
            resp.sendRedirect(req.getContextPath()+"/login");
            return;
        }

        HttpSession session = req.getSession();
        UserModel userLogin = (UserModel) session.getAttribute("userLogin");

        if (!"admin".equals(userLogin.getName())){
            resp.sendRedirect(req.getContextPath()+"/admin/users?err=4");
            return;
        }

        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/admin/user/add.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!AuthUtil.checkLogin(req,resp)){
            resp.sendRedirect(req.getContextPath()+"/login");
            return;
        }
        HttpSession session = req.getSession();
        UserModel userLogin = (UserModel) session.getAttribute("userLogin");

        if (!"admin".equals(userLogin.getName())){
            resp.sendRedirect(req.getContextPath()+"/admin/users?err=4");
            return;
        }
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
