package com.selenide.examples.fluentpageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.sleep;

public class ChatPage {

    public  SelenideElement preloader = $(byXpath("//div[@class='logo-spinner']")),
     chanelName = $(byXpath("//div[@class='channel-title']//div[@class='channel-room-name']")),
            chanelNamePublic = $(byXpath("//div[@class='chat-beginning-info']//div[@class='channel-name room-public']")),
            addRoomBtn = $(byClassName("add-room-button")),
            roomNameField = $(byName("roomName")),
            addBtn =$(byXpath("//div[@class='modal-dialog  ']//button[@class='btn btn-success']")),
            startText = $(byClassName("start-text"));


    public ChatPage(){
        $(byClassName("team-name")).shouldBe(Condition.visible);
    }


    public ChatPage preloaderIsInVisible(int time) {
        preloader.waitWhile(Condition.visible, time);
        return new ChatPage();
    }

    public ChatPage clickAddRoomBtn() {
        addRoomBtn.click();
        return new ChatPage();
    }

    public ChatPage createRoom(String roomName) {
        roomNameField.setValue(roomName);
        addBtn.click();
        return new ChatPage();
    }

    public ChatPage chanelNameIsVisible() {
        chanelName.waitUntil(Condition.visible, 6000);
        return new ChatPage();
    }

    public ChatPage chanelNameHasText(String text) {
        chanelName.shouldHave(Condition.text(text));
        return new ChatPage();
    }

    public ChatPage chanelNamePublicShouldHaveText(String text) {
        chanelNamePublic.shouldHave(Condition.text(text));
        return new ChatPage();
    }

    public ChatPage startTextShouldHaveText(String text) {
        startText.shouldHave(Condition.text(text));
        return new ChatPage();
    }

    public ChatPage openRoom(String roomName) {
        $$(byClassName("b-room")).findBy(Condition.text(roomName)).click();
        sleep(2000);
        return new ChatPage();
    }

    public ChatPage writeMsg(String message) {
        SelenideElement textbox = $(byXpath("//div[contains(@class,'chat-container active')]//div[@class='notranslate public-DraftEditor-content']"));
        textbox.waitUntil(Condition.visible, 6);
        textbox.setValue(message).pressEnter();
        return new ChatPage();
    }

    public ElementsCollection messagesList() {
        return $$(byXpath("//div[@class='message-main-container']"));
    }

    public ChatPage deleteMessageByText(String message) {
        SelenideElement threeDotsButton =   $(byXpath("//div[@class='message-main-container']//div[@class='markdown']//span[contains(text(), '$1')]//ancestor::div[@class='message-main-container']//div[contains(@class,'right-menu-btn')]".replace("$1", message))),
                deleteMessageButton = $(byXpath("//div[@class='chat-container active']//div[@class='message-body']//div[@class='message-content']//span[.='$1']//ancestor::div[@class='chat-layout clearfix']//div[@class='right-control-menu']//li[contains(text(), 'Delete Message')]".replace("$1", message)));
        return new ChatPage();
    }

    public ChatPage deleteRoom() {
        $(byTitle("Room settings")).click();
        $(byClassName("delete-room")).click();
        $(byXpath("//button[text()='Delete']")).click();
        return new ChatPage();
    }

    public ElementsCollection roomsList() {
        return $$(byXpath("//li[contains(@class,'b-room')]//span[@class=' ccss']"));
    }
}
