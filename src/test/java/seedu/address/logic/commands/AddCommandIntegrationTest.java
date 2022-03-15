package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.Application;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Application validApplication = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getInternApplyMemory(), new UserPrefs());
        expectedModel.addApplication(validApplication);

        assertCommandSuccess(new AddCommand(validApplication), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validApplication), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Application applicationInList = model.getInternApplyMemory().getApplicationList().get(0);
        assertCommandFailure(new AddCommand(applicationInList), model, AddCommand.MESSAGE_DUPLICATE_APPLICATION);
    }

}
