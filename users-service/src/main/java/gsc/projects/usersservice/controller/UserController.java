package gsc.projects.usersservice.controller;


import gsc.projects.usersservice.dto.UserCreateDto;
import gsc.projects.usersservice.dto.UserUpdateDto;
import gsc.projects.usersservice.service.UserServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserServiceImp userServiceImp;

    @PostMapping
    public ResponseEntity<?> create(UserCreateDto userCreateDto){
        return new ResponseEntity<>(userServiceImp.createUser(userCreateDto), HttpStatus.CREATED);
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity<?> getUser(@PathVariable("userEmail") String userEmail){
        return ResponseEntity.ok(userServiceImp.getUserByEmail(userEmail));
    }

    @DeleteMapping("/{userEmail}")
    public ResponseEntity<?> delete(@PathVariable ("userEmail") String userEmail){
        userServiceImp.deleteUserByEmail(userEmail);
        return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
    }
    @PutMapping("/{userEmail}")
    public ResponseEntity<?> update(@PathVariable ("userEmail") String userEmail, UserUpdateDto userUpdateDto){
        return ResponseEntity.ok(userServiceImp.updateByEmail(userEmail, userUpdateDto));
    }
}
