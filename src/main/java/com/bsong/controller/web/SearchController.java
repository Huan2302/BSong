package com.bsong.controller.web;

import com.bsong.dao.impl.SongDao;
import com.bsong.model.SongModel;
import com.bsong.padding.PageRequest;
import com.bsong.sort.Sorter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/search")
public class SearchController extends HttpServlet {
    private SongDao songDao = new SongDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        SongModel song = new SongModel();

        String search = req.getParameter("editbox_search");
        System.out.println(search);
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
        song.setListResult(songDao.findAll(search,pageRequest));
        song.setTotalItem(songDao.getTotalItem(search));
        song.setTotalPage((int )Math.ceil((double) song.getTotalItem()/song.getMaxPageItem()));
        req.setAttribute("songs",song);
        req.getRequestDispatcher("/public/index.jsp").forward(req,resp);
    }
}
