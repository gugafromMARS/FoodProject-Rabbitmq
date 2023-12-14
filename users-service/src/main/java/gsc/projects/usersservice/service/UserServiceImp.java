package gsc.projects.usersservice.service;


import gsc.projects.usersservice.converter.UserConverter;
import gsc.projects.usersservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImp {

    private final UserRepository userRepository;
    private final UserConverter userConverter;


}
