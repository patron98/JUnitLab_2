package be.intecbrussel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageServiceTest {

    private final MessageService messageService = new MessageService();

    @DisplayName("Testing MessageService.getAll()")
    @Test
    void testGetAll() {
        List<String> expected = new ArrayList<>();
        {
            for (int i = 0; i < 100; i++) {
                String formattedText = "Message: {" + "\n" +
                        "From: " + "sender" + i + "@mail.be" + "\n" +
                        "To: " + "receiver" + i + "@mail.be" + "\n" +
                        "Header: " + "header " + i + "\n" +
                        "Body" + "body " + i + "\n" + "}";
                expected.add(formattedText);
            }
        }
        var actual = messageService.getAll();
        assertLinesMatch(expected, actual);
    }

    //------------------------------------------------------------------------

    @DisplayName("Testing MessageService.getByFrom() when null")
    @Test
    void getByFromShouldFailWhenNUll() {
        assertThrows(IllegalArgumentException.class, () -> {
            messageService.getByFrom(null);
        });
    }

    @DisplayName("Testing MessageService.getByFrom() when no '.' is in the email")
    @Test
    void getByFromShouldFailNoCorrectPointIsInEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            messageService.getByFrom("js@Gmailcom");
        });
    }

    @DisplayName("Testing MessageService.getByFrom() when no '@' is in the email")
    @Test
    void getByFromShouldFailNoCorrectAtSignIsInEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            messageService.getByFrom("js.Gmailcom");
        });
    }

    //------------------------------------------------------------------------

    @DisplayName("Testing MessageService.send() when from input is null")
    @Test
    void sendShouldFailWhenFromIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            messageService.send(null, "Bob@gmail.com", "bla", "bla");
        });
    }

    @DisplayName("Testing MessageService.send() when to input is null")
    @Test
    void sendShouldFailWhenToIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            messageService.send("Bob@gmail.com", null , "bla", "bla");
        });
    }

    @DisplayName("Testing MessageService.send() when header input is null")
    @Test
    void sendShouldFailWhenHeaderIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            messageService.send("Bob@gmail.com", "Bab@Gmail.com" , null, "bla");
        });
    }

    @DisplayName("Testing MessageService.send() when body input is null")
    @Test
    void sendShouldFailWhenBodyIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            messageService.send("Bob@gmail.com", "Bab@Gmail.com" , "bla", null);
        });
    }


    @DisplayName("Testing MessageService.send() when from has no '.' is in email")
    @Test
    void sendShouldFailWhenFromEmailHasNoPoint() {
        assertThrows(IllegalArgumentException.class, () -> {
            messageService.send("js@gmail", "bob@gmail.com", "blablabla", "blablabla");
        });
    }

    @DisplayName("Testing MessageService.send() when from has no '.' is in email")
    @Test
    void sendShouldFailWhenToEmailHasNoPoint() {
        assertThrows(IllegalArgumentException.class, () -> {
            messageService.send("js@gmail.com", "bob@gmail", "blablabla", "blablabla");
        });
    }

    @DisplayName("Testing MessageService.send() when from has no '@' is in email")
    @Test
    void sendShouldFailWhenFromEmailHasNoAtSign() {
        assertThrows(IllegalArgumentException.class, () -> {
            messageService.send("js.gmail", "bob@gmail.com", "blablabla", "blablabla");
        });
    }

    @DisplayName("Testing MessageService.send() when to has no '@' is in email")
    @Test
    void sendShouldFailWhenToEmailHasNoAtSign() {
        assertThrows(IllegalArgumentException.class, () -> {
            messageService.send("js@gmail.com", "bobgmail.com", "blablabla", "blablabla");
        });
    }
}