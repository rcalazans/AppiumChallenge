package Challenge.steps;

import static org.testng.Assert.assertEquals;

import Challenge.api.RequestApi;
import Challenge.dto.Data;
import Challenge.ui.driver.DriverFactory;
import Challenge.utils.DateFormatter;
import Challenge.utils.FullNameCreator;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddNoteSteps extends DriverFactory{
	
	private final Data data;
	
	public AddNoteSteps(Data data) {
        this.data = data;
    }
	
	@Before()
	public void setup() throws Exception {
		createDriver();
	}
	
	@After()
	public void cleanup(Scenario scenario) {
		try {
			if(driver != null ) {
				if (scenario.isFailed()) {
					captureScreenshot();
				}
				closeApp();
			}
			
		} catch (Exception e) {
			System.out.println("Methods failed: cleanup, Exception: " + e.getMessage());
		}
	}
	
	@Given("^the user is on the Add Note page$")
	public void the_user_is_on_the_Add_Note_page() throws Throwable {
		homePage.addNewNote();

	}

	@When("^the user fills in the note information$")
	public void the_user_fills_in_the_note_information() throws Throwable {		
 		data.apiReturn = RequestApi.getRandomUser();
 		data.title = FullNameCreator.GetFullName(data.apiReturn.results[0].name);
 		data.description = DateFormatter.GetCurrentDateFormatted();

 		newNotePage.typeTitle(data.title);
 		newNotePage.typeDescription(data.description);
	}

	@When("^the user create the note$")
	public void the_user_create_the_note() throws Throwable {
		newNotePage.addNote();
	}

	@Then("^the note should be created$")
	public void the_note_should_be_created() throws Throwable {
		assertEquals(homePage.returnNoteTitle(), data.title);
		assertEquals(homePage.returnNoteDescription(), data.description);
	}

}