package com.bsong.controller.admin.song;

import com.bsong.dao.ISongDao;
import com.bsong.dao.impl.SongDao;
import com.bsong.model.SongModel;
import com.bsong.util.AuthUtil;
import com.bsong.util.DefineUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/admin/songs")
public class IndexSongController extends HttpServlet {

    private SongDao songDao = new SongDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!AuthUtil.checkLogin(req,resp)){
            resp.sendRedirect(req.getContextPath()+"/login");
            return;
        }
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");

        int getTotalItem = songDao.getTotalItem();
        int getTotalPage = (int)Math.ceil((float)getTotalItem/ DefineUtil.NUMBER_PER_PAGE);

        int currentPage = 1;
        try{
            currentPage = Integer.parseInt(req.getParameter("page"));
        }catch (NumberFormatException e){}
        if (currentPage > getTotalPage || currentPage < 1){

        }
        int offset = (currentPage - 1)*DefineUtil.NUMBER_PER_PAGE;

        req.setAttribute("getTotalPage",getTotalPage);
        req.setAttribute("currentPage",currentPage);
        req.setAttribute("songs",songDao.findAllPagination(offset));
        req.getRequestDispatcher("/admin/song/index.jsp").forward(req,resp);
    }
}
