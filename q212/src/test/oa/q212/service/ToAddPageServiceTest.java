package oa.q212.service;

import oa.common.model.User;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import jcx.Talk;
import oa.common.dao.UserDao;
import oa.q212.bin.model.request.ToAddPageRequest;
import oa.q212.bin.model.response.ToAddPageResponse;
import oa.q212.bin.service.ToAddPageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ToAddPageServiceTest {

    private static final Logger logger = Logger.getLogger(ToAddPageServiceTest.class.getName());
    @Mock
    private Talk talk;

    @Mock
    private UserDao userDao;

    private ToAddPageService toAddPageService;

    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
        // 因為 Q212Service 在構造函數中創建了 UserDaoImpl，
        // 我們需要使用 PowerMockito 來模擬這個行為
        toAddPageService = new ToAddPageService(talk) {
            {
                this.userDao = ToAddPageServiceTest.this.userDao;
            }
        };
    }

    @Test
    public void testToAddPage_Success() {
        // 準備測試數據
        String loginUserId = "testUser123";
        User mockUser = new User();
        mockUser.setUserId(loginUserId);
        mockUser.setUserName("Test User");

        // 設置模擬行為
        when(userDao.getUser(loginUserId)).thenReturn(mockUser);

        // 創建請求對象
        ToAddPageRequest request = new ToAddPageRequest();
        request.setLoginUserId(loginUserId);

        // 執行測試
        ToAddPageResponse response = toAddPageService.getResponse(request);


        // 驗證結果
        assertNotNull("Response should not be null", response);
        assertEquals("User ID should match", loginUserId, response.getUserId());
        assertEquals("Username should match", "Test User", response.getUserName());
        assertNotNull("eDate should not be null", response.geteDate());

        logger.log(Level.INFO, "eDate: {0}", response.geteDate());
        assertTrue("eDate should match today's date pattern",
                response.geteDate().matches("\\d{4}\\d{2}\\d{2}")); // 假設日期格式為 yyyyMMdd

        // 驗證 userDao.getUser 是否被調用
        verify(userDao).getUser(loginUserId);
    }

    @Test(expected = NullPointerException.class)
    public void testToAddPage_UserNotFound() {
        // 準備測試數據
        String loginUserId = "nonExistentUser";

        // 設置模擬行為返回 null
        when(userDao.getUser(loginUserId)).thenReturn(null);

        // 創建請求對象
        ToAddPageRequest request = new ToAddPageRequest();
        request.setLoginUserId(loginUserId);

        // 執行測試 - 應該拋出 NullPointerException
        toAddPageService.getResponse(request);
    }

    @Test
    public void testToAddPage_WithNullRequest() {
        try {
            toAddPageService.getResponse(null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true); // 預期會拋出異常
        }
    }
}