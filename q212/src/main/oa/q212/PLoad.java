package oa.q212;

import jcx.Hproc;
import jcx.Talk;
import util.Convert;

public class PLoad extends Hproc {

    Talk dbTalk1;
    Talk dbTalk2;

    @Override
    public void action() {

        this.dbTalk1 = getTalk();
        this.dbTalk2=getTalk("dbTalk2");

        String objName = getName();
        if("TO_ADD_PAGE_BTN".equals(objName)){
            toAddPage();
        }else if("SAVE_BTN".equals(objName)){
            saveBtn();
        }

    }

    private void toAddPage(){
        String empId = getUser();
        String sql = "select hecname,depName from hruser where empID = '" + Convert.ToSql(empId) + "'";
        String[][] dbData = dbTalk1.queryFromPool(sql);
        String hecname="";
        String depName="";
        if(dbData.length>0 && dbData[0].length>0){
            hecname= dbData[0][0];
            depName= dbData[0][1];
        }

        setValue("HECNAME", hecname);
        setValue("DEPNAME", depName);

    }

    private void saveBtn(){
        String uuid = getValue("UUID");
        String hecname = getValue("HECNAME");
        String depName = getValue("DEPNAME");
        String depNo = getValue("DEPNO");
        String sql = "update hruser set hecname = '" + Convert.ToSql(hecname) + "', depName = '" + Convert.ToSql(depName) + "'" +
                " where uuid = '" + Convert.ToSql(uuid) + "' and dep_no = '" + Convert.ToSql(depNo) + "'";
        dbTalk2.execFromPool(sql);

    }
}
