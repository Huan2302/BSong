package com.bsong.mapper;

import com.bsong.model.ContactModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactMapper implements RowMapper{
    @Override
    public Object mapRow(ResultSet rs) {
        try {
            ContactModel contact = new ContactModel();
            contact.setId(rs.getInt("id"));
            contact.setName(rs.getString("name"));
            contact.setEmail(rs.getString("email"));
            contact.setWebsite(rs.getString("website"));
            contact.setMessage(rs.getString("message"));
            return contact;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
