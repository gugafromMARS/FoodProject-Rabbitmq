package gsc.projects.usersservice.service;


import gsc.projects.usersservice.converter.UserConverter;
import gsc.projects.usersservice.dto.UserCreateDto;
import gsc.projects.usersservice.dto.UserDto;
import gsc.projects.usersservice.dto.UserUpdateDto;
import gsc.projects.usersservice.model.User;
import gsc.projects.usersservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserServiceImp {

    private final UserRepository userRepository;
    private final UserConverter userConverter;


    public UserDto createUser(UserCreateDto userCreateDto) {
        User existingUser = userRepository.findByEmail(userCreateDto.getEmail());
        if(existingUser != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User email already exists");
        }
        existingUser = userConverter.fromCreateDto(userCreateDto);
        userRepository.save(existingUser);
        return userConverter.toDto(existingUser);
    }


    public UserDto getUserByEmail(String userEmail) {
        User existingUser = userRepository.findByEmail(userEmail);
        if(existingUser == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User email not found");
        }
        return userConverter.toDto(existingUser);
    }


    public void deleteUserByEmail(String userEmail) {
        User existingUser = userRepository.findByEmail(userEmail);
        if(existingUser == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User email not found");
        }
        userRepository.delete(existingUser);
    }

    public UserDto updateByEmail(String userEmail, UserUpdateDto userUpdateDto) {
        User existingUser = userRepository.findByEmail(userEmail);
        if(existingUser == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User email not found");
        }
        if(userUpdateDto.getEmail() != null && userUpdateDto.getAddress() != null){
            existingUser.setEmail(userUpdateDto.getEmail());
            existingUser.setAddress(userUpdateDto.getAddress());
            return userConverter.toDto(existingUser);
        }
        if(userUpdateDto.getEmail() != null && userUpdateDto.getAddress() == null){
            existingUser.setEmail(userUpdateDto.getEmail());
            return userConverter.toDto(existingUser);
        }
        if(userUpdateDto.getAddress() != null && userUpdateDto.getEmail() == null){
            existingUser.setAddress(userUpdateDto.getAddress());
        }
        return userConverter.toDto(existingUser);
    }

}
