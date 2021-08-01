package com.bsong.controller.admin.song;

import com.bsong.dao.ISongDao;
import com.bsong.dao.impl.SongDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/songs")
public class IndexSongController extends HttpServlet {

    private SongDao songDao = new SongDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");

        req.setAttribute("songs",songDao.findAll());
        req.getRequestDispatcher("/admin/song/index.jsp").forward(req,resp);
    }
}
