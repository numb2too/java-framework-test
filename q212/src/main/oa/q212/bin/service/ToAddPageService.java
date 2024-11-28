package oa.q212.bin.service;

import jcx.Talk;
import oa.common.dao.UserDao;
import oa.common.dao.impl.UserDaoImpl;
import oa.common.model.User;
import oa.q212.bin.model.request.ToAddPageRequest;
import oa.q212.bin.model.response.ToAddPageResponse;
import oa.common.util.DateUtils;

public class ToAddPageService {

    protected UserDao userDao;

    public ToAddPageService(Talk talk) {
        userDao = new UserDaoImpl(talk);
    }

    public ToAddPageResponse getResponse(ToAddPageRequest request) {

        User user = userDao.getUser(request.getLoginUserId());

        ToAddPageResponse response = new ToAddPageResponse();
        response.setUserName(user.getUserName());
        response.setUserId(user.getUserId());
        response.seteDate(DateUtils.getTodayDefault());
        return response;

    }


}
