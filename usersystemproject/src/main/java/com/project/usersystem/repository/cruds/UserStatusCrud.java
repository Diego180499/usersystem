package com.project.usersystem.repository.cruds;


import com.project.usersystem.repository.entities.UserStatusEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusCrud  extends CrudRepository<UserStatusEntity,Integer> {

    @Query(value = "SELECT * FROM user_status WHERE status_id = ?",nativeQuery = true)
    public UserStatusEntity findByStatusId(Integer id);

}
