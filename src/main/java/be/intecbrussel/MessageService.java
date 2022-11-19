package be.intecbrussel;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MessageService {

    private static final List<String> messages = new LinkedList<>();

    {
        for (int i = 0; i < 100; i++) {
            String formattedText = "Message: {" + "\n" +
                    "From: " + "sender" + i + "@mail.be" + "\n" +
                    "To: " + "receiver" + i + "@mail.be" + "\n" +
                    "Header: " + "header " + i + "\n" +
                    "Body" + "body " + i + "\n" + "}";
            messages.add(formattedText);
        }
    }

    public List<String> getAll() {

        return messages;
    }

    public List<String> getByFrom(String from) {

        if (from == null || from.isEmpty()) {
            throw new IllegalArgumentException(
                    "The sender from the message cannot be null or empty");
        }

        if (!from.contains("@") || !from.contains(".")) {
            throw new IllegalArgumentException(
                    "The sender from the message must be a valid email address");
        }

        List<String> filteredList = messages.stream()
                .filter(m -> m.contains("From: " + from))
                .collect(Collectors.toList());

        printAll(filteredList);

        return filteredList;
    }

    public List<String> getByTo(String to) {

        if (to == null || to.isEmpty()) {
            throw new IllegalArgumentException(
                    "The receiver from the message cannot be null or empty");
        }

        if (!to.contains("@") || !to.contains(".")) {
            throw new IllegalArgumentException(
                    "The receiver from the message must be a valid email address");
        }

        List<String> filteredList = messages.stream()
                .filter(m -> m.contains("To: " + to))
                .collect(Collectors.toList());

        printAll(filteredList);

        return filteredList;
    }

    private void printAll(List<String> messages) {
        for (var m : messages) {
            System.out.println(m);
        }
    }

    public void send(String from, String to, String header, String body) {

        if (from == null || from.isEmpty()) {
            throw new IllegalArgumentException(
                    "The sender from the message cannot be null or empty");
        }

        if (!from.contains("@") || !from.contains(".")) {
            throw new IllegalArgumentException(
                    "The sender from the message must be a valid email address");
        }

        if (to == null || to.isEmpty()) {
            throw new IllegalArgumentException(
                    "The receiver from the message cannot be null or empty");
        }

        if (!to.contains("@") || !to.contains(".")) {
            throw new IllegalArgumentException(
                    "The receiver from the message must be a valid email address");
        }

        if (header == null || header.isEmpty()) {
            throw new IllegalArgumentException(
                    "The header from the message cannot be null or empty");
        }

        if (body == null || body.isEmpty()) {
            throw new IllegalArgumentException(
                    "The body from the message cannot be null or empty");
        }

        // if the message body or header contains bad words throw an exception
        if (body.contains("bad") || header.contains("bad")) {
            throw new IllegalArgumentException(
                    "The message body or header cannot contain bad words");
        }

        String formattedText = "Message: {" + "\n" +
                "From: " + from + "\n" +
                "To: " + to + "\n" +
                "Header: " + header + "\n" +
                "Body" + body + "\n" + "}";
        messages.add(formattedText);
    }

}
