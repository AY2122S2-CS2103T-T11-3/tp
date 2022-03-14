package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.InternApplyMemory;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyInternApplyMemory;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.application.Application;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Application validApplication = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validApplication).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validApplication), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validApplication), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Application validApplication = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validApplication);
        ModelStub modelStub = new ModelStubWithPerson(validApplication);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_APPLICATION, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Application alice = new PersonBuilder().withName("Alice").build();
        Application bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getInternApplyMemoryFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternApplyMemoryFilePath(Path internApplyMemoryFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addApplication(Application application) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternApplyMemory(ReadOnlyInternApplyMemory newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyInternApplyMemory getInternApplyMemory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasApplication(Application application) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteApplication(Application target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setApplication(Application target, Application editedApplication) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Application> getFilteredApplicationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredApplicationList(Predicate<Application> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Application application;

        ModelStubWithPerson(Application application) {
            requireNonNull(application);
            this.application = application;
        }

        @Override
        public boolean hasApplication(Application application) {
            requireNonNull(application);
            return this.application.isSameApplication(application);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Application> personsAdded = new ArrayList<>();

        @Override
        public boolean hasApplication(Application application) {
            requireNonNull(application);
            return personsAdded.stream().anyMatch(application::isSameApplication);
        }

        @Override
        public void addApplication(Application application) {
            requireNonNull(application);
            personsAdded.add(application);
        }

        @Override
        public ReadOnlyInternApplyMemory getInternApplyMemory() {
            return new InternApplyMemory();
        }
    }

}
