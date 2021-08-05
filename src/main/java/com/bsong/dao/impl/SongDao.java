package com.bsong.dao.impl;

import com.bsong.dao.ISongDao;
import com.bsong.mapper.SongMapper;
import com.bsong.model.SongModel;
import com.bsong.util.DefineUtil;

import java.util.List;

public class SongDao extends AbstractDao<SongModel> implements ISongDao {
    @Override
    public List<SongModel> findAll(){
        StringBuffer sql = new StringBuffer("SELECT s.id AS id,s.name AS name, ");
        sql.append("preview_text, detail_text, date_create,picture," );
        sql.append("counter,cat_id,c.name AS cat_name ");
        sql.append("FROM songs AS s ");
        sql.append("INNER JOIN categories AS c ON s.cat_id = c.id ");
        sql.append("ORDER BY s.id DESC");
        return query(sql.toString(),new SongMapper());
    }

    @Override
    public List<SongModel> findAll(int id){
        StringBuffer sql = new StringBuffer("SELECT s.id AS id,s.name AS name, ");
        sql.append("preview_text, detail_text, date_create,picture," );
        sql.append("counter,cat_id,c.name AS cat_name ");
        sql.append("FROM songs AS s ");
        sql.append("INNER JOIN categories AS c ON s.cat_id = c.id ");
        sql.append("ORDER BY s.id DESC LIMIT ?");
        return query(sql.toString(),new SongMapper(),id);
    }

    @Override
    public List<SongModel> findRelatedSong(SongModel song, int limit) {
        StringBuffer sql = new StringBuffer("SELECT s.id AS id,s.name AS name, ");
        sql.append("preview_text, detail_text, date_create,picture," );
        sql.append("counter,cat_id,c.name AS cat_name ");
        sql.append("FROM songs AS s ");
        sql.append("INNER JOIN categories AS c ON s.cat_id = c.id ");
        sql.append("WHERE s.cat_id = ? && s.id != ? ");
        sql.append("ORDER BY s.id DESC LIMIT ?");
        return query(sql.toString(),new SongMapper(),song.getCat_id(),song.getId(),limit);
    }

    @Override
    public Long addSong(SongModel song){
        StringBuffer sql =new StringBuffer("INSERT INTO songs(name,preview_text,detail_text,date_create,picture,counter,cat_id)");
        sql.append("VALUES (?,?,?,?,?,?,?)");
        return insert(sql.toString(),song.getName(),song.getPreview_text(),
                song.getDetail_text(),song.getDate_create(),song.getPicture(),
                song.getCounter(),song.getCat_id());
    }

    @Override
    public int delSong(int id){
        String sql = "DELETE FROM songs WHERE id = ?";
        return delete(sql,id);
    }

    @Override
    public SongModel findId(int id){
        StringBuffer sql = new StringBuffer("SELECT s.id AS id,s.name AS name, ");
        sql.append("preview_text, detail_text, date_create,picture," );
        sql.append("counter,cat_id,c.name AS cat_name ");
        sql.append("FROM songs AS s ");
        sql.append("INNER JOIN categories AS c ON s.cat_id = c.id ");
        sql.append("WHERE s.id = ?");
        List<SongModel> song = query(sql.toString(),new SongMapper(),id);
        return song.isEmpty() ? null : song.get(0);
    }

    @Override
    public List<SongModel> findCatId(int id) {
        String sql = "select * from songs where cat_id = ?";
        return query(sql,new SongMapper(),id);
    }

    @Override
    public void upSong(SongModel song, int id){
        String sql = "UPDATE songs SET name = ?,preview_text = ?,detail_text = ?," +
                "picture = ?,cat_id = ? WHERE id = ?";
        update(sql,song.getName(),song.getPreview_text(),song.getDetail_text(),
                song.getPicture(),song.getCat_id(),id);
    }

    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) from songs";
        return count(sql);
    }

    @Override
    public List<SongModel> findAllPagination(int offset) {
        StringBuffer sql = new StringBuffer("SELECT s.id AS id,s.name AS name, ");
        sql.append("preview_text, detail_text, date_create,picture," );
        sql.append("counter,cat_id,c.name AS cat_name ");
        sql.append("FROM songs AS s ");
        sql.append("INNER JOIN categories AS c ON s.cat_id = c.id ");
        sql.append("ORDER BY s.id DESC LIMIT ?,?");
        return query(sql.toString(),new SongMapper(),offset, DefineUtil.NUMBER_PER_PAGE);
    }
}
