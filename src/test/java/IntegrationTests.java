import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.revature.entities.Pin;
import com.revature.entities.User;
import com.revature.web.MappingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MappingService.class)
@WebAppConfiguration
class IntegrationTests {

    // Wire the mock application context
    @Autowired
    private WebApplicationContext webApplicationContext;

    // Store mock web application beans.
    private MockMvc mockMvc;

    private static TestUtil testUtil;
    private static ObjectWriter writer;

    @BeforeAll
    static void initializeTests() {
        testUtil = new TestUtil();
        // Configure object mapper
        ObjectMapper mapper = new ObjectMapper();
        writer = mapper.writer();
    }

    // Initialize beans.
    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void testWebAppContextProvidesMappingService() {
        // Confirms mock context is correctly configured.
        ServletContext servletContext = webApplicationContext.getServletContext();

        Assertions.assertNotNull(servletContext);
        Assertions.assertTrue(servletContext instanceof MockServletContext);
        Assertions.assertNotNull(webApplicationContext.getBean("mappingService"));
    }

    @Test
    void testBasicGetMethodCanBeRetrieved() throws Exception {
        // Test basic call
        this.mockMvc.perform(get("/testConnection"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=ISO-8859-1"))
                .andExpect(content().string("Connection valid, here's a number: 1!"));
        // Test parameterized call
        this.mockMvc.perform(get("/testConnection").param("id", "5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=ISO-8859-1"))
                .andExpect(content().string("Connection valid, here's a number: 5!"));
    }

    /*
    @Test
    void testCreateUserCreatesNewUser() throws Exception {
        User user = testUtil.createTestUser(1);
        testUtil.createTestPin(user, 1);

        String requestJson = "'" + writer.writeValueAsString(user) + "'";
        System.out.println(requestJson);
        MvcResult result = this.mockMvc.perform(post("/createUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                //.andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResolvedException().getMessage());
    }
    */
}