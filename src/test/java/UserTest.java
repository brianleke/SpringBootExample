import org.hamcrest.Matchers;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserTest {
    @Test
    public void testShouldKnowTwoUsersWithExactDetailsAreTheSame(){
        User user = new User("FirstName", "LastName");

        User anotherUserWithSameDetails = new User("FirstName", "LastName");

        assertEquals(user, anotherUserWithSameDetails);
    }

    @Test
    public void testShouldKnowTwoUsersWithDifferentDetailsAreNotTheSame(){
        User user = new User("FirstName", "LastName");

        User anotherUserWithDifferentDetails = new User("FirstName", "DifferentLastName");

        assertThat(user, is(not(equalTo(anotherUserWithDifferentDetails))));
    }

    @Test
    public void testKnowsHashCodeForSameUserDetailsIsSame(){
        User user = new User("FirstName", "LastName");

        User anotherUserWithSameDetails = new User("FirstName", "LastName");
        assertEquals(user.hashCode(), anotherUserWithSameDetails.hashCode());
    }

    @Test
    public void testKnowsHashCodeForDifferentUserDetailsIsNotSame(){
        User user = new User("FirstName", "LastName");

        User anotherUserWithSameDetails = new User("FirstName", "AnotherLastName");
        assertThat(user.hashCode(), is(not(Matchers.equalTo(anotherUserWithSameDetails.hashCode()))));
    }


}
