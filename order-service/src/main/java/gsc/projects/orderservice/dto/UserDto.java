package gsc.projects.orderservice.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String name;
    private int age;
    private String email;
    private String address;
}
