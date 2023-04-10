package com.duke.carregistration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.duke.carregistration.dto.PersonDto;
import com.duke.carregistration.exceptions.ServerException;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockBodyContent;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestParam;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.duke.carregistration.controllers.RestController;
import com.duke.carregistration.services.CarRegistrationService;
import com.duke.carregistration.services.CarService;
import com.duke.carregistration.services.PersonService;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {
    @InjectMocks
    RestController restControllerMock;

    @Test
    void getNegative() throws Exception {
        assertNotNull(restControllerMock);
        ResponseEntity<PersonDto> resultGet = restControllerMock.getPersonByNumber("343434");
        assertEquals("404 NOT_FOUND", resultGet.getStatusCode().toString());
    }

    @Test
    void getPositive() throws Exception {





        ResponseEntity<PersonDto> resultGet = restControllerMock.getPersonByNumber("343434");
        assertEquals("200 OK", resultGet.getStatusCode().toString());

        // MvcResult resultPost = mockMvc.perform(MockMvcRequestBuilders.post("/person")
        // .contentType(MediaType.APPLICATION_JSON)
        // .accept(MediaType.APPLICATION_JSON)
        // .content("""
        // {
        // "surname": "Dega",
        // "firstName": "Edgar",
        // "passportNumber": "343434"
        // }
        // """))
        // .andReturn();
        // assertThat(resultPost.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        // assertThrows(ServerException.class,
        // () -> restControllerMock.getPersonByNumber("343436"));

        // PersonDto personDto = new PersonDto();
        // personDto.setPassportNumber("343434");
        // doReturn(personDto).when(restControllerMock).getPersonByNumber(personDto.getPassportNumber());

        // MockHttpServletResponse resultGet =
        // mockMvc.perform(MockMvcRequestBuilders.get("/person_number")
        // .queryParam("number","343434"))
        // .andReturn().getResponse();
        // assertThat(resultGet.getStatus()).isEqualTo(HttpStatus.OK.value());

        // mockMvc.perform(MockMvcRequestBuilders.get("/person_number")
        // .queryParam("number","343430"))
        // .andDo(print()).andExpect(status().isOk());
    }
}
