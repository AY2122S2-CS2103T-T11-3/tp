package seedu.address.model.application;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an Application in InternApply.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Application {

    // Identity fields
    /** Name of the Company */
    private final Name name;
    /** Company Phone Number */
    private final Phone phone;
    /** Company Email */
    private final Email email;

    // Data fields
    /** Company Address */
    private final Address address;
    /** [W.I.P] Will be repurposed in the future */
    private final Set<Tag> tags = new HashSet<>();
    /** Job Title */
    private final JobTitle jobTitle;

    /**
     * Every field must be present and not null.
     */
    public Application(Name name, Phone phone, Email email, Address address, Set<Tag> tags, JobTitle jobTitle) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.jobTitle = jobTitle;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    /**
     * Returns true if both applications have the same name.
     * This defines a weaker notion of equality between two applications.
     */
    public boolean isSameApplication(Application otherApplication) {
        if (otherApplication == this) {
            return true;
        }

        return otherApplication != null
                && otherApplication.getName().equals(getName());
    }

    /**
     * Returns true if both applications have the same identity and data fields.
     * This defines a stronger notion of equality between two applications.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Application)) {
            return false;
        }

        Application otherApplication = (Application) other;
        return otherApplication.getName().equals(getName())
                && otherApplication.getPhone().equals(getPhone())
                && otherApplication.getEmail().equals(getEmail())
                && otherApplication.getAddress().equals(getAddress())
                && otherApplication.getTags().equals(getTags())
                && otherApplication.getJobTitle().equals(getJobTitle());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, jobTitle);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        builder.append("; Job Title: ").append(getJobTitle());
        return builder.toString();
    }

}
