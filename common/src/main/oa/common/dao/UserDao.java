package oa.common.dao;


import oa.common.model.User;

public interface UserDao {

    User getUser(String empId);
}
