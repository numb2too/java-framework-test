package demo;

import util.Convert;

import java.sql.PreparedStatement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {

        Map<String, Object> map = new HashMap<>();
        map.put("req_emp_id", Convert.ToSql("f"));
        map.put("req_dep_no", Convert.ToSql("f1"));
        map.put("req_date", Convert.ToSql("223123"));


        List<String> setList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            setList.add(entry.getKey() + " = " + entry.getValue());
        }

        String sql = "update q311 set " + String.join(",", setList) + " where uuid = '" + "f" + "'";
        logger.log(Level.INFO, sql);
    }
}