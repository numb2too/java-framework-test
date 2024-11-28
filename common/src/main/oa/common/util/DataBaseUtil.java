package oa.common.util;

import jcx.Talk;
import oa.common.exception.MyException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseUtil {

    DataBaseUtil(){}
    private static final Logger logger = Logger.getLogger(DataBaseUtil.class.getName());

    /**
     * 從連接池查詢資料
     * @param talk Talk 物件
     * @param sql SQL 查詢語句
     * @return 查詢結果陣列
     * @throws MyException 當查詢發生錯誤時拋出
     */
    public static String[][] queryFromPool(Talk talk, String sql) {
        validateParameters(talk, sql);

        try {
            String[][] result = talk.queryFromPool(sql);
            logQuerySuccess(sql);
            return result;
        } catch (Exception e) {
            return handleQueryException(sql, e);
        }
    }

    private static void validateParameters(Talk talk, String sql) {
        if (talk == null) {
            throw new MyException("Talk object cannot be null");
        }
        if (sql == null || sql.trim().isEmpty()) {
            throw new MyException("SQL query cannot be null or empty");
        }
    }

    private static void logQuerySuccess(String sql) {
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("Successfully executed query: " + sql);
        }
    }

    private static String[][] handleQueryException(String sql, Exception e) {
        String errorMessage = String.format("Error executing query: %s. Error: %s", sql, e.getMessage());
        logger.log(Level.SEVERE, errorMessage, e);
        throw new MyException(errorMessage, e);
    }
}