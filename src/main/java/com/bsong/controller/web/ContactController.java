package com.bsong.controller.web;

import com.bsong.dao.impl.ContactDao;
import com.bsong.model.ContactModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/contact")
public class ContactController extends HttpServlet {
    private ContactDao contactDao = new ContactDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");

        req.getRequestDispatcher("/public/contact.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        ContactModel contact = new ContactModel();
        contact.setName(req.getParameter("name"));
        contact.setEmail(req.getParameter("email"));
        contact.setWebsite(req.getParameter("website"));
        contact.setMessage(req.getParameter("message"));

        if (contactDao.addContact(contact)>0){
            resp.sendRedirect(req.getContextPath()+"/contact?msg=1");
        }else {
            resp.sendRedirect(req.getContextPath()+"/contact?err=1");
        }
    }
}
