package com.finalproject.SuperDuperDrive.FinalProject;

import com.finalproject.SuperDuperDrive.FinalProject.service.HashService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class FilePage {
    @FindBy(css="#fileUpload")
    private WebElement fileField;
    @FindBy(css="#FileSubmit")
    private WebElement submitButton;
    @FindBy(css="#DeleteFile")
    private WebElement deleteHref;
    @FindBy(css="#ViewFile")
    private WebElement viewHref;
    @FindBy(css = "#nav-files-tab")
    private WebElement navFileField;

    public FilePage(WebDriver driver){
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }
    public void UploadFile() throws InterruptedException {
        //Find the AbsolutePath of "src/main/resources/img/Taiwan.jpg"
        File file = new File("src/main/resources/img/Taiwan.jpg");
        //put the path into the fileField
        this.fileField.sendKeys(file.getAbsolutePath());
        this.submitButton.click();
    }
    public void DeleteFile(){
        System.out.println("delete was called");
        this.navFileField.click();
        this.deleteHref.click();
    }
    public void ViewFile(){
        this.viewHref.click();
    }

}
