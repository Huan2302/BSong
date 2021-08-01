package com.bsong.mapper;

import com.bsong.model.CategoryModel;
import com.bsong.model.SongModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SongMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs) {

        try {
            SongModel song = new SongModel();
            song.setId(rs.getInt("id"));
            song.setName(rs.getString("name"));
            song.setPreview_text(rs.getString("preview_text"));
            song.setDetail_text(rs.getString("detail_text"));
            song.setDate_create(rs.getTimestamp("date_create"));
            song.setPicture(rs.getString("picture"));
            song.setCounter(rs.getInt("counter"));
            song.setCat_id(rs.getInt("cat_id"));
            try {
                CategoryModel cat = new CategoryModel();
                cat.setId(rs.getInt("cat_id"));
                cat.setName(rs.getString("cat_name"));
                song.setCat_name(cat);
            }catch (Exception e){
                System.out.print(e.getMessage());
            }
            return song;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
