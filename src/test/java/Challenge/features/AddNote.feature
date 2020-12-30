Feature: Add note
App user should be able to create a note with title and a description

Scenario: Create note with a title and a description
Given the user is on the Add Note page
When the user fills in the note information
And the user create the note
Then the note should be created