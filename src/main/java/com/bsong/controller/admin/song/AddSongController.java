package com.bsong.controller.admin.song;

import com.bsong.dao.impl.CategoryDao;
import com.bsong.dao.impl.SongDao;
import com.bsong.model.SongModel;
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
import java.sql.Timestamp;
import java.util.Date;

@MultipartConfig
@WebServlet(value = "/admin/song/add")
public class AddSongController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("html/text");
        resp.setCharacterEncoding("UTF-8");

        req.setAttribute("categories",new CategoryDao().findAll());
        req.getRequestDispatcher("/admin/song/addSong.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        SongModel song = new SongModel();
        song.setName(req.getParameter("name"));
        song.setPreview_text(req.getParameter("preview_text"));
        song.setDetail_text(req.getParameter("detail_text"));
        song.setDate_create(new Timestamp(new Date().getTime()));
        song.setCounter(0);
        song.setCat_id(Integer.parseInt(req.getParameter("category")));

        Part filePart = req.getPart("picture");

        //tạo thư mục lưu ảnh
        final String dirPathName = req.getServletContext().getRealPath("/teamplate/admin/assets/img");
        File dirFile = new File(dirPathName);
        // kiểm tra thư mục đã tồn tại chưa. nếu chưa thì tạo thư mục để lưu ảnh
        if (!dirFile.exists()){
            dirFile.mkdir();
        }

        //lấy tên file từ path
        String fileName = FileUtil.getName(filePart);
        //đổi tên file
        String picture = FileUtil.rename(fileName);
        song.setPicture(picture);
        //đường dẫn file
        String filePathName = dirPathName + File.separator + picture;

        if (new SongDao().addSong(song)>0){
            //thành công

            if (!fileName.isEmpty()){
                filePart.write(filePathName);
            }
            resp.sendRedirect(req.getContextPath()+"/admin/songs?msg=1");
            return;
        }else {
            //thất bại
            resp.sendRedirect(req.getContextPath()+"/admin/song/add?err=1");
            return;
        }
    }
}
