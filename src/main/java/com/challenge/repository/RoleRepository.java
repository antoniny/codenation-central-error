package com.challenge.repository;

import com.challenge.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends BaseRepository<Role , Long> {

    Optional<Role> findByNameIgnoreCase(String name);

}
