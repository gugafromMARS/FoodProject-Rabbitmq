package gsc.projects.usersservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gsc.projects.usersservice.dto.UserCreateDto;
import gsc.projects.usersservice.dto.UserDto;
import gsc.projects.usersservice.dto.UserUpdateDto;
import gsc.projects.usersservice.model.User;
import gsc.projects.usersservice.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    private UserCreateDto userCreateDto;
    private UserDto userDto;
    private User user;
    private UserUpdateDto userUpdateDto;
    @BeforeEach
    void setUp() {
        userRepository.deleteAll();

        userCreateDto = new UserCreateDto();
        userCreateDto.setName("test");
        userCreateDto.setAge(27);
        userCreateDto.setEmail("test@email.com");
        userCreateDto.setAddress("Street test");

        user = new User();
        user.setName(userCreateDto.getName());
        user.setAge(user.getAge());
        user.setEmail(userCreateDto.getEmail());
        user.setAddress(userCreateDto.getAddress());

        userDto = new UserDto();
        userDto.setName(userCreateDto.getName());
        userDto.setAge(userCreateDto.getAge());
        userDto.setEmail(userCreateDto.getEmail());
        userDto.setAddress(userCreateDto.getAddress());
    }

    void updateUser(){
        userUpdateDto = new UserUpdateDto();
        userUpdateDto.setEmail("newEmail@gmail.com");
        userUpdateDto.setAddress("new Street");

    }

    void saveUser() {
        userRepository.save(user);
    }

    @Nested
    @Tag("Controller integration tests")
    public class ControllerIntegrationTests {

        @Test
        @DisplayName("Create an correct user and return 200")
        public void createACorrectUser200() throws Exception {
            ResultActions response = mockMvc.perform(post("/api/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userCreateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name", is(userCreateDto.getName())))
                    .andExpect(jsonPath("$.email", is(userCreateDto.getEmail())));
        }

        @Test
        @DisplayName("Create an existing user and return 400")
        public void createAnExistsUser400() throws Exception {
            saveUser();
            ResultActions response = mockMvc.perform(post("/api/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userCreateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Get an exists user by email and return 200")
        public void getAnExistsUserAndReturn200() throws Exception {
            saveUser();
            ResultActions response = mockMvc.perform(get("/api/user/{userEmail}", userCreateDto.getEmail()));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.email", is(userDto.getEmail())));
        }

        @Test
        @DisplayName("Get a non exists user by email and return 404")
        public void getANonExistsUserAndReturn404() throws Exception {
            saveUser();
            ResultActions response = mockMvc.perform(get("/api/user/{userEmail}", "testing@email.com"));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Delete an user with valid email and return 200")
        public void deleteAnExistsUser200() throws Exception {
            saveUser();

            ResultActions response = mockMvc.perform(delete("/api/user/{userEmail}", userCreateDto.getEmail()));
            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Try to delete an user with invalid email and return 404")
        public void deleteANonExistsUser404() throws Exception {
            saveUser();

            ResultActions response = mockMvc.perform(delete("/api/user/{userEmail}", "testing@email.com"));
            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Update email and address from an exists user and return 200")
        public void updateAnExistsUser200() throws Exception {
            saveUser();
            updateUser();

            ResultActions response = mockMvc.perform(put("/api/user/{userEmail}", userCreateDto.getEmail())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userUpdateDto)));
            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.email", is(userUpdateDto.getEmail())))
                    .andExpect(jsonPath("$.address", is(userUpdateDto.getAddress())));
        }

        @Test
        @DisplayName("Try to update an user with invalid email and return 404")
        public void updateANonExistsUser404() throws Exception {
            updateUser();

            ResultActions response = mockMvc.perform(put("/api/user/{userEmail}", "testing@email.com")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userUpdateDto)));
            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound());
        }
    }
}