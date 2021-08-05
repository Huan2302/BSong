package com.bsong.controller.admin.song;

import com.bsong.dao.impl.SongDao;
import com.bsong.util.AuthUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/song/del")
public class DelSongController extends HttpServlet {
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
            resp.sendRedirect(req.getContextPath()+"/admin/songs");
        }
        if (new SongDao().delSong(id)>0){
            resp.sendRedirect(req.getContextPath()+"/admin/songs?msg=3");
        }else {
            resp.sendRedirect(req.getContextPath()+"/admin/songs?err=3");
        }
    }
}
