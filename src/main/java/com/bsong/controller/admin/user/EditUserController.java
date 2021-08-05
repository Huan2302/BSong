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

@WebServlet(value = "/admin/user/edit")
public class EditUserController extends HttpServlet {
    private UserDao userDao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!AuthUtil.checkLogin(req,resp)){
            resp.sendRedirect(req.getContextPath()+"/login");
            return;
        }
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");

        int id =0;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        }catch (NumberFormatException e){
            resp.sendRedirect(req.getContextPath()+"/admin/users?err=1");
        }

        HttpSession session = req.getSession();
        UserModel userLogin = (UserModel) session.getAttribute("userLogin");
        if ("admin".equals(userLogin.getName()) || (id == userLogin.getId())){
            req.setAttribute("user",new UserDao().findId(id));
            req.getRequestDispatcher("/admin/user/edit.jsp").forward(req,resp);
        }else {
            resp.sendRedirect(req.getContextPath()+"/admin/users?err=4");
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

        int id =0;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        }catch (NumberFormatException e){
            resp.sendRedirect(req.getContextPath()+"/admin/users?err=1");
        }

        HttpSession session = req.getSession();
        UserModel userLogin = (UserModel) session.getAttribute("userLogin");
        if ("admin".equals(userDao.findId(id).getName()) || (id == userLogin.getId())){
            UserModel user = new UserModel();
            user.setId(id);
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
        }else {
            resp.sendRedirect(req.getContextPath()+"/admin/users?err=4");
            return;
        }
    }
}
