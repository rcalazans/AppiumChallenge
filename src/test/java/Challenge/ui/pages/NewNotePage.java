package Challenge.ui.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Challenge.ui.driver.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class NewNotePage extends DriverFactory{

	public NewNotePage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(id = "com.example.android.testing.notes.mock:id/add_note_title")
	private AndroidElement titleField;

	@FindBy(id = "com.example.android.testing.notes.mock:id/add_note_description")
	private AndroidElement descriptionField;

	@FindBy(id = "com.example.android.testing.notes.mock:id/fab_add_notes")
	private AndroidElement addNoteButton;

	public void typeTitle(String text) {
		titleField.sendKeys(text);
	}

	public void typeDescription(String text) {
		descriptionField.sendKeys(text);
	}

	public HomePage addNote() {
		addNoteButton.click();
		return homePage;
	}
}