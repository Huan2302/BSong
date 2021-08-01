package com.bsong.dao;

import com.bsong.model.ContactModel;

import java.util.List;

public interface IContactDao extends GenericDao<ContactModel>{
    List<ContactModel> findAll();
    void delContact(int id);
    Long addContact(ContactModel contact);
}
