package com.finalproject.SuperDuperDrive.FinalProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {
    @FindBy(css = "#inputFirstName")
    private WebElement firstNameField;
    @FindBy(css = "#inputLastName")
    private WebElement lastNameField;
    @FindBy(css = "#inputUsername")
    private WebElement usernameField;
    @FindBy(css = "#inputPassword")
    private WebElement passwordField;
    @FindBy(css = "#submit-button")
    private WebElement submitButton;

    //把該頁面的所有元素裝進去
    public SignUpPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void signup(String firstname, String lastname, String username, String password) throws InterruptedException {
        this.firstNameField.sendKeys(firstname);
        this.lastNameField.sendKeys(lastname);
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
        this.submitButton.click();
    }
}
