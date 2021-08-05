package com.bsong.dao;

import com.bsong.model.CategoryModel;

import java.util.List;

public interface ICategoryDao extends GenericDao<CategoryModel> {
    List<CategoryModel> findAll();
    Long addCat(CategoryModel categoryModel);
    CategoryModel findId(int id);
    void updateCat(CategoryModel cat);
    int del(int id);
    List<CategoryModel> search(String search);
}
