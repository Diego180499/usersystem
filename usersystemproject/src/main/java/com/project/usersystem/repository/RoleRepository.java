package com.project.usersystem.repository;


import com.project.usersystem.repository.cruds.RoleCrud;
import com.project.usersystem.repository.entities.RoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RoleRepository {

    private RoleCrud roleCrud;

    public RoleEntity save(RoleEntity roleEntity) {
        return roleCrud.save(roleEntity);
    }


    public boolean existRole(String rolaname) {
        try {
            RoleEntity roleEntity = roleCrud.findByRoleName(rolaname);
            Optional.ofNullable(roleEntity).orElseThrow(() -> new NoSuchElementException());
            return true;
        } catch (NoSuchElementException nse) {
            return false;
        }

    }

    public Optional<RoleEntity> findById(String id) {
        return roleCrud.findById(id);
    }


    public ArrayList<RoleEntity> findAll() {
        return (ArrayList<RoleEntity>) roleCrud.findAll();
    }

}
