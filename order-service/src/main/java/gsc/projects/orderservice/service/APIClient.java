package gsc.projects.orderservice.service;


import gsc.projects.orderservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8080/api/user", name = "USERS-SERVICE")
public interface APIClient {

    @GetMapping("/{userEmail}")
    public UserDto getUser(@PathVariable("userEmail") String userEmail);
}
