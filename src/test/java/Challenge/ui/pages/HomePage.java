package Challenge.ui.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Challenge.ui.driver.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage extends DriverFactory{

	public HomePage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(id = "com.example.android.testing.notes.mock:id/fab_add_notes")
	private AndroidElement addNotesButton;
	
	@FindBy(id = "com.example.android.testing.notes.mock:id/note_detail_title")
	private AndroidElement noteTitle;
	
	@FindBy(id = "com.example.android.testing.notes.mock:id/note_detail_description")
	private AndroidElement noteDescription;

	public NewNotePage addNewNote() {
		addNotesButton.click();
		return newNotePage;
	}
	
	public String returnNoteTitle() {
		return noteTitle.getText();
	}
	
	public String returnNoteDescription() {
		return noteDescription.getText();
	}
}