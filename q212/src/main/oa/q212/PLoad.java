package oa.q212;

import jcx.Hproc;
import jcx.Talk;
import oa.q212.bin.model.request.ToAddPageRequest;
import oa.q212.bin.model.response.ToAddPageResponse;
import oa.q212.bin.service.ToAddPageService;
import util.Convert;

import static oa.q212.bin.parameter.Q212Parameter.*;

public class PLoad extends Hproc {

    Talk dbTalk1;
    Talk dbTalk2;

    @Override
    public void action() {

        this.dbTalk1 = getTalk();

        String objName = getName();
        if (TO_ADD_PAGE_BTN.equals(objName)) {
            toAddPage();
        } else if (SAVE_BTN.equals(objName)) {
            saveBtn();
        }

    }

    private void toAddPage() {
        String loginUserId = getUser();

        ToAddPageRequest request = new ToAddPageRequest();
        request.setLoginUserId(loginUserId);

        ToAddPageService toAddPageService = new ToAddPageService(dbTalk1);
        ToAddPageResponse response = toAddPageService.getResponse(request);
        setValue(USER_ID, response.getUserId());
        setValue(USER_NAME, response.getUserName());

    }

    private void saveBtn() {
        String uuid = getValue("UUID");
        String hecname = getValue("HECNAME");
        String depName = getValue("DEPNAME");
        String depNo = getValue("DEPNO");
        String sql = "update hruser set hecname = '" + Convert.ToSql(hecname) + "', depName = '" + Convert.ToSql(depName) + "'" +
                " where uuid = '" + Convert.ToSql(uuid) + "' and dep_no = '" + Convert.ToSql(depNo) + "'";
        dbTalk2.execFromPool(sql);

    }
}
