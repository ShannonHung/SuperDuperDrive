package com.finalproject.SuperDuperDrive.FinalProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CredenPage {
    @FindBy(css = "#addNewCred")
    private WebElement AddCred;

    @FindBy(css = "#nav-credentials-tab")
    private  WebElement navCredField;

    @FindBy(css = "#credential-url")
    private WebElement urlField;

    @FindBy(css = "#credential-username")
    private WebElement usernameField;

    @FindBy(css = "#credential-password")
    private WebElement passwordField;

    @FindBy(css = "#SaveCred")
    private WebElement saveButton;

    @FindBy(css = "#edit-Cred")
    private WebElement editButton;

    @FindBy(css = "#DeleteCred")
    private WebElement deleteButton;

    public CredenPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void AddCred(String url, String username, String password) throws InterruptedException {
        this.navCredField.click();
//        Thread.sleep(1000);
        this.AddCred.click();
//        Thread.sleep(1000);
        this.urlField.sendKeys(url);
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
//        Thread.sleep(2000);
        this.saveButton.click();
//        Thread.sleep(2000);
    }
    public void editCred(String url, String username, String password) throws InterruptedException {
        this.navCredField.click();
//        Thread.sleep(1000);
        this.editButton.click();
//        Thread.sleep(1000);
        this.urlField.sendKeys(url);
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
//        Thread.sleep(2000);
        this.saveButton.click();
//        Thread.sleep(2000);
    }
    public void deleteCred() throws InterruptedException {
        this.navCredField.click();
//        Thread.sleep(1000);
        this.deleteButton.click();
//        Thread.sleep(1000);
    }
}
