package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.application.Application;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final InternApplyMemory addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Application> filteredApplications;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyInternApplyMemory addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new InternApplyMemory(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredApplications = new FilteredList<>(this.addressBook.getApplicationList());
    }

    public ModelManager() {
        this(new InternApplyMemory(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyInternApplyMemory addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyInternApplyMemory getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Application application) {
        requireNonNull(application);
        return addressBook.hasPerson(application);
    }

    @Override
    public void deletePerson(Application target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Application application) {
        addressBook.addPerson(application);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Application target, Application editedApplication) {
        requireAllNonNull(target, editedApplication);

        addressBook.setPerson(target, editedApplication);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Application> getFilteredPersonList() {
        return filteredApplications;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Application> predicate) {
        requireNonNull(predicate);
        filteredApplications.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredApplications.equals(other.filteredApplications);
    }

}
