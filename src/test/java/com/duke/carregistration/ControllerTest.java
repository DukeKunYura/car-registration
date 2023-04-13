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

import java.util.UUID;

import com.duke.carregistration.controllers.RestController;
import com.duke.carregistration.services.CarRegistrationService;
import com.duke.carregistration.services.CarService;
import com.duke.carregistration.services.PersonService;
import com.duke.carregistration.services.impl.PersonServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {
    @Mock
    PersonServiceImpl personService;

    @InjectMocks
    RestController restControllerMock;

    @Test
    void getPositive() throws Exception {
        assertNotNull(restControllerMock);
        ResponseEntity<PersonDto> resultGet = restControllerMock.getPersonByNumber("343434");
        assertEquals("200 OK", resultGet.getStatusCode().toString());
    }

    @Test
    void postPositive() throws Exception {
        PersonDto person = new PersonDto();
        person.setPassportNumber("797979");
        person.setSurname("Picasso");
        ResponseEntity<PersonDto> resultPost = restControllerMock.addPerson(person);
        assertEquals("201 CREATED", resultPost.getStatusCode().toString());

        assertThrows(ServerException.class,
                () -> restControllerMock.addPerson(null));
    }

    @Test
    void postNegative() throws Exception {
        assertThrows(ServerException.class,
                () -> restControllerMock.addPerson(null));
    }

    @Test
    void putNegative() throws Exception {
        ResponseEntity<PersonDto> resultPut = restControllerMock.editPerson(null, null);
        assertEquals("400 BAD_REQUEST", resultPut.getStatusCode().toString());
    }

    @Test
    void putPositive() throws Exception {
        PersonDto person = new PersonDto();
        person.setPassportNumber("797979");
        person.setSurname("Picasso");
        UUID id = UUID.randomUUID();
        ResponseEntity<PersonDto> resultPut = restControllerMock.editPerson(id, person);
        assertEquals("201 CREATED", resultPut.getStatusCode().toString());

    }

    // ResponseEntity<PersonDto> resultPost = restControllerMock.addPerson(person);
    // assertEquals("200 OK", resultPost.getStatusCode().toString());

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
