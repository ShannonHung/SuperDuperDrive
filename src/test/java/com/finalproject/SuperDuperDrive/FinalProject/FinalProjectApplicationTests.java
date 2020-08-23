package com.finalproject.SuperDuperDrive.FinalProject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FinalProjectApplicationTests {

	@LocalServerPort
	public int port;
	public static WebDriver driver;
	public String baseURL;

	SignUpPage signUpPage = new SignUpPage(driver);
	FilePage filePage = new FilePage(driver);
	NotePage notePage = new NotePage(driver);
	CredenPage credenPage = new CredenPage(driver);



	@BeforeAll
	public static void beforeAll(){
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterAll
	public static void afterAll() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
		driver = null;
	}

	@BeforeEach
	public void beforeEach(){
		baseURL = "http://localhost:"+port;
	}

	@Test
	@Order(1)
	public void testSignUp() throws InterruptedException {

		driver.get(baseURL + "/home");
		//進入signup畫面
		driver.get(baseURL + "/signup");
		//把signup畫面的元素填入object
		signUpPage.signup("Hsuan", "Hung", "Shannon", "123");
	}
	@Test
	@Order(2)
	public void testLogin() throws InterruptedException{

		driver.get(baseURL + "/home");
		//進入login畫面
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("Shannon", "123");
	}
	// add file, note and description
	@Test
	@Order(3)
	public void testFileUpload() throws  InterruptedException{

		driver.get(baseURL + "/home");
		filePage.UploadFile();
	}
	@Test
	@Order(4)
	public void testAddNote() throws InterruptedException {
		driver.get(baseURL + "/home");
		notePage.AddNote("UnitTest", "Here is going to add a new Note");
	}
	@Test
	@Order(5)
	public void testAddDesc() throws InterruptedException {

		driver.get(baseURL + "/home");
		credenPage.AddCred("google", "shannon", "123");
	}

	//view file, edit note and description
	@Test
	@Order(6)
	public void testFileView() throws InterruptedException{

		driver.get(baseURL + "/home");
		filePage.ViewFile();
	}
	@Test
	@Order(7)
	public void testEditNote() throws InterruptedException{

		driver.get(baseURL + "/home");
		notePage.EditNote("Test2", "here is edit note");
	}
	@Test
	@Order(8)
	public void testEditDesc() throws InterruptedException {

		driver.get(baseURL + "/home");
		credenPage.editCred("yahoo", "micky", "456");
	}

	// Delete file, note and description
	@Test
	@Order(9)
	public void testFileDelete() throws InterruptedException{

		driver.get(baseURL + "/home");
		filePage.DeleteFile();
	}
	@Test
	@Order(10)
	public void testNoteDelete() throws  InterruptedException{

		driver.get(baseURL + "/home");
		notePage.DeleteNote();
	}
	@Test
	@Order(11)
	public void testDescDelete() throws  InterruptedException{

		driver.get(baseURL + "/home");
		credenPage.deleteCred();
	}
	@Test
	@Order(12)
	public void testlogout() throws  InterruptedException{

		driver.get(baseURL + "/home");
		driver.findElement(By.id("logout")).click();
	}

}
