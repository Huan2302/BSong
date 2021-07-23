package com.bsong.dao;

import com.bsong.mapper.CategoryMapper;
import com.bsong.model.CategoryModel;

import java.util.List;

public class CategoryDao extends AbstractDao{
    public List<CategoryModel> findAll(){
        String sql = "SELECT * FROM categories ORDER BY id DESC";
        return query(sql,new CategoryMapper());
    }
}
