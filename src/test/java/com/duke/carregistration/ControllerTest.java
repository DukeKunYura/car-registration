package com.duke.carregistration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.duke.carregistration.dto.PersonDto;
import com.duke.carregistration.exceptions.ServerException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import com.duke.carregistration.controllers.RestController;
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
}
