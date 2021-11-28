package com.bsong.controller.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@WebServlet(value = "/xulysort")
public class SortController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");
        String page = req.getParameter("page");
        String maxPageItem = req.getParameter("maxPageItem");
        String sortBy = req.getParameter("sortBy");
        String sortName = req.getParameter("sortName");
        System.out.println(sortName);

        String name = req.getParameter("name");
        System.out.println(name);
        if (name != null && name.equals("cname")){
            name = "/cats";
        }else if (name != null && name.equals("sname")){
            name = "/songs";
        }
        if (sortName.equals("id")&&sortBy.toUpperCase(Locale.ROOT).equals("DESC")){
            resp.sendRedirect(req.getContextPath()+"/admin"+name+"?page="+page+"&maxPageItem="+maxPageItem+"&sortBy=ASC&sortName="+sortName);
        }else if (sortName.equals("id")&&sortBy.toUpperCase(Locale.ROOT).equals("ASC")){
            resp.sendRedirect(req.getContextPath()+"/admin"+name+"?page="+page+"&maxPageItem="+maxPageItem+"&sortBy=DESC&sortName="+sortName);
        }else if (sortName.equals("name")&&sortBy.toUpperCase(Locale.ROOT).equals("DESC")){
            resp.sendRedirect(req.getContextPath()+"/admin"+name+"?page="+page+"&maxPageItem="+maxPageItem+"&sortBy=ASC&sortName="+sortName);
        }else if (sortName.equals("name")&&sortBy.toUpperCase(Locale.ROOT).equals("ASC")){
            resp.sendRedirect(req.getContextPath()+"/admin"+name+"?page="+page+"&maxPageItem="+maxPageItem+"&sortBy=DESC&sortName="+sortName);
        }else if (sortName.equals("cat_id")&&sortBy.toUpperCase(Locale.ROOT).equals("DESC")){
            resp.sendRedirect(req.getContextPath()+"/admin"+name+"?page="+page+"&maxPageItem="+maxPageItem+"&sortBy=ASC&sortName="+sortName);
        }else if (sortName.equals("cat_id")&&sortBy.toUpperCase(Locale.ROOT).equals("ASC")){
            resp.sendRedirect(req.getContextPath()+"/admin"+name+"?page="+page+"&maxPageItem="+maxPageItem+"&sortBy=DESC&sortName="+sortName);
        }else if (sortName.equals("counter")&&sortBy.toUpperCase(Locale.ROOT).equals("DESC")){
            resp.sendRedirect(req.getContextPath()+"/admin"+name+"?page="+page+"&maxPageItem="+maxPageItem+"&sortBy=ASC&sortName="+sortName);
        }else if (sortName.equals("counter")&&sortBy.toUpperCase(Locale.ROOT).equals("ASC")){
            resp.sendRedirect(req.getContextPath()+"/admin"+name+"?page="+page+"&maxPageItem="+maxPageItem+"&sortBy=DESC&sortName="+sortName);
        }
    }
}
