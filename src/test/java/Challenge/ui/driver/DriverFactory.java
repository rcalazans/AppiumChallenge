package Challenge.ui.driver;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import Challenge.ui.pages.HomePage;
import Challenge.ui.pages.NewNotePage;
import Challenge.utils.Configuration;
import Challenge.utils.Report;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverFactory {

	public static AndroidDriver<AndroidElement> driver = null;
	public static HomePage homePage;
	public static NewNotePage newNotePage;

	public static AndroidDriver<AndroidElement> createDriver() throws Exception {
		driver = new AndroidDriver<AndroidElement>(new URL(Configuration.getValue("appium.url")), getCapabilities());

		homePage = new HomePage(driver);
		newNotePage = new NewNotePage(driver);

		return driver;
	}

	private static DesiredCapabilities getCapabilities() throws Exception {
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Configuration.getValue("emulator"));
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		capabilities.setCapability(MobileCapabilityType.APP, getAppPath());

		return capabilities;
	}

	private static String getAppPath() throws Exception {
		
		String appPath = Configuration.getValue("app.file");
		
		File fs = new File(appPath);
		
		if (!fs.exists()) {
			throw new Exception("App is not on the specified folder. Path:" + appPath);
		}
		
		return fs.getAbsolutePath();
	}

	public void closeApp() {
		driver.closeApp();
		driver.quit();
	}

	public static void captureScreenshot() throws IOException, InterruptedException {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		Report.reportScreenshot(srcFile);
	}


}
