package com.MyBlog.Security.controller.implementations;

import com.MyBlog.Security.dto.UserCadasterDto;
import com.MyBlog.Security.dto.UserDTO;
import com.MyBlog.Security.enums.Role;
import com.MyBlog.Security.services.UserServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserControllerImplementations.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerImplementationsTest {

    @MockBean
    private UserServices services;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    protected UserCadasterDto usuarioCadastro = new UserCadasterDto("Carlos Amorim", "Carlos001", "senha1234", "email@email.com");
    protected UserDTO usuarioDto = new UserDTO("Carlos Amorim", "Carlos001", Role.USER, "email@email.com");
    private String url = "/api/v1";

    @Test
    @DisplayName("Endpoint cadastro de Usuario")
    @WithAnonymousUser
    void shouldBeRegisterNewUser() throws Exception {
        given(services.registerNewUser(usuarioCadastro)).willReturn(usuarioDto);

        mockMvc.perform(post(url + "/register").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(usuarioCadastro)))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(usuarioDto)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();
    }
}