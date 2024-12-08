package oa.q311.bin.dao;

import jcx.Talk;
import oa.common.util.DataBaseUtil;
import oa.q311.bin.model.Q311;
import util.Convert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Q311Dao {
    private static final Logger logger = Logger.getLogger(Q311Dao.class.getName());
    Talk dbTalk;

    public Q311Dao(Talk dbTalk) {
        this.dbTalk = dbTalk;

    }


    public Q311 getQ311(String whereSql) {
        return getQ311List(whereSql).get(0);
    }


    public List<Q311> getQ311List(String whereSql) {
        List<Q311> q311List = new ArrayList<>();

        String sql = "select uuid,req_emp_id,req_dep_no,req_date from q311 where 1=1 " + whereSql;
        logger.log(Level.INFO, sql);
        String[][] dbData = DataBaseUtil.queryFromPool(dbTalk, sql);
        if (dbData != null) {
            for (String[] rowData : dbData) {
                Q311 q311 = new Q311();
                q311.setUuid(rowData[0]);
                q311.setReqEmpId(rowData[1]);
                q311.setReqDepNo(rowData[2]);
                q311.setReqDate(rowData[3]);
                q311List.add(q311);
            }
        }
        return q311List;
    }


    public void insertQ311(Q311 q311) {

        Map<String, Object> map = new HashMap<>();
        map.put("uuid", Convert.ToSql("1"));
        map.put("req_emp_id", Convert.ToSql("2"));
        map.put("req_dep_no", Convert.ToSql("22"));
        map.put("req_date", Convert.ToSql("20241122"));

// 使用 stream() 來組合 values
        String values = map.values().stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        String sql = "insert into q311(" + String.join(",", map.keySet()) + ") values(" + values + ")";
        logger.log(Level.INFO, sql);
        DataBaseUtil.execFromPool(dbTalk, sql);
    }


    public void updateQ311(Q311 q311) {
        Map<String, Object> map = new HashMap<>();
        map.put("req_emp_id", Convert.ToSql(q311.getReqEmpId()));
        map.put("req_dep_no", Convert.ToSql(q311.getReqDepNo()));
        map.put("req_date", Convert.ToSql(q311.getReqDate()));


        List<String> setList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            setList.add(entry.getKey() + " = " + entry.getValue());
        }

        String sql = "update q311 set " + String.join(",", setList) + " where uuid = '" + q311.getUuid() + "'";
        logger.log(Level.INFO, sql);
        DataBaseUtil.execFromPool(dbTalk, sql);
    }


    public void deleteQ311(Q311 q311) {

        String sql = "delete from q311  where uuid = '" + q311.getUuid() + "'";
        logger.log(Level.INFO, sql);
        DataBaseUtil.execFromPool(dbTalk, sql);
    }

}
