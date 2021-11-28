package com.bsong.controller.admin.song;

import com.bsong.dao.ISongDao;
import com.bsong.dao.impl.SongDao;
import com.bsong.model.CategoryModel;
import com.bsong.model.SongModel;
import com.bsong.padding.PageRequest;
import com.bsong.sort.Sorter;
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

        SongModel song = new SongModel();
        String pageStr = req.getParameter("page");

        String maxPageitemStr =req.getParameter("maxPageItem");
        String sortBy = req.getParameter("sortBy");
        String sortName = req.getParameter("sortName");

        if(pageStr != null){
            song.setPage(Integer.parseInt(pageStr));
        }else {
            song.setPage(1);
        }

        if (maxPageitemStr != null){
            song.setMaxPageItem(Integer.parseInt(maxPageitemStr));
        }else {
            song.setMaxPageItem(4);
        }

        if (sortBy != null){
            song.setSortBy(sortBy);
        }else {
            song.setSortBy("DESC");
        }

        if (sortName != null){
            song.setSortName(sortName);
        }else {
            song.setSortName("id");
        }

        PageRequest pageRequest = new PageRequest(song.getPage(),song.getMaxPageItem(),
                new Sorter(song.getSortBy(),song.getSortName()));

        song.setListResult(songDao.findAll(null,pageRequest));
        song.setTotalItem(songDao.getTotalItem(null));
        song.setTotalPage((int )Math.ceil((double) song.getTotalItem()/song.getMaxPageItem()));
        req.setAttribute("songs",song);
        req.getRequestDispatcher("/admin/song/index.jsp").forward(req,resp);

    }
}
