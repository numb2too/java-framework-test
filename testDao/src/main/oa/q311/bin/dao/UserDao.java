package oa.q311.bin.dao;

import jcx.Talk;
import oa.common.model.User;

import java.util.Collections;
import java.util.List;

public class UserDao {

    Talk dbTalk;
    public UserDao(Talk dbTalk) {
        this.dbTalk = dbTalk;

    }


    public List<User> getUserList(String whereSql) {
        return Collections.emptyList();
    }


    public User getUser(String whereSql) {
        return new User();
    }
}
