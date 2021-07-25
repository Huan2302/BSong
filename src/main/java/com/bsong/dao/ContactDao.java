package com.bsong.dao;

import com.bsong.mapper.ContactMapper;
import com.bsong.model.ContactModel;

import java.util.List;

public class ContactDao extends AbstractDao{
    public List<ContactModel> findAll(){
        String sql = "SELECT * FROM contacts ORDER BY id DESC";
        return query(sql,new ContactMapper());
    }

    public void delContact(int id){
        String sql = "DELETE FROM contacts WHERE id = ?";
        update(sql,id);
    }
}
