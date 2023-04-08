package com.duke.carregistration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.duke.carregistration.dto.PersonDto;
import org.hamcrest.collection.IsArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockBodyContent;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.duke.carregistration.controllers.RestController;
import com.duke.carregistration.services.CarRegistrationService;
import com.duke.carregistration.services.CarService;
import com.duke.carregistration.services.PersonService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
public class RegistrationServiceTest {

    @Autowired
    MockMvc mockMvc;
    @Mock
    PersonService personServiceMock;
    @Mock
    CarService carServiceMock;
    @Mock
    CarRegistrationService carRegistrationServiceMock;

    @MockBean
    RestController restControllerMock;

    @Test
    void registration() throws Exception {
        assertNotNull(carRegistrationServiceMock);

        MvcResult resultPost = mockMvc.perform(MockMvcRequestBuilders.post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "surname": "Dega",
                            "firstName": "Edgar",
                            "passportNumber": "343434"
                        }
                        """))
                .andReturn();
        int statusPost = resultPost.getResponse().getStatus();
        System.out.println("статус POST: " + statusPost);


        MvcResult resultGet = mockMvc.perform(MockMvcRequestBuilders.get("/person_number")
                        .queryParam("number","343434"))
                .andReturn();
        int statusGet = resultGet.getResponse().getStatus();
        System.out.println("статус GET: " + statusGet);
    }
}
