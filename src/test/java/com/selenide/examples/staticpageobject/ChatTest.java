package com.selenide.examples.staticpageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


/**
 * Проект для обучения Selenide
 * В тестовом классе реализованны базовые тесты
 *
 * класс LoginPage реализован путем Паттерна Void Page Object (у него есть конструктор, все методы класса void)
 * класс ChatPage реализован путем Паттерна static Page Object
 */

 public class ChatTest {

    @BeforeClass

    public static void setup() {
        Configuration.baseUrl = "https://chat-stage.aimprosoft.com/index.html#";
        open("/");
    }

    @Test
    public void loginAsValidUser() {
        new LoginPage().loginAs("testuser1@email.com", "qwerty1");
        Assert.assertEquals(title(), "Chat AimChat v.3.23.2");
        ChatPage.preloader.waitWhile(visible,6);
        ChatPage.chanelName.shouldHave(text("General"));
    }

    @Test
     public void createRoom() {
        new LoginPage().loginAs("testuser1@email.com", "qwerty1");
        Assert.assertEquals(title(), "Chat AimChat v.3.23.2");
        ChatPage.preloader.waitWhile(visible,6);
        ChatPage.addRoomBtn.click();
        ChatPage.createRoom("selenide");
        ChatPage.chanelName.waitUntil(visible, 6);
        ChatPage.chanelName.shouldHave(text("selenide"));
        ChatPage.chanelNamePublic.shouldHave(text("selenide"));
        ChatPage.startText.shouldHave(text("Room conversation has started here"));
    }

    @Test
     public void writeMessage() {
        new LoginPage().loginAs("testuser1@email.com", "qwerty1");
        Assert.assertEquals(title(), "Chat AimChat v.3.23.2");
        ChatPage.preloader.waitWhile(visible,6);
        ChatPage.openRoom("selenide");
        ChatPage.writeMsg("test");
        ChatPage.messagesList().findBy(Condition.text("test"));
    }

    @Test
     public void editMessage() {
        new LoginPage().loginAs("testuser1@email.com", "qwerty1");
        Assert.assertEquals(title(), "Chat AimChat v.3.23.2");
        ChatPage.preloader.waitWhile(visible,6);
        ChatPage.openRoom("selenide");
        ChatPage.editMessageByText( "test", "selenide");
        ChatPage.messagesList().findBy(Condition.text("testselenide"));
    }

    @Test
     public void deleteMessage() {
        new LoginPage().loginAs("testuser1@email.com", "qwerty1");
        Assert.assertEquals(title(), "Chat AimChat v.3.23.2");
        ChatPage.preloader.waitWhile(visible,6);
        ChatPage.openRoom("selenide");
        ChatPage.deleteMessageByText( "testselenide");
        ChatPage.messagesList().findBy(Condition.not(text("testselenide")));
    }

    @Test
     public void deleteRoom() {
        new LoginPage().loginAs("testuser1@email.com", "qwerty1");
        Assert.assertEquals(title(), "Chat AimChat v.3.23.2");
        ChatPage.preloader.waitWhile(visible,6);
        ChatPage.openRoom("selenide");
        ChatPage.deleteRoom();
        ChatPage.roomsList().findBy(Condition.not(text("selenide")));
//        TODO не работает
    }
}