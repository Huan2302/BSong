package com.bsong.controller.admin.contact;

import com.bsong.dao.impl.ContactDao;
import com.bsong.util.AuthUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/contacts")
public class IndexContactController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!AuthUtil.checkLogin(req,resp)){
            resp.sendRedirect(req.getContextPath()+"/login");
            return;
        }
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");

        req.setAttribute("listcontact",new ContactDao().findAll());
        req.getRequestDispatcher("/admin/contact/index.jsp").forward(req,resp);
    }
}
