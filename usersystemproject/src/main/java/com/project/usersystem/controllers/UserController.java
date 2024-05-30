package com.project.usersystem.controllers;


import com.project.usersystem.anotations.AuthenticationRequired;
import com.project.usersystem.dto.LoginDTO;
import com.project.usersystem.dto.commons.UserDTO;
import com.project.usersystem.dto.user.RequestUserDTO;
import com.project.usersystem.dto.userRole.RequestUserRoleDTO;
import com.project.usersystem.service.UserRoleService;
import com.project.usersystem.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private UserRoleService userRoleService;


    @Operation(
            summary = "Guardar nuevo usuario",
            description = "EndPoint que registra un nuevo usuario al sistema",
            tags = "Guardar Usuario")
    @ApiResponse(
            responseCode = "201",
            description = "Usuario registrado correctamente",
            content = @Content)
    @ApiResponse(
            responseCode = "400",
            description = "Error en la petición",
            content = @Content)
    @ApiResponse(
            responseCode = "412",
            description = "Un usuario ya existente, no puede ser almacenado de nuevo",
            content = @Content)
    @PostMapping
    public ResponseEntity<?> save(@RequestBody RequestUserDTO requestUserDTO) {
        return new ResponseEntity<>(userService.saveUser(requestUserDTO), HttpStatus.CREATED);
    }


    @Operation(
            summary = "Asignarle rol a un usuario",
            description = "EndPoint que asigna role a usuario",
            tags = "Agregar Role a Usuario")
    @ApiResponse(
            responseCode = "204",
            description = "Rol asignado correctamente",
            content = @Content)
    @ApiResponse(
            responseCode = "400",
            description = "Error en el formato de la petición",
            content = @Content)
    @ApiResponse(
            responseCode = "412",
            description = "Ya ha sido asignado el mismo rol al mismo usuario",
            content = @Content)
    @PostMapping("/add/role")
    public ResponseEntity<?> addRole(@RequestBody RequestUserRoleDTO requestUserRoleDTO) {
        userRoleService.addUserRole(requestUserRoleDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Operation(
            summary = "Eliminar usuario",
            description = "EndPoint que elimina a un usuario",
            tags = "Eliminar Usuario")
    @ApiResponse(
            responseCode = "204",
            description = "Usuario eliminado correctamente",
            content = @Content)
    @ApiResponse(
            responseCode = "400",
            description = "Error en el formato de  la petición",
            content = @Content)
    @ApiResponse(
            responseCode = "412",
            description = "El usuario ya fué eliminado",
            content = @Content)
    @DeleteMapping("/{id}")
    @AuthenticationRequired(allowedRoles = {"ADMIN"})
    public ResponseEntity<?> delete(@PathVariable String id, @RequestHeader String accessToken) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Obtener usuarios",
            description = """
                    EndPoint que devuelve una lista de usuarios registrados, mostrando los roles
                    que tienen asignados
                    """,
            tags = "Obtener Usuarios")
    @ApiResponse(
            responseCode = "200",
            description = "Usuarios obtenidos correctamente",
            content = @Content)
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }


    @Operation(
            summary = "Login de usuario",
            description = "EndPoint que gestiona el login de un usuario, devolviendo una Session válida",
            tags = "Login Usuario")
    @ApiResponse(
            responseCode = "201",
            description = "Usuario logeado correctamente",
            content = @Content)
    @ApiResponse(
            responseCode = "401",
            description = "Credenciales inválidas del usuario",
            content = @Content)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        return new ResponseEntity<>(userService.login(loginDTO), HttpStatus.CREATED);
    }


}
