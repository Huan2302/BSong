package com.bsong.dao.impl;

import com.bsong.mapper.UserMapper;
import com.bsong.model.UserModel;

import java.util.List;

public class UserDao extends AbstractDao{
    public List<UserModel> findAll(){
        String sql = "SELECT * FROM users ORDER BY id DESC";
        return query(sql,new UserMapper());
    }
    public Long addUser(UserModel user){
        String sql = "INSERT INTO users(username, password, fullname) VALUES (?,?,?)";
        return insert(sql,user.getName(),user.getPass(),user.getFullName());
    }

    public UserModel findId(int id){
        String sql = "SELECT * FROM users WHERE id = ?";
        List<UserModel> user = query(sql,new UserMapper(),id);
        return user.isEmpty() ? null : user.get(0);
    }

    public void updateUser(UserModel user){
        String sql = "UPDATE users SET password = ?, fullname = ?  WHERE id = ?";
        update(sql,user.getPass(),user.getFullName(),user.getId());
    }

    public void del(int id){
        String sql = "DELETE FROM users WHERE id = ?";
        update(sql,id);
    }

    public UserModel checkLogin(UserModel user){
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        List<UserModel> login = query(sql,new UserMapper(),user.getName(),user.getPass());
        return login.isEmpty() ? null : login.get(0);
    }
}
