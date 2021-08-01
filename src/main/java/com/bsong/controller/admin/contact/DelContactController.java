package com.bsong.controller.admin.contact;

import com.bsong.dao.impl.ContactDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/contacts/del")
public class DelContactController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");

        int id = 0;
        try{
            id = Integer.parseInt(req.getParameter("id"));
            new ContactDao().delContact(id);
            resp.sendRedirect(req.getContextPath()+"/admin/contacts?msg=1");
        }catch (NumberFormatException e){
            resp.sendRedirect(req.getContextPath()+"/admin/contacts?err=1");
        }
    }
}
