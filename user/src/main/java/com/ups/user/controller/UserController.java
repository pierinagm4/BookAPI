package com.ups.user.controller;

import com.ups.user.entity.Status;
import com.ups.user.entity.User;
import com.ups.user.repository.UserRepository;
import dto.StatusChangeDto;
import dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<UserDTO> getAllUsers(){
        return convertListUserToListUserDTO(userRepository.findAll());
    }

    @PutMapping("/changeStatus/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable long id, @RequestBody StatusChangeDto statusDto){
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            user.setStatus(Status.valueOf(statusDto.getStatus()));
            userRepository.save(user);
        }
        return new ResponseEntity<>("User status updated successfully", HttpStatus.OK);
    }

    @GetMapping("/users/{status}")
    public List<UserDTO> getUserByStatus(@PathVariable String status){
        return convertListUserToListUserDTO(userRepository.findByStatus(Status.valueOf(status)));
    }


    private UserDTO convertUserToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setStatus(user.getStatus().name());
        return userDTO;
    }

    private List<UserDTO> convertListUserToListUserDTO(List<User> user) {
        return user.stream()
                .map(this::convertUserToUserDTO).toList();
    }

}
