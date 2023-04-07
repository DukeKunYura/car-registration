package com.duke.carregistration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.hamcrest.collection.IsArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

        // restControllerMock.addPerson(null);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/persons"))
                .andReturn();
        Class<? extends MockHttpServletResponse> actual = result.getResponse().getClass();

        Boolean isArr = actual.isArray();
        assertThat(isArr).isEqualTo(false);
    }

}
