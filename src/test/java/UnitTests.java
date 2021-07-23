import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.revature.daos.DAOService;
import com.revature.entities.Pin;
import com.revature.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UnitTests {

    private static TestUtil testUtil;
    private static DAOService daoService;

    @BeforeAll
    static void initializeTests() {
        testUtil = new TestUtil();
        daoService = new DAOService();
    }


    @Test
    void testUserCanBeAddedRetrievedAndDeletedFromDatabase() {
        User user = testUtil.createTestUser(1);
        daoService.saveUser(user);
        Assertions.assertNotNull(daoService.getUserByEmail(user.getEmail()));
        daoService.deleteUser(user);
        Assertions.assertNull(daoService.getUserByEmail(user.getEmail()));
    }

    @Test
    void testPinCanBeSavedAndDeletedFromDatabase() {
    }

    @Test
    void getPinByID() {
    }

    @Test
    void getAllPins() {
    }

    @Test
    void getPinOwnerFromPin() {
    }

    @Test
    void getPinOwnerByPinID() {
    }

    @Test
    void updatePin() {
    }

    @Test
    void deletePin() {
    }

    @Test
    void getAllEntities() {
    }

    @Test
    void getEntityByID() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getUserByEmail() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getAllUserPinsByEmail() {
    }

    @Test
    void getAllUserPinsFromUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void validateLogin() {
    }
}
