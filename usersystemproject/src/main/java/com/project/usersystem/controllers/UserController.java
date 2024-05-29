package com.project.usersystem.controllers;


import com.project.usersystem.exception.ErrorDTO;
import com.project.usersystem.dto.user.request.RequestUserDTO;
import com.project.usersystem.exception.BadRequestException;
import com.project.usersystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;



    @PostMapping
    public ResponseEntity<?> save(@RequestBody RequestUserDTO requestUserDTO){
        return new ResponseEntity<>(userService.saveUser(requestUserDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        userService.deleteUser(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
