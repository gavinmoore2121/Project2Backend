import com.revature.web.MappingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MappingService.class)
@WebAppConfiguration
class MappingServiceIntegrationTest {

    // Wire the mock application context
    @Autowired
    private WebApplicationContext webApplicationContext;

    // Store mock web application beans.
    private MockMvc mockMvc;

    // Initialize beans.
    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    // Test that test is properly configured
    @Test
    void testWebAppContextProvidesMappingService() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        Assertions.assertNotNull(servletContext);
        Assertions.assertTrue(servletContext instanceof MockServletContext);
        Assertions.assertNotNull(webApplicationContext.getBean("mappingService"));
    }


    @Test
    void validateLogin() {
    }

    @Test
    void getUserPins() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void createUser() {
    }

    @Test
    void createPin() {
    }

    @Test
    void deletePin() {
    }
}