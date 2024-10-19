package org.formataxproject.controller;

import org.formataxproject.dto.UserDTO;
import org.formataxproject.entity.User;
import org.formataxproject.mapper.UserMapper;
import org.formataxproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(userMapper.userToUserDTO(createdUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        return user.map(value -> ResponseEntity.ok(userMapper.userToUserDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        Optional<User> existingUser = userService.findUserById(id);
        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setUsername(userDTO.getUsername());
            userToUpdate.setEmail(userDTO.getEmail());
            User updatedUser = userService.updateUser(userToUpdate);
            return ResponseEntity.ok(userMapper.userToUserDTO(updatedUser));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.findUserById(id).isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}