package com.selenide.examples.staticpageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public static SelenideElement chatLogo = $(byXpath("//span[@class='login-img']"));

    private static SelenideElement     loginField = $(By.xpath("//input[@placeholder='Login']")),
                        passwordField = $(By.xpath("//input[@placeholder='Password']")),
                        loginBtn = $(byText("Sign In"));


    public static void loginAs(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        loginBtn.click();
    }

}
