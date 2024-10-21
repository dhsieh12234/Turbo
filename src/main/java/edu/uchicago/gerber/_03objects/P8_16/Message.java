package edu.uchicago.gerber._03objects.P8_16;

public class Message {
    private final String sender;
    private final String recipient;
    private String message;

    public Message(String sender, String recipient) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = "";
    }
    public void append(String newLine) {
        if (!message.isEmpty()) {
            message += "\n";
        }
        message += newLine;
    }

    public String toString() {
        return "From: " + sender + "\nTo: " + recipient + "\n" + message;
    }
}
