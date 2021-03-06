package com.bsong.controller.admin.song;

import com.bsong.dao.impl.CategoryDao;
import com.bsong.dao.impl.SongDao;
import com.bsong.model.SongModel;
import com.bsong.util.AuthUtil;
import com.bsong.util.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@MultipartConfig
@WebServlet(value = "/admin/song/edit")
public class EditSongController extends HttpServlet {
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
        SongModel song = new SongDao().findId(id);
        req.setAttribute("categories",new CategoryDao().findAll());

        if (song != null){
            req.setAttribute("song",song);
            req.getRequestDispatcher("/admin/song/edit.jsp").forward(req,resp);
        }else{
            resp.sendRedirect(req.getContextPath()+"/admin/songs?err=2");
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!AuthUtil.checkLogin(req,resp)){
            resp.sendRedirect(req.getContextPath()+"/login");
            return;
        }
        req.setCharacterEncoding("UTF-8");

        int id = 0;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        }catch (NumberFormatException e){
            resp.sendRedirect(req.getContextPath()+"/admin/songs");
        }

        SongModel song = new SongModel();
        song.setName(req.getParameter("name"));
        song.setPreview_text(req.getParameter("preview_text"));
        song.setDetail_text(req.getParameter("detail_text"));
        System.out.println(song.getDetail_text());
        song.setCat_id(Integer.parseInt(req.getParameter("category")));
        Part filePart = req.getPart("picture");

        SongModel songid = new SongDao().findId(id);
        if (songid == null){
            resp.sendRedirect(req.getContextPath()+"/admin/songs?err=2");
            return;
        }

        //t???o th?? m???c l??u ???nh
        final String dirPathName = req.getServletContext().getRealPath("/teamplate/admin/assets/img");
        File dirFile = new File(dirPathName);
        // ki???m tra th?? m???c ???? t???n t???i ch??a. n???u ch??a th?? t???o th?? m???c ????? l??u ???nh
        if (!dirFile.exists()){
            dirFile.mkdir();
        }

        //l???y t??n file t??? path
        String fileName = FileUtil.getName(filePart);
        //?????i t??n file
        String picture = "";
        if (fileName.isEmpty()){
            picture = songid.getPicture();
        }else {
            picture = FileUtil.rename(fileName);
        }

        song.setPicture(picture);
        //???????ng d???n file
        String filePathName = dirPathName + File.separator + picture;

        new SongDao().upSong(song,id);
        if (!fileName.isEmpty()){
            //x??a file c??
            String oldFilePathName = dirPathName + File.separator + songid.getPicture();
            File oldFile = new File(oldFilePathName);
            if (oldFile.exists()){
                oldFile.delete();
            }

            //ghi file
            filePart.write(filePathName);
        }
        resp.sendRedirect(req.getContextPath()+"/admin/songs?msg=2");
    }
}
