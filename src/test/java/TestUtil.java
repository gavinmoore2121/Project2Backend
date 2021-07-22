import com.revature.entities.Pin;
import com.revature.entities.User;

import java.util.ArrayList;

/**
 * Utility class with some basic entity creation tools to simplify creation of new tests.
 */
public class TestUtil {
    public User createTestUser(int numInTest) {
        return new User("TestUser" + numInTest+ "@email.com", "TestName" + numInTest,
                "TestPassword" + numInTest, new ArrayList<Pin>());
    }
    public Pin createTestPin(User owner, int numInTest) {
        Pin pin = new Pin("TestPin" + numInTest, "TestDescription" + numInTest,
                36.4221 + numInTest, -123.0841 + numInTest, owner);
        owner.addPin(pin);
        return pin;
    }
}
