package com.selenide.examples.fluentpageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.codeborne.selenide.Condition.*;
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
        new LoginPage().loginAs("testuser1@email.com", "qwerty1")
                .preloaderIsInVisible(6000)
                .chanelName.shouldHave(text("General"));

        Assert.assertEquals(title(), "Chat AimChat v.3.24.0");
    }

    @Test
    public void createRoom() {
        new LoginPage().loginAs("testuser1@email.com", "qwerty1")
                .preloaderIsInVisible(6000)
                .clickAddRoomBtn()
                .createRoom("selenide")
                .chanelNameIsVisible()
                .chanelNameHasText("selenide")
                .chanelNamePublicShouldHaveText("selenide")
                .startTextShouldHaveText("Room conversation has started here");
    }

    @Test
    public void writeMessage() {
        new LoginPage().loginAs("testuser1@email.com", "qwerty1")
                .preloaderIsInVisible(6000)
                .openRoom("selenide")
                .writeMsg("test")
                .messagesList().findBy(Condition.text("test"));
    }


    @Test
    public void deleteMessage() {
        new LoginPage().loginAs("testuser1@email.com", "qwerty1")
                .preloaderIsInVisible(6000)
                .openRoom("selenide")
                .deleteMessageByText("textselenide")
                .messagesList().findBy(Condition.not(text("testselenide")));
    }


    @Test
    public void deleteRoom() {
        new LoginPage().loginAs("testuser1@email.com", "qwerty1")
                .preloaderIsInVisible(6000)
                .openRoom("selenide")
                .deleteRoom()
                .roomsList().findBy(Condition.not(text("selenide")));
    }

}
