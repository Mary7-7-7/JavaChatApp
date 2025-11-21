/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package poe3;

import POE3.MessageInfo;
import java.util.ArrayList;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author RC_Student_Lab
 */
public class MessageInfoNGTest {
    
    @Test
    public void testAddSentMessage() {
        System.out.println("addSentMessage");
        String recipient = "+27834557896";
        String message = "Did you get the cake?";
        MessageInfo instance = null;
        String expResult = "Message Sent";
        String result = instance.addSentMessage(recipient, message);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
     
    }

    /**
     * Test of addStoredMessage method, of class MessageInfo.
     */
    @Test
    public void testAddStoredMessage() {
        System.out.println("addStoredMessage");
        String messageId = "";
        String recipient = "+27838884567";
        String message = "Where are you ? You are late ! I asked you to be on time";
        MessageInfo instance = null;
        instance.addStoredMessage(messageId, recipient, message);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of disregardMessage method, of class MessageInfo.
     */
    @Test
    public void testDisregardMessage() {
        System.out.println("disregardMessage");
        String messageId = "";
        String recipient = "+27834484567";
        String message = "Yohooo, I am at your gate";
        MessageInfo instance = null;
        instance.disregardMessage(messageId, recipient, message);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of displaySenderAndRecipient method, of class MessageInfo.
     */
    @Test
    public void testDisplaySenderAndRecipient() {
        System.out.println("displaySenderAndRecipient");
        MessageInfo instance = null;
        String expResult = "";
        String result = instance.displaySenderAndRecipient();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
      
    }

    /**
     * Test of findLongestSentMessage method, of class MessageInfo.
     */
    @Test
    public void testFindLongestSentMessage() {
        System.out.println("findLongestSentMessage");
        MessageInfo instance = null;
        String expResult = "";
        String result = instance.findLongestSentMessage();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
      
    }

    /**
     * Test of searchMessageID method, of class MessageInfo.
     */
    @Test
    public void testSearchMessageID() {
        System.out.println("searchMessageID");
        String messageId = "message 4";
        MessageInfo instance = null;
        String expResult = "";
        String result = instance.searchMessageID(messageId);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of searchMessages method, of class MessageInfo.
     */
    @Test
    public void testSearchMessages() {
        System.out.println("searchMessages");
        String recipient = "+27838884567";
        MessageInfo instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.searchMessages(recipient);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of deleteMessageByHash method, of class MessageInfo.
     */
    @Test
    public void testDeleteMessageByHash() {
        System.out.println("deleteMessageByHash");
        String hash = "";
        MessageInfo instance = null;
        boolean expResult = false;
        boolean result = instance.deleteMessageByHash(hash);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
      
    }

    /**
     * Test of generateSentReport method, of class MessageInfo.
     */
    @Test
    public void testGenerateSentReport() {
        System.out.println("generateSentReport");
        MessageInfo instance = null;
        String expResult = "";
        String result = instance.generateSentReport();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of loadStoredMessagesFromJson method, of class MessageInfo.
     */
    @Test
    public void testLoadStoredMessagesFromJson() {
        System.out.println("loadStoredMessagesFromJson");
        String filename = "";
        MessageInfo instance = null;
        String expResult = "";
        String result = instance.loadStoredMessagesFromJson(filename);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
}
