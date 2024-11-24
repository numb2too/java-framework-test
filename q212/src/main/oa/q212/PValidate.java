package oa.q212;

import jcx.Bproc;
import jcx.Talk;
import util.Convert;

public class PValidate extends Bproc {

    Talk dbTalk1;
    Talk dbTalk2;
    @Override
    public String validate(String value) {

        this.dbTalk1 = getTalk();
        this.dbTalk2=getTalk("dbTalk2");
        String objName = getName();
        if("QRCODE_FIELD".equals(objName)){
            return qrCodeFieldValidate(value);
        }else if ("REQ_EMP_ID".equals(objName)) {
            reqEmpIdValidate(value);
        }

        return value;
    }

    private String qrCodeFieldValidate(String value){

        String sql = "select * from hruser where uuid = '" + Convert.ToSql(value) + "'";
        String[][] dbData = dbTalk1.queryFromPool(sql);
        if(dbData.length>0 && dbData[0].length>0){
            return "false";
        }
        return value;
    }

    private void reqEmpIdValidate(String empId) {
        String sql = "select * from hruser where empID = '" + Convert.ToSql(empId) + "'";
        String[][] dbData = dbTalk1.queryFromPool(sql);
        if (dbData.length > 0 && dbData[0].length > 0) {
            setValue("UUID", dbData[0][0]);
        }
    }
}
