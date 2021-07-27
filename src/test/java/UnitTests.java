import com.revature.daos.DAOService;
import com.revature.entities.LoginForm;
import com.revature.entities.Pin;
import com.revature.entities.User;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.PersistenceException;

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
        // Assert save correctly saves to database.
        daoService.saveUser(user);
        Assertions.assertNotNull(daoService.getUserByEmail(user.getEmail()));
        // Assert delete correctly deletes from database.
        daoService.deleteUser(user);
        Assertions.assertNull(daoService.getUserByEmail(user.getEmail()));
    }

    @Test
    void testPinCanBeSavedAndDeletedFromDatabase() {
        User user = testUtil.createTestUser(1);
        Pin pin = testUtil.createTestPin(user, 1);
        // Assert pin without owner in database violates foreign key constraint.
        PersistenceException e = Assertions.assertThrows(PersistenceException.class, () -> daoService.savePin(pin));
        Assertions.assertEquals(e.getCause().getClass(), ConstraintViolationException.class);

        // Assert pin with owner in database can be saved.
        daoService.saveUser(user);
        Assertions.assertDoesNotThrow(()->daoService.savePin(pin));
        Assertions.assertNotNull(daoService.getPinByID(pin.getId()));

        // Assert pin can be deleted.
        daoService.deletePin(pin);
        Assertions.assertNull(daoService.getPinByID(pin.getId()));

        // Refresh user to refresh references, then delete.
        user = daoService.getUserByEmail(user.getEmail());
        daoService.deleteUser(user);
    }

    @Test
    void testDeletingUserFromDatabaseDeletesUserPins() {
        User user = testUtil.createTestUser(1);
        Pin pin = testUtil.createTestPin(user, 1);
        Pin pin2 = testUtil.createTestPin(user, 2);
        daoService.saveUser(user);
        daoService.savePin(pin);
        daoService.savePin(pin2);

        // Refresh user to refresh references, then delete.
        user = daoService.getUserByEmail(user.getEmail());
        daoService.deleteUser(user);

        // Assert pins are deleted from database.
        Assertions.assertNull(daoService.getPinByID(pin.getId()));
        Assertions.assertNull(daoService.getPinByID(pin2.getId()));
    }

    @Test
    void testGetAllPinsRetrievesPinsFromAllUsers() {
        int numPins = daoService.getAllPins().size();
        User user = testUtil.createTestUser(1);
        Pin pin = testUtil.createTestPin(user, 1);
        Pin pin2 = testUtil.createTestPin(user, 2);
        daoService.saveUser(user);
        daoService.savePin(pin);
        daoService.savePin(pin2);
        User user2 = testUtil.createTestUser(2);
        Pin pin3 = testUtil.createTestPin(user2, 3);
        Pin pin4 = testUtil.createTestPin(user2, 4);
        daoService.saveUser(user2);
        daoService.savePin(pin3);
        daoService.savePin(pin4);

        Assertions.assertEquals(numPins + 4, daoService.getAllPins().size());

        // Remove test data, assert test data is removed.
        daoService.deleteUser(user);
        daoService.deleteUser(user2);
        Assertions.assertEquals(numPins, daoService.getAllPins().size());
    }

    @Test
    void testGetPinOwnerFromPinAndPinID() {
        User user = testUtil.createTestUser(1);
        Pin pin = testUtil.createTestPin(user, 1);
        daoService.saveUser(user);
        daoService.savePin(pin);

        Assertions.assertEquals(user.toString(), daoService.getPinOwnerFromPin(pin).toString());
        Assertions.assertEquals(user.toString(), daoService.getPinOwnerByPinID(pin.getId()).toString());

        daoService.deleteUser(user);
    }

    @Test
    void getAllUserPinsFromUserAndEmail() {
        User user = testUtil.createTestUser(1);
        Pin pin = testUtil.createTestPin(user, 1);
        Pin pin2 = testUtil.createTestPin(user, 2);
        daoService.saveUser(user);
        daoService.savePin(pin);
        daoService.savePin(pin2);

        Assertions.assertEquals(2,
                daoService.getAllUserPinsFromUser(daoService.getUserByEmail(user.getEmail())).size());
        Assertions.assertEquals(2, daoService.getAllUserPinsByEmail(user.getEmail()).size());

        daoService.deleteUser(user);
    }

    @Test
    void updatedEntitiesAreUpdated() {
        User user = testUtil.createTestUser(1);
        daoService.saveUser(user);
        user.setDisplayName("updatedDisplayName");
        daoService.updateUser(user);
        Assertions.assertEquals(user.toString(), daoService.getUserByEmail(user.getEmail()).toString());

        Pin pin = testUtil.createTestPin(user, 1);
        daoService.savePin(pin);
        pin.setDesc("updated description.");
        daoService.updatePin(pin);
        Assertions.assertEquals(pin.toString(), daoService.getPinByID(pin.getId()).toString());

        daoService.deleteUser(user);
    }

    @Test
    void testValidateLogin() {
        User user = testUtil.createTestUser(1);
        daoService.saveUser(user);
        // Assert valid username, invalid password returns null.
        Assertions.assertNull(daoService.validateLogin(new LoginForm(user.getEmail(), "invalidPassword")));
        // Assert invalid username, valid password returns null.
        Assertions.assertNull(daoService.validateLogin(new LoginForm("invalidUsername", user.getPassword())));
        // Assert valid username and password returns user
        Assertions.assertEquals(user.toString(),
                daoService.validateLogin(new LoginForm(user.getEmail(), user.getPassword())).toString());

        daoService.deleteUser(user);
    }
}
