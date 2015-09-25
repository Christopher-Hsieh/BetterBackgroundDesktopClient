/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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

    String[][] test_entries = new String[][] {
        // 0 & 1 valid
        // 2 Invalid username
        // 3 empty password
        // 4 empty user
        // 5 unsupported chars
        
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
        {"#$%^@live.or$","@#$@"}
    };
    
    // Test login entries
    @Test
    public void testLoginEntries() {
        
    }
    
}
