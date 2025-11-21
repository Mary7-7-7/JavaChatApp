/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package poe3;

import POE3.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_Lab
 */
public class MainTest {
    
    public MainTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of userNameCheck method, of class Main.
     */
    @Test
    public void testUserNameCheck() {
        System.out.println("userNameCheck");
        String username = "";
        boolean expResult = false;
        boolean result = Main.userNameCheck(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of PasswordCheck method, of class Main.
     */
    @Test
    public void testPasswordCheck() {
        System.out.println("PasswordCheck");
        String password = "";
        boolean expResult = false;
        boolean result = Main.PasswordCheck(password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cellNumberCheck method, of class Main.
     */
    @Test
    public void testCellNumberCheck() {
        System.out.println("cellNumberCheck");
        String phone = "+2771863002";
        boolean expResult = false;
        boolean result = Main.cellNumberCheck(phone);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of signUp method, of class Main.
     */
    @Test
    public void testSignUp() {
        System.out.println("signUp");
        Main instance = new Main();
        instance.signUp();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class Main.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        Main instance = new Main();
        instance.login();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendMessage method, of class Main.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        Main instance = new Main();
        instance.sendMessage();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkMessageId method, of class Main.
     */
    @Test
    public void testCheckMessageId() {
        System.out.println("checkMessageId");
        Main instance = new Main();
        String expResult = "";
        String result = instance.checkMessageId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMessageHash method, of class Main.
     */
    @Test
    public void testCreateMessageHash() {
        System.out.println("createMessageHash");
        String messageId = "";
        int messageNum = 0;
        Main instance = new Main();
        String expResult = "";
        String result = instance.createMessageHash(messageId, messageNum);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnTotalMessages method, of class Main.
     */
    @Test
    public void testReturnTotalMessages() {
        System.out.println("returnTotalMessages");
        Main instance = new Main();
        int expResult = 0;
        int result = instance.returnTotalMessages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printMessages method, of class Main.
     */
    @Test
    public void testPrintMessages() {
        System.out.println("printMessages");
        Main instance = new Main();
        instance.printMessages();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveMessagesToJson method, of class Main.
     */
    @Test
    public void testSaveMessagesToJson() {
        System.out.println("saveMessagesToJson");
        Main instance = new Main();
        instance.saveMessagesToJson();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of escapeJson method, of class Main.
     */
    @Test
    public void testEscapeJson() {
        System.out.println("escapeJson");
        String text = "";
        Main instance = new Main();
        String expResult = "";
        String result = instance.escapeJson(text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
