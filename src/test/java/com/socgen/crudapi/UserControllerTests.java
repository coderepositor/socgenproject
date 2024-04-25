package com.socgen.crudapi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socgen.crudapi.entity.User;
import com.socgen.crudapi.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest
public class UserControllerTests {
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception{
    	
    	User user = new User();
    	user.setFirstName("Pankaj");
    	user.setLastName("Ji");
    	user.setEmail("df@gmail.com");
    	user.setPassword("112322");
    			
    given(userService.createUser(any(User.class)))
    .willAnswer((invocation)->invocation.getArgument(0));
    
    ResultActions response = mockMvc.perform(post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)));

    response.andDo(print()).
    andExpect(status().isCreated())
    .andExpect(jsonPath("$.firstName",
            is(user.getFirstName())))
    .andExpect(jsonPath("$.lastName",
            is(user.getLastName())))
    .andExpect(jsonPath("$.email",
            is(user.getEmail())));
}
}