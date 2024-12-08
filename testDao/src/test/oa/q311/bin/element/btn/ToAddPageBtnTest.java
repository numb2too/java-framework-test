package oa.q311.bin.element.btn;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import oa.common.model.User;
import oa.q311.bin.dao.UserDao;
import oa.q311.bin.response.MyResponse;

import java.util.Map;

public class ToAddPageBtnTest {

    @Mock
    private UserDao userDao;

    private ToAddPageBtn toAddPageBtn;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        toAddPageBtn = new ToAddPageBtn(userDao);
    }

    @Test
    public void testClick_Success() {
        // Arrange
        String loginUserId = "12345";
        User mockUser = new User();
        mockUser.setUserId("12345");
        mockUser.setUserDepId("DEP001");

        when(userDao.getUser(" and empId = " + loginUserId)).thenReturn(mockUser);

        // Act
        MyResponse response = toAddPageBtn.click(loginUserId);

        // Assert
        assertNotNull("Response should not be null", response);
        assertEquals("Response code should be 200", "200", response.getCode());

        Map<String, String> responseData = (Map<String, String>) response.getData();
        assertNotNull("Response data should not be null", responseData);
        assertEquals("Employee ID should match", mockUser.getUserId(), responseData.get("empId"));
        assertEquals("Department ID should match", mockUser.getUserDepId(), responseData.get("depId"));

        verify(userDao).getUser(" and empId = " + loginUserId);
    }

    @Test
    public void testClick_WithNullUser() {
        // Arrange
        String loginUserId = "12345";
        when(userDao.getUser(" and empId = " + loginUserId)).thenReturn(new User());

        // Act
        MyResponse response = toAddPageBtn.click(loginUserId);

        // Assert
        assertNotNull("Response should not be null", response);
        assertEquals("Response code should be 200", "200", response.getCode());

        Map<String, String> responseData = (Map<String, String>) response.getData();
        assertNotNull("Response data should not be null", responseData);
        assertNull("Employee ID should be null", responseData.get("empId"));
        assertNull("Department ID should be null", responseData.get("depId"));

        verify(userDao).getUser(" and empId = " + loginUserId);
    }

    @Test
    public void testClick_WithEmptyUser() {
        // Arrange
        String loginUserId = "12345";
        User emptyUser = new User();
        when(userDao.getUser(" and empId = " + loginUserId)).thenReturn(emptyUser);

        // Act
        MyResponse response = toAddPageBtn.click(loginUserId);

        // Assert
        assertNotNull("Response should not be null", response);
        assertEquals("Response code should be 200", "200", response.getCode());

        Map<String, String> responseData = (Map<String, String>) response.getData();
        assertNotNull("Response data should not be null", responseData);
        assertNull("Employee ID should be null", responseData.get("empId"));
        assertNull("Department ID should be null", responseData.get("depId"));

        verify(userDao).getUser(" and empId = " + loginUserId);
    }
}