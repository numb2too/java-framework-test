package oa.q311;

import jcx.Hproc;
import jcx.Talk;
import oa.q311.bin.dao.Q311Dao;
import oa.q311.bin.model.Q311;

public class SqlPLoad extends Hproc {
    Q311Dao q311Dao;
    @Override
    public void action() {

        Talk dbTalk = getTalk("dbTalk");
        q311Dao = new Q311Dao(dbTalk);


    }

    public void testInsertQ311() {
        Q311 q311 = new Q311();
        q311.setUuid("1");
        q311.setReqEmpId("2");
        q311.setReqDepNo("22");
        q311.setReqDate("20241122");
        q311Dao.insertQ311(q311);
    }




}
