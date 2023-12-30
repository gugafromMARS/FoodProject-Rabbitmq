package gsc.projects.usersservice.converter;


import gsc.projects.usersservice.dto.UserCreateDto;
import gsc.projects.usersservice.dto.UserDto;
import gsc.projects.usersservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {


    public UserDto toDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .email(user.getEmail())
                .address(user.getAddress())
                .build();
    }

    public User fromCreateDto(UserCreateDto userCreateDto){
        return User.builder()
                .withName(userCreateDto.getName())
                .withAge(userCreateDto.getAge())
                .withEmail(userCreateDto.getEmail())
                .withAddress(userCreateDto.getAddress())
                .build();
    }
}
