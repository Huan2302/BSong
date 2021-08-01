package com.bsong.dao;

import com.bsong.model.SongModel;

import java.util.List;

public interface ISongDao extends GenericDao<SongModel> {
    List<SongModel> findAll();
    Long addSong(SongModel song);
    int delSong(int id);
    SongModel findId(int id);
    List<SongModel> findCatId(int id);
    void upSong(SongModel song, int id);
    List<SongModel> findAll(int id);
    List<SongModel> findRelatedSong(SongModel song,int limit);
}
