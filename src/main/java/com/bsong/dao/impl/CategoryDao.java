package com.bsong.dao.impl;

import com.bsong.dao.ICategoryDao;
import com.bsong.mapper.CategoryMapper;
import com.bsong.model.CategoryModel;
import com.bsong.padding.PageRequest;

import java.util.List;

public class CategoryDao extends AbstractDao<CategoryModel> implements ICategoryDao {

    @Override
    public List<CategoryModel> findAll(String search, PageRequest pageble){
        StringBuffer sql = new StringBuffer("SELECT * FROM categories");
        if(search != null){
            String s = "'%"+search+"%'";
            sql.append(" where name like "+s);
        }
        if (pageble.getSorter().getSortBy() != null && pageble.getSorter().getSortname() != null){
            sql.append(" ORDER BY "+pageble.getSorter().getSortname()+" "+pageble.getSorter().getSortBy());
        }
        if (pageble.getOffset() != null & pageble.getLimit() != null){
            sql.append(" LIMIT "+pageble.getOffset()+", "+pageble.getLimit());
        }
        return query(sql.toString(),new CategoryMapper());
    }

    @Override
    public List<CategoryModel> findAll(){
        String sql ="SELECT * FROM categories";
        return query(sql,new CategoryMapper());
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
    public int getTotalItem(String search) {
        StringBuffer sql = new StringBuffer("SELECT count(*) from categories");
        if (search != null){
            String s = "'%"+search+"%'";
            sql.append(" where name like "+s);
        }
        return count(sql.toString());
    }
}
