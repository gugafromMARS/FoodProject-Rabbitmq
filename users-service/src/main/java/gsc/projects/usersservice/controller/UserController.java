package gsc.projects.usersservice.controller;


import gsc.projects.usersservice.service.UserServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {


    private final UserServiceImp userServiceImp;
}
