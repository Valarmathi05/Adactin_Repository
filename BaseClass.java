package com.Base;

	import java.awt.Robot;
	import java.io.File;
	import java.io.IOException;
	import java.util.List;

	import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.Alert;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.Select;

	public class BaseClass {
		public static WebDriver driver;

		public static WebDriver browserLaunch(String browser) {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\Valarmathi Vignesh\\eclipse-workspace\\ProjectCucumber\\Driver\\chromedriver.exe");
				driver = new ChromeDriver();
				
			}
			driver.manage().window().maximize();
			return driver;
		}
		

		public static void url(String url) {
			driver.get(url);
			
		}

		public static void currentUrl() {
			System.out.println("The current url is " + driver.getCurrentUrl());
		}

		public static void getTitle() {
			System.out.println("The current Titile is " + driver.getTitle());

		}

		public static void navigateto(String url) {
			driver.navigate().to(url);

		}

		public static void navigate(String type) {
			if (type.equalsIgnoreCase("back")) {
				driver.navigate().back();
			} else if (type.equalsIgnoreCase("forward")) {
				driver.navigate().forward();
			} else if (type.equalsIgnoreCase("refresh")) {
				driver.navigate().refresh();
			}
		}

		public static void input(WebElement element, String input) {
			element.sendKeys(input);
		}

		public static void click(WebElement element) {
			element.click();
		}

		public static void dropDown(WebElement locator, String type, String value) {
			Select select = new Select(locator);
			switch (type) {
			case "index":
				select.selectByIndex(Integer.parseInt(value));
				break;
			case "value":
				select.selectByValue(value);
				break;
			case "visibletext":
				select.selectByVisibleText(value);
				break;
			case "deselectbyindex":
				select.deselectByIndex(Integer.parseInt(value));
				break;
			case "deselectbyvalue":
				select.deselectByValue(value);
				break;
			case "deselectbyvisibletext":
				select.deselectByVisibleText(value);
				break;
			case "deselectall":
				select.deselectAll();
				break;
			default:
				System.out.println("Please enter the correct type for dropdown"
						+ "\nindex, value,visibletext,deselectbyindex,deselectbyvalue,deselectbyvisibletext,deselectall ");
			break;
			}
		}

		public static void getOptions(WebElement element, String type) {
			Select select1 = new Select(element);
			if (type.equalsIgnoreCase("getallselectedoptions")) {
				List<WebElement> selectedOptions = select1.getAllSelectedOptions();
				for (WebElement option : selectedOptions) {
					System.out.println(option.getText());
				}
			} else if (type.equalsIgnoreCase("getoptions")) {
				List<WebElement> options = select1.getOptions();
				for (WebElement get_options : options) {
					System.out.println(get_options.getText());
				}
			} else if (type.equalsIgnoreCase("firstselectoption")) {
				System.out.println(select1.getFirstSelectedOption().getText());
			}

		}

		public static void alert(WebElement element, String type, String value) {
			Alert alert = driver.switchTo().alert();
			if (type.equalsIgnoreCase("simple")) {
				alert.accept();
			} else if (type.equalsIgnoreCase("confirm")) {
				if (value.equalsIgnoreCase("okay")) {
					alert.accept();
				} else {
					alert.dismiss();
				}
			} else if (type.equalsIgnoreCase("prompt")) {
				alert.sendKeys(value);
				alert.accept();
			}
		}

		public static void frame(String type, String value, WebElement element) {
			if (type.equalsIgnoreCase("index")) {
				driver.switchTo().frame(Integer.parseInt(value));
			} else if (type.equalsIgnoreCase("nameorID")) {
				driver.switchTo().frame(value);
			} else if (type.equalsIgnoreCase("element")) {
				driver.switchTo().frame(element);
			} else if (type.equalsIgnoreCase("default")) {
				driver.switchTo().defaultContent();
			}
		}

		public static void mouse(WebElement element1, WebElement element2, String type) {
			Actions mouse = new Actions(driver);
			 if (type.equalsIgnoreCase("moveto")) {
				mouse.moveToElement(element1).build().perform();
			} else if (type.equalsIgnoreCase("click")) {
				mouse.click(element1).build().perform();
			} else if (type.equalsIgnoreCase("holdfree")) {
				mouse.clickAndHold(element1).build().perform();
				mouse.release(element2).build().perform();			
			}else if (type.equalsIgnoreCase("rightclick")) {
				mouse.contextClick(element1).build().perform();
				}else if (type.equalsIgnoreCase("dragdrop")) {
					mouse.dragAndDrop(element1, element2).build().perform();
				} 
			
			else {
				System.out.println("Please enter the correct type to choose" + "\nmoveto,click,holdfree");
			}
		}
		public static void dragDrop(WebElement element1, WebElement element2) {
			Actions mouse2=new Actions(driver);
			mouse2.dragAndDrop(element1, element2).build().perform();

		}

		public static void screenShot(String name) throws IOException {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File path = new File("D:\\Maven Project\\Maven_Project\\ScreenShot\\" + name + ".png");
			FileUtils.copyFile(src, path);
		}
	}


