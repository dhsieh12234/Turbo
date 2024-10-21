package edu.uchicago.gerber._03objects.P8_16;

import java.util.ArrayList;

public class Mailbox {
    private ArrayList<Message> messages;
    public Mailbox() {
        messages = new ArrayList<>();
    }
    public void addMessage(Message m) {
        messages.add(m);
    }
    public Message getMessage(int i) {
        if (i >= 0 && i < messages.size()) {
            return messages.get(i);
        }
        return null;
    }
    public void removeMessage(int i) {
        if (i >= 0 && i < messages.size()) {
            messages.remove(i);
        } else {
            System.out.println("Invalid index. No message removed.");
        }
    }
}