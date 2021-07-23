package com.bsong.mapper;

import com.bsong.model.CategoryModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper{
    @Override
    public Object mapRow(ResultSet rs) {
        try{
            CategoryModel category = new CategoryModel();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            return category;
        }catch (SQLException e){
            return  null;
        }
    }
}
