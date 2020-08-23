package com.finalproject.SuperDuperDrive.FinalProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class NotePage {
    @FindBy(css = "#nav-notes-tab")
    private  WebElement navNoteField;
    //發現不能使用 add-note
    @FindBy(css ="#addNewNote")
    public WebElement addNote;

    @FindBy(css = "#note-title")
    private WebElement noteTitleField;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "SaveNote")
    private WebElement saveNote;

    @FindBy(css="#DeleteNote")
    private WebElement deleteHref;

    @FindBy(css = "#edit-Note")
    private WebElement editNote;

    public NotePage(WebDriver driver){
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    public void AddNote(String Title, String Description) throws InterruptedException {
        this.navNoteField.click();
//        Thread.sleep(1000);
        this.addNote.click();
//        Thread.sleep(1000);
        this.noteTitleField.sendKeys(Title);
        this.noteDescription.sendKeys(Description);
//        Thread.sleep(1000);
        this.saveNote.click();
    }

    public void DeleteNote() throws InterruptedException {
        this.navNoteField.click();
//        Thread.sleep(1000);
        this.deleteHref.click();
//        Thread.sleep(1000);
    }

    public void EditNote(String Title, String description) throws InterruptedException {
        this.navNoteField.click();
//        Thread.sleep(1000);
        this.editNote.click();
        System.out.println("EditNote Was Clicked");
//        Thread.sleep(1000);
        this.noteTitleField.sendKeys(Title);
        this.noteDescription.sendKeys(description);
//        Thread.sleep(1000);
        this.saveNote.click();
    }
}
