package oa.common.dao.impl;

import jcx.Talk;
import oa.common.dao.UserDao;
import oa.common.model.User;
import oa.common.util.DataBaseUtil;
import util.Convert;

public class UserDaoImpl implements UserDao {

    Talk dbTalk1;
    public UserDaoImpl(Talk dbTalk1) {
        this.dbTalk1 = dbTalk1;
    }
    @Override
    public User getHruser(String empId) {
        User user = new User();
        String sql = "select userId, userName, userDepId, userDepName from user where userId = '" + Convert.ToSql(empId) + "'";
        String[][] dbData = DataBaseUtil.queryFromPool(dbTalk1,sql);
        if (dbData.length > 0 && dbData[0].length > 0) {
            user.setUserId(dbData[0][0]);
            user.setUserName(dbData[0][1]);
            user.setUserDepId(dbData[0][2]);
            user.setUserDepName(dbData[0][3]);
        }
        return user;
    }
}
