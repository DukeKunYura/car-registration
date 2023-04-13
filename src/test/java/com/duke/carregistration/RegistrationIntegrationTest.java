package com.duke.carregistration;

import com.duke.carregistration.dto.PersonWithCarsDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
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

import java.util.UUID;

import com.duke.carregistration.kit.PairId;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationIntegrationTest {

    @Autowired
    MockMvc mockMvc;

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

        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(pairId);

        MvcResult resultRegistration = mockMvc.perform(MockMvcRequestBuilders.post("/registration_car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andReturn();
        assertThat(resultRegistration.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());

        MockHttpServletResponse resultGet = mockMvc.perform(MockMvcRequestBuilders.get("/person_with_cars")
                        .queryParam("id", personId))
                .andReturn().getResponse();
        assertThat(resultGet.getStatus()).isEqualTo(HttpStatus.OK.value());

        String jsonString = resultGet.getContentAsString();
        PersonWithCarsDto personWithCar = mapper.readValue(jsonString, PersonWithCarsDto.class);
        assertThat(personWithCar.getCars().size()).isEqualTo(1);


    }
}
