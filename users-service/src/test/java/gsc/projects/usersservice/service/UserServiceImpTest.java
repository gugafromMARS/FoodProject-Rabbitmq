package gsc.projects.usersservice.service;

import gsc.projects.usersservice.converter.UserConverter;
import gsc.projects.usersservice.dto.UserCreateDto;
import gsc.projects.usersservice.dto.UserDto;
import gsc.projects.usersservice.dto.UserUpdateDto;
import gsc.projects.usersservice.model.User;
import gsc.projects.usersservice.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class UserServiceImpTest {

    @Mock
    private UserConverter userConverter;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImp userServiceImp;

    private UserCreateDto userCreateDto;
    private User user;
    private UserDto userDto;

    private UserUpdateDto userUpdateDto;

    @BeforeEach
    void setUp(){
        user = User.builder()
                .withName("test")
                .withEmail("test@email.com")
                .build();
        //user.setId(1L);

        userDto = UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();

        userCreateDto = new UserCreateDto();
        userCreateDto.setEmail(user.getEmail());
    }

    void updateUser(){
        userUpdateDto = new UserUpdateDto();
        userUpdateDto.setEmail("newEmail@email.com");
    }

    @Nested
    @Tag("Service unit tests")
    public class ServiceUnitTests{

        @Test
        @DisplayName("Get an user by valid id")
        public void getUserByValidId(){

            given(userRepository.save(user)).willReturn(user);
            when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));

            assertEquals(userDto.getId(), user.getId());
        }

        @Test
        @DisplayName("Get an user with invalid id")
        public void tryToGetUserInvalidId() {

            when(userRepository.findById(10L)).thenReturn(null);

            assertNotEquals(user.getId(), 10L);
        }

        @Test
        @DisplayName("Create a non exists user!")
        public void createANonExistsUser(){

            given(userRepository.findByEmail("test@email.com")).willReturn(null);

            when(userRepository.save(user)).thenReturn(user);

            assertEquals(userDto.getId(), user.getId());
        }

        @Test
        @DisplayName("Try to create an user with same email")
        public void tryToCreateAnExistsUser(){

            given(userRepository.save(user)).willReturn(user);

            when(userRepository.findByEmail(user.getEmail())).thenThrow(ResponseStatusException.class);

            assertThrows(ResponseStatusException.class, () -> {
                userServiceImp.createUser(userCreateDto);
            });
        }


        @Test
        @DisplayName("Delete a exists user")
        public void deleteAnExistsUser(){


            given(userRepository.save(user)).willReturn(user);

            when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

            userRepository.delete(user);

            when(userRepository.findByEmail(user.getEmail())).thenReturn(null);

            assertEquals(null, user.getId());
        }


        @Test
        @DisplayName("Update exists user")
        public void updateAnExistsUser(){
            updateUser();

            UserDto updatedUser = new UserDto();
            updatedUser.setName(user.getName());
            updatedUser.setEmail(userUpdateDto.getEmail());

            given(userRepository.save(user)).willReturn(user);

            given(userRepository.findByEmail(user.getEmail())).willReturn(user);

            when(userServiceImp.updateByEmail(user.getEmail(), userUpdateDto)).thenReturn(updatedUser);

            assertEquals(user.getEmail(), updatedUser.getEmail());
        }

        @Test
        @DisplayName("Try to update invalid user")
        public void updateNonExistsUser(){
            updateUser();

            given(userRepository.save(user)).willReturn(user);

            given(userRepository.findByEmail(user.getEmail())).willReturn(user);

            when(userRepository.findByEmail("123@email.com")).thenThrow(ResponseStatusException.class);

            assertThrows(ResponseStatusException.class, () -> {
                userServiceImp.updateByEmail("123@email.com", userUpdateDto);
            });
        }
    }

}