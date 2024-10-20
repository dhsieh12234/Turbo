package edu.uchicago.gerber._03objects.P8_16;

public class Driver {
    public static void main(String[] args) {
        Mailbox mailbox = new Mailbox();

        // Create some messages
        Message message1 = new Message("Alice", "Bob");
        message1.append("Hi Bob, how are you?");
        message1.append("Let's meet for coffee.");

        Message message2 = new Message("Carol", "Dave");
        message2.append("Don't forget the meeting tomorrow.");

        // Add messages to the mailbox
        mailbox.addMessage(message1);
        mailbox.addMessage(message2);

        // Retrieve and print the first message
        Message retrievedMessage = mailbox.getMessage(0);
        if (retrievedMessage != null) {
            System.out.println("Retrieved Message:\n" + retrievedMessage);
        }

        // Remove the second message
        mailbox.removeMessage(1);

        // Try to retrieve the removed message
        Message removedMessage = mailbox.getMessage(1);
        if (removedMessage == null) {
            System.out.println("Message not found.");
        }
    }
}
