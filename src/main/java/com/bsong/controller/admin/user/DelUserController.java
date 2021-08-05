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

@WebServlet(value = "/admin/user/del")
public class DelUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!AuthUtil.checkLogin(req,resp)){
            resp.sendRedirect(req.getContextPath()+"/login");
            return;
        }
        int id = 0;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        }catch (NumberFormatException e){
            resp.sendRedirect(req.getContextPath()+"/admin/users?err=1");
        }

        HttpSession session = req.getSession();
        UserModel userLogin = (UserModel) session.getAttribute("userLogin");

        UserModel user = new UserDao().findId(id);
        if ("admin".equals(user.getName())){
            resp.sendRedirect(req.getContextPath()+"/admin/users?err=4");
            return;
        }else {
            if("admin".equals(userLogin.getName())){
                new UserDao().del(id);
                resp.sendRedirect(req.getContextPath()+"/admin/users?dell=1");
            }else {

            }
        }
    }
}
