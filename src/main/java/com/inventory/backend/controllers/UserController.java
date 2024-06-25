package com.inventory.backend.controllers;

import com.inventory.backend.dto.UserDto;
import com.inventory.backend.entities.User;
import com.inventory.backend.services.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserManagementService userManagementService;

    @PostMapping("/auth/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto reg){
        return ResponseEntity.ok(userManagementService.register(reg));
    }

//    @PostMapping("/auth/register")
//    public ResponseEntity<UserDto> register(@RequestBody UserDto reg){
//        UserDto response = userManagementService.register(reg);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }

    @PostMapping("/auth/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto req){
        return ResponseEntity.ok(userManagementService.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<UserDto> refreshToken(@RequestBody UserDto req){
        return ResponseEntity.ok(userManagementService.refreshToken(req));
    }

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<UserDto> getAllUsers(){
        return ResponseEntity.ok(userManagementService.getAllUsers());
    }

    @GetMapping("/admin/get-users/{userId}")
    public ResponseEntity<UserDto> getUSerByID(@PathVariable Long userId){
        return ResponseEntity.ok(userManagementService.getUsersById(userId));
    }

    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody User reqres){
        return ResponseEntity.ok(userManagementService.updateUser(userId, reqres));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<UserDto> getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserDto response = userManagementService.getMyInfo(email);
        return  ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<UserDto> deleteUSer(@PathVariable Long userId){
        return ResponseEntity.ok(userManagementService.deleteUser(userId));
    }
}