package com.example.employeeapi;

import com.example.employeeapi.controller.EmployeeController;
import com.example.employeeapi.errorcode.config.EmployeeErrorCodeConfig;
import com.example.employeeapi.mapper.EmployeeMapper;
import com.example.employeeapi.repo.EmployeeDBOperation;
import com.example.employeeapi.service.EmployeeService;
import com.example.employeeapi.test.config.MongoDBConfigTest;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("unittest")
@ContextConfiguration(classes = {EmployeeController.class, EmployeeService.class, EmployeeMapper.class, EmployeeDBOperation.class, MongoDBConfigTest.class, EmployeeErrorCodeConfig.class})
@ComponentScan(basePackages = {"com.example.employeeapi.controller","com.example.employeeapi.service","com.example.employeeapi.mapper","com.example.employeeapi.repo","com.example.employee.test.config"})
@WebMvcTest
@TestPropertySource(locations = "classpath:/application-unittest.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(properties = "server.port=8089")
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("classpath:tests/createEmployee.json")
    private Resource createEmployee;


    private WireMockServer wireMockServer;

//    @AfterAll
//    void tearDown(){
//        mongoDatabase.getCollection(employee).deleteMany(new Document());
//        mongoDatabase.getCollection(audit).deleteMany(new Document());
//    }


//    @BeforeAll
//    public  void setup(){
////        wireMockServer.start();
//        wireMockServer = new WireMockServer(8089); // Initialize WireMockServer with a specific port
//        wireMockServer.start();
//        configureFor("localhost",wireMockServer.port());
//    }

//    @AfterEach
//    public  void tearDown(){
//        wireMockServer.stop();
//    }

//    @BeforeEach
//    public void setupResponseForWireMockServer(){
////        wireMockServer = new WireMockServer(8081); // Initialize WireMockServer with a specific port
////        wireMockServer.start();
//
//        stubFor(get(urlEqualTo("/customerlink/api")).willReturn(aResponse().withStatus(200).withBody("6577dbcef5ec181c50c3c6d3")));
//        int i=1;
//    }

//    @BeforeEach
//    public void setUp() {
//        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8089));
//        wireMockServer.start();
//
//        stubFor(get(urlEqualTo("/customerlink/api"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withBody("6577dbcef5ec181c50c3c6d3")));
//    }
    private final UriComponents URI_Components= UriComponentsBuilder.fromUriString("/employeeApi").build();

    @Test
    void testCreateEmployee200() throws Exception {
        String createEmployeePayload=resourceToString(createEmployee);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(URI_Components.toUri()).contentType(MediaType.APPLICATION_JSON).content(createEmployeePayload))
                .andExpect(status().isOk()).andReturn();
        System.out.println("This is the result "+mvcResult);
    }

//    @Test
//    void testConnectToCustomerLink() throws Exception {
//
////        wireMockServer = new WireMockServer(8089); // Initialize WireMockServer with a specific port
////        wireMockServer.start();
////
////        stubFor(get(urlEqualTo("/customerlink/api")).willReturn(aResponse().withStatus(200).withBody("6577dbcef5ec181c50c3c6d3")));
//
//        UriComponents url=UriComponentsBuilder.fromUriString("/customerlink/api").build();
//
//        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/customerlink/api").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
//
//        String resultString = mvcResult.getResponse().getContentAsString();
//        assertTrue(resultString.contains("6577dbcef5ec181c50c3c6d3"));
//
//    }



    private String resourceToString(Resource resource) throws IOException {
        Reader reader=new InputStreamReader(resource.getInputStream(), UTF_8);
        return FileCopyUtils.copyToString(reader);

    }
}
