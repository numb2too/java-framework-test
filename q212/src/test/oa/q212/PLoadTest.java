package oa.q212;

import jcx.Talk;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

public class PLoadTest {

    private TestPLoad pload;
    private Map<String, String> setValueMap;
    private String lastQuerySql;
    private String lastExecSql;

    // 測試用的 PLoad 子類
    private class TestPLoad extends PLoad {
        private String testObjName;
        private String testUser;
        private String[][] testQueryResult;
        private TestValueStore valueStore = new TestValueStore();

        @Override
        public Talk getTalk() {
            return new TestTalk();
        }

        @Override
        public Talk getTalk(String name) {
            return new TestTalk();
        }

        @Override
        public String getName() {
            return testObjName;
        }

        @Override
        public String getUser() {
            return testUser;
        }

        @Override
        public String getValue(String key) {
            return valueStore.get(key);
        }

        @Override
        protected void setValue(String key, String value) {
            setValueMap.put(key, value);
            valueStore.put(key, value);
        }

        public void setTestObjName(String name) {
            this.testObjName = name;
        }

        public void setTestUser(String user) {
            this.testUser = user;
        }

        public void setTestQueryResult(String[][] result) {
            this.testQueryResult = result;
        }

        public void setupTestValue(String key, String value) {
            valueStore.put(key, value);
        }
    }

    // 測試用的值存儲
    private class TestValueStore {
        private Map<String, String> store = new HashMap<>();

        public void put(String key, String value) {
            store.put(key, value);
        }

        public String get(String key) {
            return store.getOrDefault(key, "");
        }
    }

    // 測試用的 Talk 類
    private class TestTalk extends Talk {
        public String[][] queryFromPool(String sql) {
            lastQuerySql = sql;
            return pload.testQueryResult;
        }

        public int execFromPool(String sql) {
            lastExecSql = sql;
            return 0;
        }
    }

    @Before
    public void setUp() {
        pload = new TestPLoad();
        setValueMap = new HashMap<>();
        lastQuerySql = null;
        lastExecSql = null;
    }

    @Test
    public void testToAddPage() {
        // Arrange
        pload.setTestObjName("TO_ADD_PAGE_BTN");
        pload.setTestUser("TEST001");
        pload.setTestQueryResult(new String[][]{{"Test User", "Test Dep"}});

        // Act
        pload.action();

        // Assert
        assertEquals("Should query with correct empID",
                "select hecname,depName from hruser where empID = 'TEST001'",
                lastQuerySql.trim());

        assertEquals("Should set correct hecname value",
                "Test User",
                setValueMap.get("HECNAME"));

        assertEquals("Should set correct depname value",
                "Test Dep",
                setValueMap.get("DEPNAME"));
    }

    @Test
    public void testToAddPageNoData() {
        // Arrange
        pload.setTestObjName("TO_ADD_PAGE_BTN");
        pload.setTestUser("TEST001");
        pload.setTestQueryResult(new String[0][0]);

        // Act
        pload.action();

        // Assert
        assertEquals("Should query with correct empID",
                "select hecname,depName from hruser where empID = 'TEST001'",
                lastQuerySql.trim());

        assertEquals("Empty HECNAME should be set",
                "",
                setValueMap.get("HECNAME"));

        assertEquals("Empty DEPNAME should be set",
                "",
                setValueMap.get("DEPNAME"));
    }

    @Test
    public void testSaveBtn() {
        // Arrange
        pload.setTestObjName("SAVE_BTN");
        pload.setupTestValue("UUID", "123");
        pload.setupTestValue("HECNAME", "Test Name");
        pload.setupTestValue("DEPNAME", "Test Dep");
        pload.setupTestValue("DEPNO", "D001");

        // Act
        pload.action();

        // Assert
        String expectedSql = "update hruser set hecname = 'Test Name', depName = 'Test Dep' " +
                "where uuid = '123' and dep_no = 'D001'";
        assertEquals("Should execute correct update SQL",
                expectedSql,
                lastExecSql.trim());
    }
}