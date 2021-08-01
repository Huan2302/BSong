package com.bsong.dao.impl;

import com.bsong.dao.IContactDao;
import com.bsong.mapper.ContactMapper;
import com.bsong.model.ContactModel;

import java.util.List;

public class ContactDao extends AbstractDao<ContactModel> implements IContactDao {
    @Override
    public List<ContactModel> findAll(){
        String sql = "SELECT * FROM contacts ORDER BY id DESC";
        return query(sql,new ContactMapper());
    }

    @Override
    public void delContact(int id){
        String sql = "DELETE FROM contacts WHERE id = ?";
        update(sql,id);
    }

    @Override
    public Long addContact(ContactModel contact) {
        String sql = "INSERT INTO contacts(name, email, website, message) VALUES (?, ?, ?, ?)";
        return insert(sql,contact.getName(),contact.getEmail(),contact.getWebsite(),contact.getMessage());
    }
}
