package com.bsong.dao;

import com.bsong.mapper.CategoryMapper;
import com.bsong.model.CategoryModel;

import java.util.List;

public class CategoryDao extends AbstractDao{
    public List<CategoryModel> findAll(){
        String sql = "SELECT * FROM categories ORDER BY id DESC";
        return query(sql,new CategoryMapper());
    }

    public Long addCat(CategoryModel categoryModel){
        String sql = "INSERT INTO categories(name) VALUES (?)";
        return insert(sql,categoryModel.getName());
    }

    public CategoryModel findId(int id){
        String sql = "SELECT * FROM categories WHERE id = ?";
        List<CategoryModel> categories = query(sql,new CategoryMapper(),id);
        return categories.isEmpty() ? null : categories.get(0);
    }

    public void updateCat(CategoryModel cat){
        String sql = "UPDATE categories SET name = ? WHERE id = ?";
        update(sql,cat.getName(),cat.getId());
    }

    public void del(int id){
        String sql = "DELETE FROM categories WHERE id = ?";
        update(sql,id);
    }
}
