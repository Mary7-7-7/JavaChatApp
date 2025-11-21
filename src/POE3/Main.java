/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POE3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_Lab
 */
public class Main {
    String registeredUser;
    String registeredUserPassword;
    String registeredUserCellNumber;
    ArrayList<String> sentMessage = new ArrayList<>();
    ArrayList<String> storeMessage = new ArrayList<>();
    ArrayList<String> disregardMessage = new ArrayList<>();
    ArrayList<String> recipientPhone = new ArrayList<>();
    ArrayList<String> hashID = new ArrayList<>();
    ArrayList<String> uniqueMessageID = new ArrayList<>();
    ArrayList<String> messageSequence = new ArrayList<>();
    Random random = new Random();
    int messageCounter = 0;

    public static boolean userNameCheck(String username) {
        if (username.contains("_") && username.length() <= 5) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean PasswordCheck(String password) {
        if (password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean cellNumberCheck(String phone) {
        if (phone.matches("^(\\+27|27|0027)[0-9]{1,10}$")) {
            return true;
        } else {
            return false;
        }
    }

    public void signUp() {
        String userName;
        String Password;
        String cellNumber;

        do {
            userName = JOptionPane.showInputDialog("Enter username:");
            if (userName == null) {
                JOptionPane.showMessageDialog(null, "Registration cancelled.", "Cancel", JOptionPane.CANCEL_OPTION);
                return;
            }
            // username
            if (!userNameCheck(userName)) {
                JOptionPane.showMessageDialog(null,
                        "Invalid username, please ensure that your username contains \n-An underscore \n-No more than five characters in length",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (!userNameCheck(userName));
        registeredUser = userName;
        // password
        do {
            Password = JOptionPane.showInputDialog("Enter password:");
            if (Password == null) {
                JOptionPane.showMessageDialog(null, "Registration cancelled.", "Cancel", JOptionPane.CANCEL_OPTION);
                return;
            }

            if (!PasswordCheck(Password)) {
                JOptionPane.showMessageDialog(null,
                        "Invalid password, please ensure that the password contains \n-At least eight characters \n-Acapital letter \n-A number \n-A special character.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (!PasswordCheck(Password));
        registeredUserPassword = Password;

        // cell number
        do {
            cellNumber = JOptionPane.showInputDialog("Enter cellphone: ");
            if (cellNumber == null) {
                JOptionPane.showMessageDialog(null, "Registration cancelled.", "Cancel", JOptionPane.CANCEL_OPTION);
                return;
            }

            if (!cellNumberCheck(cellNumber)) {
                JOptionPane.showMessageDialog(null, "Cellphone incorrectly formatted or does not containt international code",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (!cellNumberCheck(cellNumber));
        registeredUserCellNumber = cellNumber;

        JOptionPane.showMessageDialog(null, "Registration successful.", "Sucessful", JOptionPane.INFORMATION_MESSAGE);
    }

    // login
    public void login() {
        if (registeredUser == null || registeredUserPassword == null) {
            JOptionPane.showMessageDialog(null, "Create an account before sign in", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String userName = JOptionPane.showInputDialog("Enter username to login:");
        if (userName == null) {
            JOptionPane.showMessageDialog(null, "Login cancelled.", "Cancel", JOptionPane.CANCEL_OPTION);
            return;
        }

        String Password = JOptionPane.showInputDialog("Enter password:");
        if (Password == null) {
            JOptionPane.showMessageDialog(null, "Login cancelled.", "Cancel", JOptionPane.CANCEL_OPTION);
            return;
        }

        if (userName.equals(registeredUser) && Password.equals(registeredUserPassword)) {
            MessageInfo mi = new MessageInfo(this);
            int choices;
            do {
                String[] options = {"Send Message", "Manage Messages", "Coming Soon", "Quit"};
                choices = JOptionPane.showOptionDialog(null, "Welcome to QuickChat!", "Options",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                if (choices == -1) {
                    choices = 3;
                }
                switch (choices) {
                    case 0 -> sendMessage();
                    case 1 -> manageMessagesMenu(mi);
                    case 2 -> JOptionPane.showMessageDialog(null, "Coming Soon", "Information", JOptionPane.INFORMATION_MESSAGE);
                    case 3 -> {
                    }
                }
            } while (choices != 3);
            return;
        } else {
            JOptionPane.showMessageDialog(null, "Your credentials are incorrect, Please try again", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void sendMessage() {
        final int MAX_MESSAGE_LENGTH = 250;
        String recipientNumber;
        do {
            recipientNumber = JOptionPane.showInputDialog(null, "Enter recipient's number");
            if (recipientNumber == null) {
                return;
            }
            if (!cellNumberCheck(recipientNumber)) {
                JOptionPane.showMessageDialog(null, "Invalid number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (!cellNumberCheck(recipientNumber));

        String messageNumber = JOptionPane.showInputDialog("How many messages would you like to send?");
        if (messageNumber == null) {
            return;
        }
        try {
            int messageCount = Integer.parseInt(messageNumber);
            String message = null;
            for (int i = 0; i < messageCount; i++) {
                message = JOptionPane.showInputDialog(null, String.format("Enter your message (%d of %d)", i + 1, messageCount));
                if (message == null) {
                    return;
                }
                if (message.length() > MAX_MESSAGE_LENGTH) {
                    JOptionPane.showMessageDialog(null, "Enter message less than 250 characters", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String[] options = {"SEND", "STORE", "DISREGARD"};
                int actions = JOptionPane.showOptionDialog(null, "What would you like to do with the message?", "Message Action",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                String id = checkMessageId();
                String hash = createMessageHash(id, i + 1);

                recipientPhone.add(recipientNumber);
                uniqueMessageID.add(id);
                hashID.add(hash);

                switch (actions) {
                    case 0 -> {
                        sentMessage.add(message);
                        recipientPhone.add(recipientNumber);
                        uniqueMessageID.add(id);
                        hashID.add(hash);
                        messageCounter++;
                        messageSequence.add(String.valueOf(messageCounter));
                        JOptionPane.showMessageDialog(null, "Message sent successfully. \nMESSAGE ID:" + id + "\nHash ID:" + hash);
                    }
                    case 1 -> {
                        storeMessage.add(message);
                        JOptionPane.showMessageDialog(null, "Message stored successfully", "Success", JOptionPane.DEFAULT_OPTION);
                    }
                    case 2 -> {
                        disregardMessage.add(message);
                        JOptionPane.showMessageDialog(null, "Message disregarded", "Cancel", JOptionPane.DEFAULT_OPTION);
                    }
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // end of method
    public String checkMessageId() {
            int nextNumber = uniqueMessageID.size() + 1;
            String Id = "MESSAGE"+ nextNumber;
           uniqueMessageID.add(Id);
        return Id;
    }

    public String createMessageHash(String messageId, int messageNum) {
        return messageId.substring(0, 2) + ":" + messageNum;
    }

    public int returnTotalMessages() {
        return sentMessage.size();
    }

    public void printMessages() {
        if (sentMessage.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No message was sent", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        StringBuilder display = new StringBuilder("\n*** SENT MESSAGES ***\n");
        for (int i = 0; i < sentMessage.size(); i++) {
            display.append("Message:.  ").append(":\n")
                    .append("Message HashID:  ").append(hashID.get(i).toUpperCase()).append("\n")
                    .append("Message ID:  ").append(uniqueMessageID.get(i)).append("\n")
                    .append("Message content:  ").append(sentMessage.get(i)).append("\n")
                    .append("Recipient:  ").append(recipientPhone.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(null, display.toString());

    }

    // Save all sent messages to JSON
    public void saveMessagesToJson() {
        StringBuilder json = new StringBuilder();
        json.append("{\n  \"messages\": [\n");
        for (int i = 0; i < sentMessage.size(); i++) {
            json.append("    \"").append(escapeJson(sentMessage.get(i))).append("\"");
            if (i < sentMessage.size() - 1) json.append(",");
            json.append("\n");
        }
        json.append("  ]\n}");

        try (FileWriter fw = new FileWriter("messages.json")) {
            fw.write(json.toString());
            JOptionPane.showMessageDialog(null, "Messages saved to messages.json");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving messages: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Escape JSON characters
    public String escapeJson(String text) {
        return text.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    public void manageMessagesMenu(MessageInfo mi) {
        while (true) {
         String[] menuItems = {
             "A) Details of Sender and Recipient",
             "B) Longest Message Sent",
             "C) Search Message ID", 
             "D) Search by Recipient",
             "E) Delete Message by using Hash",
             "F) Full Sent Messages Report",
             "G) Load stored messages",
    "Back"
         };
         
         javax.swing.JList<String> list = new javax.swing.JList<>(menuItems);
         list.setVisibleRowCount(menuItems.length);
         javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(list);
         
         int option = JOptionPane.showConfirmDialog(
        null,
        scroll,
        "Message Manager - Please Select Option",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.INFORMATION_MESSAGE 
         );
         
         if (option != JOptionPane.OK_OPTION) {
             return; 
         }
         
         int pick = list.getSelectedIndex();
         if (pick == -1) {
             JOptionPane.showMessageDialog(null, "Please Select Option.");
             continue;
         }
   
            
            switch (pick) {
                case 0 -> {
                    String text = mi.displaySenderAndRecipient();
           if (text == null || text.isEmpty()) text = "No messages sent";
                    JOptionPane.showMessageDialog(null, text, "Sender & Recipient", JOptionPane.INFORMATION_MESSAGE);
                }
                case 1 -> { // longest sent message
                    String longest = mi.findLongestSentMessage();
           if (longest == null) JOptionPane.showMessageDialog(null, "No sent messages.", "Longest", JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(null, longest, "Longest Sent Message", JOptionPane.INFORMATION_MESSAGE);
                }
                case 2 -> { // search message ID
                    String id = JOptionPane.showInputDialog("Enter Message ID to search:");
                    if (id != null) {
                        String textid = mi.searchMessageID(id);
                        if (textid == null) JOptionPane.showMessageDialog(null, "Message ID not found.", "Search", JOptionPane.WARNING_MESSAGE);
                        else JOptionPane.showMessageDialog(null, textid, "Search Result", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                case 3 -> { // search for recipient
                    String rnumber = JOptionPane.showInputDialog("Enter recipient number (e.g. +27718693002):");
                    if (rnumber != null) {
                        ArrayList<String> found = mi.searchMessages(rnumber);
                        if (found == null || found.isEmpty()) JOptionPane.showMessageDialog(null, "No messages for that recipient.", "Search by Recipient", JOptionPane.INFORMATION_MESSAGE);
                        else {
                            StringBuilder sb = new StringBuilder();
                            for (String s : found) sb.append(s).append("\n\n");
                            JOptionPane.showMessageDialog(null, sb.toString(), "Messages to " + rnumber, JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
                case 4 -> { // delete by hash
                    String deleteh= JOptionPane.showInputDialog("Enter message hash to delete:");
                    if (deleteh != null) {
                        boolean ok = mi.deleteMessageByHash(deleteh);
                        if (ok) JOptionPane.showMessageDialog(null, "Message deleted.", "Delete", JOptionPane.INFORMATION_MESSAGE);
                        else JOptionPane.showMessageDialog(null, "Hash not found.", "Delete", JOptionPane.WARNING_MESSAGE);
                    }
                }
                case 5 -> { // full report
                    String report = mi.generateSentReport();
                    if (report == null || report.isEmpty()) report = "No sent messages.";
                    JOptionPane.showMessageDialog(null, report, "Full Sent Report", JOptionPane.INFORMATION_MESSAGE);
                }
                case 6 -> { // load JSON (messages.json in project root)
                    String res = mi.loadStoredMessagesFromJson("messages.json");
                    JOptionPane.showMessageDialog(null, res, "JSON Load", JOptionPane.INFORMATION_MESSAGE);
                }
                default -> JOptionPane.showMessageDialog(null, "Invalid choice", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
