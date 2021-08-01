package com.bsong.controller.web;

import com.bsong.dao.impl.SongDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/home")
public class HomeController extends HttpServlet {
    private SongDao song = new SongDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");

        req.setAttribute("song",song.findAll());

        req.getRequestDispatcher("/public/index.jsp").forward(req,resp);
    }
}
