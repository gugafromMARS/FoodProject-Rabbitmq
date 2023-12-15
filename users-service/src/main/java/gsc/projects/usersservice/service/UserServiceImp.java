package gsc.projects.usersservice.service;


import gsc.projects.usersservice.converter.UserConverter;
import gsc.projects.usersservice.dto.UserCreateDto;
import gsc.projects.usersservice.dto.UserDto;
import gsc.projects.usersservice.dto.UserUpdateDto;
import gsc.projects.usersservice.model.User;
import gsc.projects.usersservice.model.UserLocation;
import gsc.projects.usersservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

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

    public List<Object> getRestaurantByLocation(UserLocation userLocation) {
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" +
                "?keyword=restaurant" +
                "&location=" + userLocation.getLatitude() + "%" + userLocation.getLongitude() +
                "&radius=1000" +
                "&type=restaurant" +
                "&key=AIzaSyBUHH3j33tRkQNKmf36P-HnxkglCpZ9yXg";
        RestTemplate restTemplate = new RestTemplate();
        Object[] restaurants = restTemplate.getForObject(url, Object[].class);
        if(restaurants == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurants at this location in a radius of 10km not found");
        }
        return Arrays.asList(restaurants);
    }
}
