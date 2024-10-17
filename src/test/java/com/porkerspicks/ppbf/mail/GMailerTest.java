package com.porkerspicks.ppbf.mail;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.mail.Session;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GMailerTest {

    private final static String SUBJECT = "Test Subject";
    private final static String BODY_TEXT = "Test Body";

    @Mock
    private Gmail service;

    @Mock
    private EmailCreator emailCreator;

    @InjectMocks
    private GMailer gMailer;

    private Gmail.Users users;
    private Gmail.Users.Messages messages;
    private Gmail.Users.Messages.Send sendRequest;

    @BeforeEach
    public void setUp() throws IOException {
        users = mock(Gmail.Users.class);
        messages = mock(Gmail.Users.Messages.class);
        sendRequest = mock(Gmail.Users.Messages.Send.class);
    }

    @Test
    public void testSendMessage() throws MessagingException, IOException {

        when(service.users()).thenReturn(users);
        when(service.users().messages()).thenReturn(messages);
        when(messages.send(anyString(), any(Message.class))).thenReturn(sendRequest);
        when(sendRequest.execute()).thenReturn(new Message().setId("12345"));

        Message mockMessage = new Message();
        when(emailCreator.createEmail(SUBJECT, BODY_TEXT)).thenReturn(new MimeMessage((Session) null));
        when(emailCreator.createMessageWithEmail(any())).thenReturn(mockMessage);

        Message sentMessage = gMailer.sendMessage(SUBJECT, BODY_TEXT);

        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);
        verify(messages).send(eq("me"), messageCaptor.capture());
        verify(sendRequest).execute();

        assertNotNull(sentMessage);
        assertEquals("12345", sentMessage.getId());
    }

    @Test
    public void testSendMessageThrowsMessagingException() throws MessagingException, IOException {

        when(emailCreator.createEmail(SUBJECT, BODY_TEXT)).thenThrow(new MessagingException("Mock Exception"));

        MessagingException exception = assertThrows(MessagingException.class, () -> {
            gMailer.sendMessage(SUBJECT, BODY_TEXT);
        });

        assertEquals("Mock Exception", exception.getMessage());
    }

    @Test
    public void testSendMessageThrowsIOException() throws MessagingException, IOException {

        when(service.users()).thenReturn(mock(Gmail.Users.class));
        when(service.users().messages()).thenReturn(messages);
        when(messages.send(anyString(), any(Message.class))).thenReturn(sendRequest);

        when(emailCreator.createEmail(SUBJECT, BODY_TEXT)).thenReturn(new MimeMessage((Session) null));
        when(emailCreator.createMessageWithEmail(any())).thenReturn(new Message());
        when(sendRequest.execute()).thenThrow(new IOException("Mock Exception"));

        IOException exception = assertThrows(IOException.class, () -> {
            gMailer.sendMessage(SUBJECT, BODY_TEXT);
        });

        assertEquals("Mock Exception", exception.getMessage());
    }
}