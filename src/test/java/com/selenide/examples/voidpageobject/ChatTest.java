package com.selenide.examples.voidpageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;

public class ChatTest {
    @BeforeClass

    public static void setup() {
        Configuration.baseUrl = "https://chat-stage.aimprosoft.com/index.html#";
        open("/");
    }


    @Test
    public void loginAsValidUser() {
        new LoginPage().loginAs("testuser1@email.com", "qwerty1");
        ChatPage chatPage = new ChatPage();
        Assert.assertEquals(title(), "Chat AimChat v.3.24.0");
        chatPage.preloader.waitWhile(visible, 6000);
        chatPage.chanelName.shouldHave(text("General"));
    }

    @Test
    public void createRoom() {
        new LoginPage().loginAs("testuser1@email.com", "qwerty1");
        ChatPage chatPage = new ChatPage();
        Assert.assertEquals(title(), "Chat AimChat v.3.24.0");
        chatPage.preloader.waitWhile(visible,6000);
        chatPage.addRoomBtn.click();
        chatPage.createRoom("selenide");
        chatPage.chanelName.waitUntil(visible, 6000);
        chatPage.chanelName.shouldHave(text("selenide"));
        chatPage.chanelNamePublic.shouldHave(text("selenide"));
        chatPage.startText.shouldHave(text("Room conversation has started here"));
    }

    @Test
    public void writeMessage() {
        new LoginPage().loginAs("testuser1@email.com", "qwerty1");
        ChatPage chatPage = new ChatPage();
        Assert.assertEquals(title(), "Chat AimChat v.3.23.2");
        chatPage.preloader.waitWhile(visible,6);
        chatPage.openRoom("selenide");
        chatPage.writeMsg("test");
        chatPage.messagesList().findBy(Condition.text("test"));
    }

    @Test
    public void editMessage() {
        new LoginPage().loginAs("testuser1@email.com", "qwerty1");
        ChatPage chatPage = new ChatPage();
        Assert.assertEquals(title(), "Chat AimChat v.3.23.2");
        chatPage.preloader.waitWhile(visible,6);
        chatPage.openRoom("selenide");
        chatPage.editMessageByText( "test", "selenide");
        chatPage.messagesList().findBy(Condition.text("testselenide"));
    }

    @Test
    public void deleteMessage() {
        new LoginPage().loginAs("testuser1@email.com", "qwerty1");
        ChatPage chatPage = new ChatPage();
        Assert.assertEquals(title(), "Chat AimChat v.3.23.2");
        chatPage.preloader.waitWhile(visible,6000);
        chatPage.openRoom("selenide");
        chatPage.deleteMessageByText( "testselenide");
        chatPage.messagesList().findBy(Condition.not(text("testselenide")));
    }

    @Test
    public void deleteRoom() {
        new LoginPage().loginAs("testuser1@email.com", "qwerty1");
        ChatPage chatPage = new ChatPage();
        Assert.assertEquals(title(), "Chat AimChat v.3.24.0");
        chatPage.preloader.waitWhile(visible,6000);
        chatPage.openRoom("selenide");
        chatPage.deleteRoom();
        chatPage.roomsList().findBy(Condition.not(text("selenide")));
    }
}
