package com.bsong.model;

import java.sql.Timestamp;

public class SongModel extends AbstractModel{
    private String preview_text;
    private String detail_text;
    private Timestamp date_create;
    private String picture;
    private int counter;
    private int cat_id;
    private CategoryModel cat_name;

    public String getPreview_text() {
        return preview_text;
    }

    public void setPreview_text(String preview_text) {
        this.preview_text = preview_text;
    }

    public String getDetail_text() {
        return detail_text;
    }

    public void setDetail_text(String detail_text) {
        this.detail_text = detail_text;
    }

    public Timestamp getDate_create() {
        return date_create;
    }

    public void setDate_create(Timestamp date_create) {
        this.date_create = date_create;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public CategoryModel getCat_name() {
        return cat_name;
    }

    public void setCat_name(CategoryModel cat_name) {
        this.cat_name = cat_name;
    }
}
