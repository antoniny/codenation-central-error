package com.challenge.repository;

import com.challenge.entity.Role;
import com.challenge.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User , Long> {

    Optional<User> findByEmailIgnoreCaseAndStatus(String email, String status);

    Optional<List<User>> findAllByStatus(String status, Pageable pageable);

    @Query("SELECT r FROM role r where upper(r.name) = upper(:name) ")
    Optional<Role> findByRoles_Name(@Param("name") String name);

}
