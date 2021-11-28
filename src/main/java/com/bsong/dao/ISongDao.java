package com.bsong.dao;

import com.bsong.model.SongModel;
import com.bsong.padding.PageRequest;

import java.util.List;

public interface ISongDao extends GenericDao<SongModel> {
    List<SongModel> findAll(String search, PageRequest pageRequest);
    Long addSong(SongModel song);
    int delSong(int id);
    SongModel findId(int id);
    List<SongModel> findCatId(int id);
    void upSong(SongModel song, int id);
    List<SongModel> findAll(int id);
    List<SongModel> findRelatedSong(SongModel song,int limit);
    int getTotalItem(String search);
    List<SongModel> findAllPagination(int offset);
    void upCounterSong(SongModel song,int id);
}
