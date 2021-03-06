package com.bsong.controller.web;

import com.bsong.dao.impl.SongDao;
import com.bsong.model.SongModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/detail")
public class DetailController extends HttpServlet {
    private SongDao songDao = new SongDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");

        int id = 0;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        }catch (NumberFormatException e){
            resp.sendRedirect(req.getContextPath()+"/404");
            return;
        }
        SongModel song = songDao.findId(id);
        HttpSession session = req.getSession();
        String hasVisitted = (String)session.getAttribute("HasSession" + id);
        if(hasVisitted == null) {
            song.setCounter(song.getCounter()+1);
            songDao.upCounterSong(song,id);
            session.setAttribute("HasSession" + id, "yes");
            session.setMaxInactiveInterval(3600);
        }
        if (song==null){
            resp.sendRedirect(req.getContextPath()+"/404");
            return;
        }

        req.setAttribute("song",song);
        req.setAttribute("related",songDao.findRelatedSong(song,2));
        req.getRequestDispatcher("/public/detail.jsp").forward(req,resp);
    }
}
