package com.bsong.dao;

import com.bsong.model.CategoryModel;
import com.bsong.padding.PageRequest;

import java.util.List;

public interface ICategoryDao extends GenericDao<CategoryModel> {
    List<CategoryModel> findAll(String search, PageRequest pageble);
    List<CategoryModel> findAll();
    Long addCat(CategoryModel categoryModel);
    CategoryModel findId(int id);
    void updateCat(CategoryModel cat);
    int del(int id);
    int getTotalItem(String search);
}
