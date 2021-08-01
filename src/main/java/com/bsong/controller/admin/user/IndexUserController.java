package com.bsong.controller.admin.user;

import com.bsong.dao.impl.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/users")
public class IndexUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");

        req.setAttribute("users",new UserDao().findAll());
        req.getRequestDispatcher("/admin/user/index.jsp").forward(req,resp);
    }
}
