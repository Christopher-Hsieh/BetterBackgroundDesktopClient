/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import desktopgui.DesktopLogin;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Chris
 */
public class TestLoginJUnit {
    
    public TestLoginJUnit() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    String[][] testEntries = new String[][] {
        // 0 & 1 valid
        // 2 Invalid username
        // 3 empty password
        // 4 empty user
        // 5 unsupported chars
        // 6 & 7 past char limit
        
        // Two entries that are valid and will be passable to meteor for validation
        {"valid@purdue.edu","password"},
        {"valid2@google.com","pass"},
        // Invalid Username
        {"invalid.com.google","pass"},
        // Empty password
        {"invalid@live.com",""},
        // Empty username
        {" ","pass"},
        // Unsupported characters
        {"#$%^@live.or$","@#$@"},
        // Past char limit (254)
        {"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx","x"},
        {"x","xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"}
    
    };
    
    // Test login entries
    @Test
    public void testLoginEntries() {
        DesktopLogin DL = new DesktopLogin();
        
        // Ensure the window is created
        DL.setVisible(true);
        assertEquals(true, DL.isVisible());
        
        // Complete login verification
        assertEquals(true, DL.loginValidCheck(testEntries[0][0], testEntries[0][1].toCharArray()));
        assertEquals(true, DL.loginValidCheck(testEntries[1][0], testEntries[1][1].toCharArray()));
        assertEquals(false, DL.loginValidCheck(testEntries[2][0], testEntries[2][1].toCharArray()));
        assertEquals(false, DL.loginValidCheck(testEntries[3][0], testEntries[3][1].toCharArray()));
        assertEquals(false, DL.loginValidCheck(testEntries[4][0], testEntries[4][1].toCharArray()));
        assertEquals(false, DL.loginValidCheck(testEntries[5][0], testEntries[5][1].toCharArray()));
        assertEquals(false, DL.loginValidCheck(testEntries[6][0], testEntries[6][1].toCharArray()));
        assertEquals(false, DL.loginValidCheck(testEntries[7][0], testEntries[7][1].toCharArray()));
       
        // Ensure Login window is destroyed properly
        DL.dispose();
        assertEquals(false, DL.isDisplayable());
    }
    
}
