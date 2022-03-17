package seedu.address.model.tag;

import org.junit.jupiter.api.Test;

import static seedu.address.testutil.Assert.assertThrows;

class JobScopeTagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JobScopeTag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new JobScopeTag(invalidTagName));
    }
}