package com.project.usersystem.controllers;


import com.project.usersystem.dto.commons.RoleDTO;
import com.project.usersystem.dto.role.RequestRoleDTO;
import com.project.usersystem.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@AllArgsConstructor
@RestController
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;


    @PostMapping
    public ResponseEntity<?> save(@RequestBody RequestRoleDTO requestRoleDTO){
        return new ResponseEntity<>(roleService.save(requestRoleDTO), HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<ArrayList<RoleDTO>> findAll(){
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }


}
