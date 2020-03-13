package com.selenide.examples.voidpageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public SelenideElement chatLogo = $(byXpath("//span[@class='login-img']"));

    private SelenideElement     loginField = $(By.xpath("//input[@placeholder='Login']")),
            passwordField = $(By.xpath("//input[@placeholder='Password']")),
            loginBtn = $(byText("Sign In"));


    public void LoginPage() {
        chatLogo.shouldBe(Condition.visible);
    }


    public void loginAs(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        loginBtn.click();
    }
}
