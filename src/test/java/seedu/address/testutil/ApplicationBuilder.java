package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.application.Address;
import seedu.address.model.application.Application;
import seedu.address.model.application.Email;
import seedu.address.model.application.InterviewSlot;
import seedu.address.model.application.JobTitle;
import seedu.address.model.application.Name;
import seedu.address.model.application.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Application objects.
 */
public class ApplicationBuilder {

    public static final String DEFAULT_NAME = "Grab Singapore";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "grabSG@gmail.com";
    public static final String DEFAULT_INTERVIEWSLOT = LocalDateTime.MAX.format(DateTimeFormatter
            .ofPattern(InterviewSlot.FORMAT_DATETIME_INPUT));
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_JOBTITLE = "Intern";

    private Name name;
    private Phone phone;
    private Email email;
    private InterviewSlot interviewSlot;
    private Address address;
    private Set<Tag> tags;
    private JobTitle jobTitle;

    /**
     * Creates a {@code ApplicationBuilder} with the default details.
     */
    public ApplicationBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        interviewSlot = new InterviewSlot(DEFAULT_INTERVIEWSLOT);
        tags = new HashSet<>();
        jobTitle = new JobTitle(DEFAULT_JOBTITLE);
    }

    /**
     * Initializes the ApplicationBuilder with the data of {@code applicationToCopy}.
     */
    public ApplicationBuilder(Application applicationToCopy) {
        name = applicationToCopy.getName();
        phone = applicationToCopy.getPhone();
        email = applicationToCopy.getEmail();
        address = applicationToCopy.getAddress();
        interviewSlot = applicationToCopy.getInterviewSlot();
        tags = new HashSet<>(applicationToCopy.getTags());
        jobTitle = applicationToCopy.getJobTitle();
    }

    /**
     * Sets the {@code Name} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Application} that we are building.
     */
    public ApplicationBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code InterviewSlot} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withInterviewSlot(String interviewSlot) {
        this.interviewSlot = new InterviewSlot(interviewSlot);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code JobTitle} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withJobTitle(String jobTitle) {
        this.jobTitle = new JobTitle(jobTitle);
        return this;
    }

    public Application build() {
        return new Application(name, phone, email, address, interviewSlot, tags, jobTitle);
    }

}