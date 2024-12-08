package oa.q311.bin.element.btn;

import oa.common.model.User;
import oa.q311.bin.dao.UserDao;
import oa.q311.bin.response.MyResponse;

import java.util.HashMap;
import java.util.Map;

public class ToAddPageBtn {

    UserDao userDao;
    public ToAddPageBtn(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 跳至新增頁面
     * 設定登入者員工編號，登入者單位編號
     * 設定登入日期YYYYmmdd
     * */
    public MyResponse click(String loginUserId) {


        User user = userDao.getUser(" and empId = " + loginUserId);


        Map<String,String> map = new HashMap<>();
        map.put("empId",user.getUserId());
        map.put("depId",user.getUserDepId());

        // 使用建造者模式構建響應
        return MyResponse.builder()
                .code("200")
                .msg("成功")
                .data(map)
                .build();
    }
}
