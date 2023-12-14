package gsc.projects.usersservice.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {

    private String name;
    private int age;
    private String email;
    private String address;
}
