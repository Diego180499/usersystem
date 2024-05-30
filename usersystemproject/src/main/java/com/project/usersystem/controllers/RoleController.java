package com.project.usersystem.controllers;


import com.project.usersystem.dto.commons.RoleDTO;
import com.project.usersystem.dto.role.RequestRoleDTO;
import com.project.usersystem.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            summary = "Guardar un rol",
            description = "EndPoint que almacena un rol",
            tags = "Guardar Rol")
    @ApiResponse(
            responseCode = "204",
            description = "Rol creado correctamente",
            content = @Content)
    @ApiResponse(
            responseCode = "400",
            description = "Error en el formato de  la petición",
            content = @Content)
    @ApiResponse(
            responseCode = "412",
            description = "El rol ya fué agregado",
            content = @Content)
    @PostMapping
    public ResponseEntity<?> save(@RequestBody RequestRoleDTO requestRoleDTO) {
        return new ResponseEntity<>(roleService.save(requestRoleDTO), HttpStatus.CREATED);
    }


    @Operation(
            summary = "Obtener todos los roles",
            description = "EndPoint que devuelve un listado de los roles existentes",
            tags = "Obtener Roles")
    @ApiResponse(
            responseCode = "200",
            description = "Roles obtenidos correctamente",
            content = @Content)
    @GetMapping("/all")
    public ResponseEntity<ArrayList<RoleDTO>> findAll() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }


}
