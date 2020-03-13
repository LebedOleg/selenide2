package com.selenide.examples.voidpageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class ChatPage {

    public  SelenideElement  preloader = $(byXpath("//div[@class='logo-spinner']")),
            chanelName = $(byXpath("//div[@class='channel-title']//div[@class='channel-room-name']")),
            chanelNamePublic = $(byXpath("//div[@class='chat-beginning-info']//div[@class='channel-name room-public']")),
            addRoomBtn = $(byClassName("add-room-button")),
            roomNameField = $(byName("roomName")),
            addBtn =$(byXpath("//div[@class='modal-dialog  ']//button[@class='btn btn-success']")),
            startText = $(byClassName("start-text"));
//                                    messageByText = $(byXpath("//div[@class='message-main-container']//span[text()='test']"));

    public ChatPage() {
        $(byClassName("team-name")).shouldBe(Condition.visible);
    }


    public  void createRoom(String roomName) {
        roomNameField.setValue(roomName);
        addBtn.click();
    }


    public  void openRoom(String roomName) {
        $$(byClassName("b-room")).findBy(Condition.text(roomName)).click();
        sleep(2000);
    }

    public  void writeMsg(String message) {
        SelenideElement textbox = $(byXpath("//div[contains(@class,'chat-container active')]//div[@class='notranslate public-DraftEditor-content']"));
        textbox.waitUntil(Condition.visible, 6);
        textbox.setValue(message).pressEnter();
    }

    public  ElementsCollection messagesList() {
        return $$(byXpath("//div[@class='message-main-container']"));
    }

    public  void editMessageByText(String messageText, String editedMessage) {
        SelenideElement threeDotsButton = $(byXpath("//div[@class='message-main-container']//div[@class='markdown']//span[contains(text(), '$1')]//ancestor::div[@class='message-main-container']//div[contains(@class,'right-menu-btn')]".replace("$1", messageText))),
                editMessageButton = $(byXpath("//div[@class='chat-container active']//div[@class='message-body']//div[@class='message-content']//span[.='$1']//ancestor::div[@class='chat-layout clearfix']//div[@class='right-control-menu']//li[contains(text(), 'Edit Message')]".replace("$1", messageText))),
                textbox = $(byXpath("//div[contains(@class,'chat-container active')]//div[@class='notranslate public-DraftEditor-content']"));

        threeDotsButton.hover().click();
        editMessageButton.click();
        textbox.setValue(editedMessage).pressEnter();
    }

    public  void deleteMessageByText(String message) {
        SelenideElement threeDotsButton =   $(byXpath("//div[@class='message-main-container']//div[@class='markdown']//span[contains(text(), '$1')]//ancestor::div[@class='message-main-container']//div[contains(@class,'right-menu-btn')]".replace("$1", message))),
                deleteMessageButton = $(byXpath("//div[@class='chat-container active']//div[@class='message-body']//div[@class='message-content']//span[.='$1']//ancestor::div[@class='chat-layout clearfix']//div[@class='right-control-menu']//li[contains(text(), 'Delete Message')]".replace("$1", message)));

        threeDotsButton.hover().click();
        deleteMessageButton.click();
        $(byXpath("//button[text()='Delete']")).click();
    }

    public void deleteRoom() {
        $(byTitle("Room settings")).click();
        $(byClassName("delete-room")).click();
        $(byXpath("//button[text()='Delete']")).click();
        sleep(2000);
    }

    public ElementsCollection roomsList() {
        return $$(byXpath("//li[contains(@class,'b-room')]//span[@class=' ccss']"));
    }
}
