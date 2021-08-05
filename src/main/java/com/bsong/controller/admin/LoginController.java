package com.bsong.controller.admin;

import com.bsong.dao.impl.UserDao;
import com.bsong.model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/login")
public class LoginController extends HttpServlet {
    private UserDao userDao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        UserModel user = new UserModel();
        user.setName(req.getParameter("username"));
        user.setPass(req.getParameter("pass"));

        //check login user & pass
        UserModel checkLogin = userDao.checkLogin(user);
        if (checkLogin!= null){
            session.setAttribute("userLogin",checkLogin);
            resp.sendRedirect(req.getContextPath()+"/admin");
        }else {
            resp.sendRedirect(req.getContextPath()+"/login?err=1");
            return;
        }
    }
}
