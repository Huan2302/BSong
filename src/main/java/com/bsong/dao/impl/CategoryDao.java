package com.bsong.dao.impl;

import com.bsong.dao.ICategoryDao;
import com.bsong.mapper.CategoryMapper;
import com.bsong.model.CategoryModel;

import java.util.List;

public class CategoryDao extends AbstractDao<CategoryModel> implements ICategoryDao {
    @Override
    public List<CategoryModel> findAll(Integer offset, Integer limit){
        StringBuffer sql = new StringBuffer("SELECT * FROM categories ORDER BY id DESC ");
        if (offset != null & limit != null){
            sql.append("LIMIT ?,?");
            return query(sql.toString(),new CategoryMapper(),offset,limit);
        }else {
            return query(sql.toString(),new CategoryMapper());
        }
    }

    @Override
    public Long addCat(CategoryModel categoryModel){
        String sql = "INSERT INTO categories(name) VALUES (?)";
        return insert(sql,categoryModel.getName());
    }

    @Override
    public CategoryModel findId(int id){
        String sql = "SELECT * FROM categories WHERE id = ?";
        List<CategoryModel> categories = query(sql,new CategoryMapper(),id);
        return categories.isEmpty() ? null : categories.get(0);
    }

    @Override
    public void updateCat(CategoryModel cat){
        String sql = "UPDATE categories SET name = ? WHERE id = ?";
        update(sql,cat.getName(),cat.getId());
    }

    @Override
    public int del(int id){
        String sql = "DELETE FROM categories WHERE id = ?";
        return delete(sql,id);
    }

    @Override
    public List<CategoryModel> search(String search) {
        String text = "%"+search+"%";
        String sql = "select * from categories where name like ?";
        return query(sql,new CategoryMapper(),text);
    }

    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) from categories";
        return count(sql);
    }
}
