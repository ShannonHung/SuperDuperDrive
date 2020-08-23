package com.finalproject.SuperDuperDrive.FinalProject;

import com.finalproject.SuperDuperDrive.FinalProject.model.ChatMessage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class ChatPage {
    @FindBy(css = "#messageText")
    private WebElement textField;
    @FindBy(id="submitMessage")
    private WebElement submitButton;
    @FindBy(className = "chatMessageUsername")
    private WebElement firstMessageUsername;
    @FindBy(className = "chatMessageText")
    private WebElement firstMessageText;

    public ChatPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    public void sendChatMessage(String text){
        this.textField.sendKeys(text);
        this.submitButton.click();
    }
    //取得第一則訊息
    public ChatMessage getFirstMessage(){
        ChatMessage result = new ChatMessage();
        result.setMessageText(firstMessageText.getText());
        result.setUsername(firstMessageUsername.getText());
        return result;
    }
    //取得全部訊息
    //??
}
