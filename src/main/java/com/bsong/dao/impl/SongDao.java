package com.bsong.dao.impl;

import com.bsong.dao.ISongDao;
import com.bsong.mapper.CategoryMapper;
import com.bsong.mapper.SongMapper;
import com.bsong.model.CategoryModel;
import com.bsong.model.SongModel;
import com.bsong.padding.PageRequest;
import com.bsong.util.DefineUtil;

import java.util.List;

public class SongDao extends AbstractDao<SongModel> implements ISongDao {
    @Override
    public List<SongModel> findAll(String search,PageRequest pageble){
        StringBuffer sql = new StringBuffer("SELECT s.id AS id,s.name AS name, ");
        sql.append("preview_text, detail_text, date_create,picture," );
        sql.append("counter,cat_id,c.name AS cat_name ");
        sql.append("FROM songs AS s ");
        sql.append("INNER JOIN categories AS c ON s.cat_id = c.id ");

        if (search != null){
            String s = "'%"+search+"%'";
            sql.append(" WHERE s.name LIKE "+s);
        }
        if (pageble!=null && pageble.getSorter().getSortBy() != null && pageble.getSorter().getSortname() != null){
            sql.append(" ORDER BY "+pageble.getSorter().getSortname()+" "+pageble.getSorter().getSortBy());
        }
        if (pageble!=null && pageble.getOffset() != null & pageble.getLimit() != null){
            sql.append(" LIMIT "+pageble.getOffset()+", "+pageble.getLimit());
        }
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
    public int getTotalItem(String search) {
        StringBuffer sql = new StringBuffer("SELECT count(*) from songs");
        if (search != null){
            String s = "'%"+search+"%'";
            sql.append(" where name like "+s);
        }
        return count(sql.toString());
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

    @Override
    public void upCounterSong(SongModel song, int id) {
        String sql = "UPDATE songs SET counter = ? WHERE id = ?";
        update(sql,song.getCounter(),id);
    }
}
