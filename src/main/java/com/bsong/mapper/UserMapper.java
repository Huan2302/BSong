package com.bsong.mapper;

import com.bsong.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper{
    @Override
    public Object mapRow(ResultSet rs) {
        try {
            UserModel user = new UserModel();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("username"));
            user.setPass(rs.getString("password"));
            user.setFullName(rs.getString("fullname"));
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
