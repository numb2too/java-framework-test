package oa.q311;

import jcx.Hproc;
import jcx.Talk;
import oa.q311.bin.dao.UserDao;
import oa.q311.bin.element.btn.ToAddPageBtn;
import oa.q311.bin.response.MyResponse;

import java.util.Map;
import java.util.Objects;

import static oa.q311.bin.parameter.Q311Parameter.TO_ADD_PAGE_BTN;
import static oa.q311.bin.parameter.Q311Parameter.USER_ID;

public class PLoad extends Hproc {
    Talk dbTalk1;
    UserDao userDao;
    @Override
    public void action() {

        dbTalk1 = getTalk();
        userDao = new UserDao(dbTalk1);

        String objName = getName();
        if (TO_ADD_PAGE_BTN.equals(objName)) {
            toAddPageBtn();
        }
    }

    private void toAddPageBtn(){
        String loginUserId = getUser();

        ToAddPageBtn toAddPageBtn = new ToAddPageBtn(userDao);
        MyResponse response = toAddPageBtn.click(loginUserId);
        Map<String, Object> data = (Map<String, Object>)response.getData();
        setValue(USER_ID, (String) data.get("empId"));

    }
}
