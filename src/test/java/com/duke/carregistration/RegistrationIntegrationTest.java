package com.duke.carregistration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.duke.carregistration.mappers.PersonMapper;
import com.duke.carregistration.repository.CarRepository;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.UUID;

import com.duke.carregistration.controllers.RestController;
import com.duke.carregistration.entity.Car;
import com.duke.carregistration.entity.Person;
import com.duke.carregistration.kit.PairId;

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
        CarRepository carRepository;
        @Autowired
        PersonServiceImpl personService;

        @InjectMocks
        RestController restControllerMock;

        @Test
        void positiveGetPerson() throws Exception {

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
        void negativeGetPerson() throws Exception {

                MockHttpServletResponse resultGet = mockMvc.perform(MockMvcRequestBuilders.get("/person_number")
                                .queryParam("number", "343430"))
                                .andReturn().getResponse();
                assertThat(resultGet.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        }

        @Test
        void positiveGetCar() throws Exception {

                MvcResult resultPost = mockMvc.perform(MockMvcRequestBuilders.post("/car")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content("""
                                                {
                                                "brand": "Renault",
                                                "color": "White",
                                                "number": "3000"
                                                }
                                                """))
                                .andReturn();
                assertThat(resultPost.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());

                MockHttpServletResponse resultGet = mockMvc.perform(MockMvcRequestBuilders.get("/car_number")
                                .queryParam("number", "3000"))
                                .andReturn().getResponse();
                assertThat(resultGet.getStatus()).isEqualTo(HttpStatus.OK.value());
        }

        @Test
        void registration() throws Exception {

                mockMvc.perform(MockMvcRequestBuilders.post("/person")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content("""
                                                {
                                                "surname": "Dega",
                                                "firstName": "Edgar",
                                                "passportNumber": "303030"
                                                }
                                                """));

                String personId = mockMvc.perform(MockMvcRequestBuilders.get("/person_number")
                                .queryParam("number", "303030"))
                                .andReturn()
                                .getResponse()
                                .getContentAsString()
                                .substring(7, 43);

                mockMvc.perform(MockMvcRequestBuilders.post("/car")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content("""
                                                {
                                                "brand": "Renault",
                                                "color": "White",
                                                "number": "4000"
                                                }
                                                """));

                String carId = mockMvc.perform(MockMvcRequestBuilders.get("/car_number")
                                .queryParam("number", "4000"))
                                .andReturn()
                                .getResponse()
                                .getContentAsString()
                                .substring(7, 43);

                PairId pairId = new PairId();
                pairId.setPersonId(UUID.fromString(personId));
                pairId.setCarId(UUID.fromString(carId));
                assertEquals(pairId, null);

                MvcResult resultRegistration = mockMvc.perform(MockMvcRequestBuilders.post("/registration_car")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.asString(pairId)))
                                .andReturn();
                assertThat(resultRegistration.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());

        }

        // @Test
        // void registration() throws Exception {

        // Person person = personRepository.findByPassportNumber("343434");
        // UUID personId = person.getId();
        // Car car = carRepository.findByNumber("3000");
        // UUID carId = car.getId();
        // PairId pairId = new PairId();
        // pairId.setPersonId(personId);
        // pairId.setCarId(carId);

        // MvcResult resultRegistration =
        // mockMvc.perform(MockMvcRequestBuilders.post("/registration_car")
        // .contentType(MediaType.APPLICATION_JSON)
        // .accept(MediaType.APPLICATION_JSON)
        // .content(this.asString(pairId)))
        // .andReturn();
        // assertThat(resultRegistration.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());

        // }

        public String asString(PairId pairId) {
                String pair = "\"\"\"{\"personId\": \"" + pairId.getPersonId() + "\",\"carId\": \"" + pairId.getCarId()
                                + "\"}\"\"\"";
                return pair;
        }

        // mockMvc.perform(MockMvcRequestBuilders.get("/person_number")
        // .queryParam("number","343430"))

        // assertThrows(ServerException.class,
        // () -> restControllerMock.getPersonByNumber("343436"));

        // PersonDto personDto = new PersonDto();
        // personDto.setPassportNumber("343434");
        // doReturn(personDto).when(restControllerMock).getPersonByNumber(personDto.getPassportNumber());

}
