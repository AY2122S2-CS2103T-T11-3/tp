package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.InternApplyMemory;
import seedu.address.model.ReadOnlyInternApplyMemory;
import seedu.address.model.application.Address;
import seedu.address.model.application.Application;
import seedu.address.model.application.Email;
import seedu.address.model.application.JobTitle;
import seedu.address.model.application.Name;
import seedu.address.model.application.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code InternApplyMemory} with sample data.
 */
public class SampleDataUtil {
    public static Application[] getSampleApplications() {
        return new Application[] {
            new Application(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), new JobTitle("Intern")),
            new Application(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), new JobTitle("Janitor")),
            new Application(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), new JobTitle("Software Engineer")),
            new Application(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), new JobTitle("ML Engineer")),
            new Application(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), new JobTitle("CEO")),
            new Application(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), new JobTitle("AI Engineer"))
        };
        // TODO: update the sample applications (currently imported from AB3)
    }

    public static ReadOnlyInternApplyMemory getSampleInternApplyMemory() {
        InternApplyMemory sampleIam = new InternApplyMemory();
        for (Application sampleApplication : getSampleApplications()) {
            sampleIam.addApplication(sampleApplication);
        }
        return sampleIam;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
