/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POE3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author RC_Student_Lab
 */
public class MessageInfo {
    String hash;
    String sender;
    String longest;
    public ArrayList<String> sentMessage;        
    public ArrayList<String> storeMessage;       
    public ArrayList<String> disregardMessage;   
    public ArrayList<String> recipientPhone;     
    public ArrayList<String> hashID;             
    public ArrayList<String> uniqueMessageID;  
    public ArrayList<String> messageSequence;
    public Main main;           
    public Random random = new Random();

    public MessageInfo(Main mainInstance) {
        main = mainInstance;                       
        sentMessage = main.sentMessage;            
        storeMessage = main.storeMessage;
        disregardMessage = main.disregardMessage;
        recipientPhone = main.recipientPhone;
        hashID = main.hashID;
        uniqueMessageID = main.uniqueMessageID;
        messageSequence = main.messageSequence;
    }

    public String addSentMessage(String recipient, String message) {
        final int MAX_MESSAGE_LENGTH = 250;
        if (recipient == null || message == null) 
            return "Invalid input";
        if (!Main.cellNumberCheck(recipient)) 
            return "Invalid number";
        if (message.length() > MAX_MESSAGE_LENGTH) return "Message too long";

        String id = main.checkMessageId();   // add ID to uniqueMessageID

        int msgNum = main.sentMessage.size() + 1;           // message number in sent folder
        hash = main.createMessageHash(id, msgNum);   // hash

        main.sentMessage.add(message);
        main.recipientPhone.add(recipient);
        main.hashID.add(hash);
        main.messageCounter++;
        main.messageSequence.add(String.valueOf(main.messageCounter));

        return "Message sent";
    }

    public void addStoredMessage(String messageId, String recipient, String message) {
        if (messageId == null || recipient == null || message == null) 
            return;
        if (main.uniqueMessageID.contains(messageId)) {
            System.out.println("Duplicate message cancelled");
            return;
        }
        hash = main.createMessageHash(messageId, main.storeMessage.size() + 1);
        main.uniqueMessageID.add(messageId);
        main.hashID.add(hash);
        main.storeMessage.add(message);
        main.recipientPhone.add(recipient);
    }

    public void disregardMessage(String messageId, String recipient, String message) {
        if (messageId == null || recipient == null || message == null) return;
        if (main.uniqueMessageID.contains(messageId)) {
            System.out.println("Duplicate message cancelled");
            return;
        }
        hash = main.createMessageHash(messageId, main.disregardMessage.size() + 1);
        main.uniqueMessageID.add(messageId);
        main.hashID.add(hash);
        main.disregardMessage.add(message);
        main.recipientPhone.add(recipient);
    }

    public String displaySenderAndRecipient() {
        sender = (main.registeredUserCellNumber == null) ? "UNKNOWN" : main.registeredUserCellNumber;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < main.sentMessage.size(); i++) {
            sb.append("Sender: ").append(sender)
              .append(" | Recipient: ").append(main.recipientPhone.get(i))
              .append("\n");
        }
        return sb.toString();
    }

    // Return the longest sent message 
    public String findLongestSentMessage() {
        if (main.sentMessage.isEmpty()) return null;
        longest = main.sentMessage.get(0);
        for (String lmessage : main.sentMessage) {
            if (lmessage.length() > longest.length()) longest = lmessage;
        }
        return longest;
    }

    // Search for a message ID
    public String searchMessageID(String messageId) {
        
        int index = main.uniqueMessageID.indexOf(messageId);
        if (index == -1) 
            return null; 
        
        int sentCount = main.sentMessage.size();
        int storeCount = main.storeMessage.size();
        int discardCount = main.disregardMessage.size();

        // For index inside the sent folder
        if (index < sentCount) {
            String recipient = main.recipientPhone.get(index);
            String msg = main.sentMessage.get(index);
            return "Recipient: " + recipient + "\nMessage: " + msg;
        }

        int indexAfterSent = index - sentCount;// If index is not sent folder, yoou subtract sentCount to get position inside the rest

        // For indexAfterSent inside the stored messages
        if (indexAfterSent < storeCount) {
            String recipient = main.recipientPhone.get(index);
            String sMessage = main.storeMessage.get(indexAfterSent);
            return "Recipient: " + recipient + "\nMessage: " + sMessage;
        }

        int indexInDisregard = indexAfterSent - storeCount;
        if (indexInDisregard >= 0 && indexInDisregard < discardCount) {
            String recipient = main.recipientPhone.get(index);
            String dMessage = main.disregardMessage.get(indexInDisregard);
            return "Recipient: " + recipient + "\nMessage: " + dMessage;
        }

        // fallback (shouldn't happen normally)
        String recipient = main.recipientPhone.get(index);
        return "Recipient: " + recipient + "\nMessage: (unknown mapping)";
    }

    //Search for all messages 
    public ArrayList<String> searchMessages(String recipient) {
        ArrayList<String> found = new ArrayList<>();
        for (int i = 0; i < main.recipientPhone.size(); i++) {
            if (main.recipientPhone.get(i).equals(recipient)) {
            if (i < main.sentMessage.size()) {
              found.add(main.sentMessage.get(i));
                } else {
             int rel = i - main.sentMessage.size();
                if (rel < main.storeMessage.size()) {
                    found.add(main.storeMessage.get(rel));
                } else {
             int rel2 = rel - main.storeMessage.size();
                if (rel2 < main.disregardMessage.size()) {
                    found.add(main.disregardMessage.get(rel2));
                }
                }
            }
            }
        }
        return found;
    }

    // e) Delete a message using the message hash
    public boolean deleteMessageByHash(String hash) {
        int index = main.hashID.indexOf(hash);
        if (index == -1) return false;

        int sentCount = main.sentMessage.size();
        int storeCount = main.storeMessage.size();
        int discardCount = main.disregardMessage.size();

        // If the message is in sent region
        if (index < sentCount) {
            main.sentMessage.remove(index);
            main.recipientPhone.remove(index);
            main.hashID.remove(index);
            main.uniqueMessageID.remove(index);
            if (main.messageCounter > 0) main.messageCounter--;
            if (!main.messageSequence.isEmpty()) main.messageSequence.remove(main.messageSequence.size() - 1);
            return true;
        }

        // If the message is in stored region
        int indexAfterSent = index - sentCount;
        if (indexAfterSent >= 0 && indexAfterSent < storeCount) {
            // remove from stored list by its local index
            main.storeMessage.remove(indexAfterSent);
            // remove global parallel entries
            main.uniqueMessageID.remove(index);
            main.hashID.remove(index);
            main.recipientPhone.remove(index);
            return true;
        }

        // If the message is in disregard region
        int indexInDisregard = indexAfterSent - storeCount;
        if (indexInDisregard >= 0 && indexInDisregard < discardCount) {
            main.disregardMessage.remove(indexInDisregard);
            main.uniqueMessageID.remove(index);
            main.hashID.remove(index);
            main.recipientPhone.remove(index);
            return true;
        }

        return false; // fallback
    }

    // f) Generate a full report of all sent messages 
    public String generateSentReport() {
        StringBuilder sb = new StringBuilder();
        sb.append(" SENT MESSAGES REPORT \n\n");
        sender = (main.registeredUserCellNumber == null) ? "UNKNOWN" : main.registeredUserCellNumber;
        for (int i = 0; i < main.sentMessage.size(); i++) {
            sb.append("Message Number: ").append(i + 1).append("\n");
            if (i < main.uniqueMessageID.size()) sb.append("Message ID: ").append(main.uniqueMessageID.get(i)).append("\n");
            if (i < main.hashID.size()) sb.append("Message Hash: ").append(main.hashID.get(i)).append("\n");
            sb.append("Sender: ").append(sender).append("\n");
            sb.append("Recipient: ").append(main.recipientPhone.get(i)).append("\n");
            sb.append("Content: ").append(main.sentMessage.get(i)).append("\n");
            sb.append("Length: ").append(main.sentMessage.get(i).length()).append("\n");
            sb.append("Status: Sent\n\n");
        }
        return sb.toString();
    }

public String loadStoredMessagesFromJson(String filename) {
    File f = new File("src/"+ filename);
    System.out.println("DEBUG: looking for messages.json at:" +f.getAbsolutePath());
    if (!f.exists()) {
        return "File not found: " + f.getAbsolutePath();
    }

    try (BufferedReader br = new BufferedReader(new FileReader(f))) {
        String line;
        StringBuilder full = new StringBuilder();
        while ((line = br.readLine()) != null) {
            full.append(line);
        }
        String content = full.toString().trim();

        // remove UTF-8 BOM if present
        if (content.startsWith("\uFEFF")) content = content.substring(1);

        // find the array bounds (first [ and the matching ])
        int start = content.indexOf('[');
        int end = content.indexOf(']', start);
        if (start == -1 || end == -1 || end <= start) {
            return "No messages found in JSON (no array brackets). File: " + f.getAbsolutePath();
        }

        String arrayPart = content.substring(start + 1, end);

        // parse quoted JSON strings properly (handles commas inside messages and escapes)
        ArrayList<String> itemsList = new ArrayList<>();
        boolean inString = false;
        StringBuilder cur = new StringBuilder();

        for (int i = 0; i < arrayPart.length(); i++) {
            char c = arrayPart.charAt(i);
            if (!inString) {
                if (c == '"') {
                    inString = true;
                    cur.setLength(0);
                }
                // else ignore characters outside strings (commas, spaces)
            } else {
                if (c == '\\') {
                    // handle escape
                    if (i + 1 < arrayPart.length()) {
                        i++;
                        char next = arrayPart.charAt(i);
                        switch (next) {
                            case '"' -> cur.append('"');
                            case '\\' -> cur.append('\\');
                            case 'n' -> cur.append('\n');
                            case 'r' -> cur.append('\r');
                            case 't' -> cur.append('\t');
                            default -> cur.append(next); // other escapes as-is
                        }
                    }
                } else if (c == '"') {
                    // end of string
                    inString = false;
                    itemsList.add(cur.toString());
                } else {
                    cur.append(c);
                }
            }
        }

        if (itemsList.isEmpty()) {
            return "No messages found in JSON (no string items). File: " + f.getAbsolutePath();
        }

        // add items to storeMessage (and generate id/hash/recipient as before)
        for (String s : itemsList) {
            String id = main.checkMessageId(); // adds to uniqueMessageID
            String recipient = "UNKNOWN";
            String hash = main.createMessageHash(id, main.storeMessage.size() + 1);
            main.hashID.add(hash);
            main.storeMessage.add(s);
            main.recipientPhone.add(recipient);
        }

        return "Loaded " + itemsList.size() + " messages from " + f.getAbsolutePath();
    } catch (IOException e) {
        return "Error reading file: " + e.getMessage();
    }
}
}
