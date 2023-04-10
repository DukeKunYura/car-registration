package com.duke.carregistration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.duke.carregistration.mappers.PersonMapper;
import com.duke.carregistration.repository.PersonRepository;
import com.duke.carregistration.services.impl.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.duke.carregistration.controllers.RestController;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    PersonMapper personMapper;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonServiceImpl personService;

    @InjectMocks
    RestController restControllerMock;

    @Test
    void positiveGet() throws Exception {

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
        assertThat(resultPost.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());

        MockHttpServletResponse resultGet = mockMvc.perform(MockMvcRequestBuilders.get("/person_number")
                        .queryParam("number", "343434"))
                .andReturn().getResponse();
        assertThat(resultGet.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void negativeGet() throws Exception {

        MockHttpServletResponse resultGet =
                mockMvc.perform(MockMvcRequestBuilders.get("/person_number")
                                .queryParam("number", "343430"))
                        .andReturn().getResponse();
        assertThat(resultGet.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    // mockMvc.perform(MockMvcRequestBuilders.get("/person_number")
    // .queryParam("number","343430"))

    // assertThrows(ServerException.class,
    // () -> restControllerMock.getPersonByNumber("343436"));

    // PersonDto personDto = new PersonDto();
    // personDto.setPassportNumber("343434");
    // doReturn(personDto).when(restControllerMock).getPersonByNumber(personDto.getPassportNumber());

}
