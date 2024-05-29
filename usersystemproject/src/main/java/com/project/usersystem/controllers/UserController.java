package com.project.usersystem.controllers;


import com.project.usersystem.dto.commons.UserDTO;
import com.project.usersystem.dto.user.RequestUserDTO;
import com.project.usersystem.dto.userRole.RequestAddUserRoleDTO;
import com.project.usersystem.dto.userRole.RequestUserRoleDTO;
import com.project.usersystem.service.UserRoleService;
import com.project.usersystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private UserRoleService userRoleService;



    @PostMapping
    public ResponseEntity<?> save(@RequestBody RequestUserDTO requestUserDTO){
        return new ResponseEntity<>(userService.saveUser(requestUserDTO),HttpStatus.CREATED);
    }


    @PostMapping("/add/role")
    public ResponseEntity<?> addRole(@RequestBody RequestUserRoleDTO requestUserRoleDTO){
        userRoleService.addUserRole(requestUserRoleDTO);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        userService.deleteUser(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<ArrayList<UserDTO>> findAll(){
        return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
    }


}
